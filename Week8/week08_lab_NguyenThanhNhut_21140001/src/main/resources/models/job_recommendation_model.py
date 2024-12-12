import numpy as np
import pandas as pd
import pymysql
import tensorflow as tf
from difflib import SequenceMatcher

class JobRecommendationModel:
    def __init__(self, host, user, password, database):
        self.connection = pymysql.connect(
            host=host,
            user=user,
            password=password,
            database=database,
            cursorclass=pymysql.cursors.DictCursor
        )
        self.load_data()

    def load_data(self):
        with self.connection.cursor() as cursor:
            # Load Skills
            cursor.execute("SELECT skill_id, skill_name FROM skill")
            self.skills = pd.DataFrame(cursor.fetchall())

            # Load Jobs
            cursor.execute("SELECT job_id, job_name FROM job")
            self.jobs = pd.DataFrame(cursor.fetchall())

            # Load Job Skills with Skill Names
            cursor.execute("""
            SELECT js.job_id, js.skill_id, s.skill_name, 
                   CASE js.skill_level
                       WHEN 'BEGINNER' THEN 1
                       WHEN 'INTERMEDIATE' THEN 2
                       WHEN 'ADVANCED' THEN 3
                       WHEN 'PROFESSIONAL' THEN 4
                       WHEN 'MASTER' THEN 5
                       ELSE 0 END as skill_weight
            FROM job_skill js
            JOIN skill s ON js.skill_id = s.skill_id
            """)
            self.job_skills = pd.DataFrame(cursor.fetchall())

            # Load Candidate Skills with Skill Names
            cursor.execute("""
            SELECT cs.candidate_id, cs.skill_id, s.skill_name, 
                   CASE cs.skill_level
                       WHEN 'BEGINNER' THEN 1
                       WHEN 'INTERMEDIATE' THEN 2
                       WHEN 'ADVANCED' THEN 3
                       WHEN 'PROFESSIONAL' THEN 4
                       WHEN 'MASTER' THEN 5
                       ELSE 0 END as skill_weight
            FROM candidate_skill cs
            JOIN skill s ON cs.skill_id = s.skill_id
            """)
            self.candidate_skills = pd.DataFrame(cursor.fetchall())

    def compute_skill_match(self, candidate_id):
        candidate_skills = self.candidate_skills[self.candidate_skills['candidate_id'] == candidate_id]
        job_recommendations = []

        for job_id, job_name in zip(self.jobs['job_id'], self.jobs['job_name']):
            job_skills = self.job_skills[self.job_skills['job_id'] == job_id]
            if job_skills.empty:
                job_recommendations.append(self._format_recommendation(job_id, job_name, 0, [], 0, 0))
                continue

            total_weight = job_skills['skill_weight'].sum()
            matches = job_skills.merge(candidate_skills, on='skill_name', suffixes=('_job', '_cand'))
            matches['match_points'] = tf.minimum(matches['skill_weight_job'], matches['skill_weight_cand']).numpy()
            matches['match_points'] *= matches.apply(lambda x: self._compute_name_similarity(x['skill_name'], x['skill_name']), axis=1)

            matched_weight = matches['match_points'].sum()
            percentage_match = round((matched_weight / total_weight) * 100, 2) if total_weight > 0 else 0.0

            job_recommendations.append(self._format_recommendation(
                job_id, job_name, percentage_match, matches[['skill_name', 'match_points']].to_dict('records'),
                len(job_skills), len(matches)
            ))

        return pd.DataFrame(job_recommendations).sort_values('match_percentage', ascending=False).head(10)

    def _compute_name_similarity(self, name1, name2):
        return SequenceMatcher(None, name1.lower().strip(), name2.lower().strip()).ratio()

    def _format_recommendation(self, job_id, job_name, match_percentage, matched_skills, total_required_skills, matched_skills_count):
        return {
            'job_id': job_id,
            'job_name': job_name,
            'match_percentage': match_percentage,
            'matched_skills': matched_skills,
            'total_required_skills': total_required_skills,
            'matched_skills_count': matched_skills_count
        }

    def predict_job_matches(self, candidate_id):
        self.load_data()
        return self.compute_skill_match(candidate_id)

    def save_model(self, model_path="../models/job_recommendation_model"):
        input_layer = tf.keras.layers.Input(shape=(1,), name="input_candidate_skills")
        output_layer = tf.keras.layers.Dense(1, activation='linear', name="output_job_recommendations")(input_layer)
        model = tf.keras.Model(inputs=input_layer, outputs=output_layer)

        model.compile(optimizer='adam', loss='mse')

        # Khởi tạo các biến trọng số
        dummy_input = tf.constant([[0.0]], dtype=tf.float32)  # Dữ liệu giả
        model(dummy_input)  # Chạy một lần để khởi tạo các biến

        # Định nghĩa signature
        @tf.function(input_signature=[tf.TensorSpec(shape=(None, 1), dtype=tf.float32)])
        def serve(input_candidate_skills):
            output = model(input_candidate_skills)
            return {"output_job_recommendations": output}

        # Lưu mô hình
        tf.saved_model.save(
            model,
            model_path,
            signatures={"serving_default": serve}
        )
        print(f"Model saved at {model_path}")

    def close_connection(self):
        self.connection.close()

# Usage example
if __name__ == '__main__':
    recommender = JobRecommendationModel(
        host='localhost',
        user='root',
        password='sapassword',
        database='works'
    )

    candidate_id = 1020  # Replace with actual candidate ID
    recommendations = recommender.predict_job_matches(candidate_id)
    print(recommendations[['job_id', 'job_name', 'match_percentage', 'matched_skills', 'total_required_skills', 'matched_skills_count']])

    # Save the model
    recommender.save_model()

    model_path = "../models/job_recommendation_model"
    model = tf.saved_model.load(model_path)

    print("Inputs:", model.signatures['serving_default'].structured_input_signature)
    print("Outputs:", model.signatures['serving_default'].structured_outputs)

    recommender.close_connection()
