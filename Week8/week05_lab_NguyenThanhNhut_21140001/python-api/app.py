from flask import Flask, jsonify, request
import tensorflow as tf
from models.job_recommendation_model import JobRecommendationModel

app = Flask(__name__)

MODEL_PATH = 'models/job_recommendation_model'

# Tải mô hình đã lưu khi server khởi động
model = tf.saved_model.load(MODEL_PATH)
predict_fn = model.signatures['serving_default']

@app.route('/recommendations/<int:candidate_id>', methods=['GET'])
def get_recommendations(candidate_id):
    try:
        recommender = JobRecommendationModel(
            host='localhost',
            user='root',
            password='sapassword',
            database='works'
        )

        recommendations = recommender.predict_job_matches(candidate_id)

        result = recommendations[['job_id', 'job_name', 'match_percentage']].to_dict(orient='records')

        # Đóng kết nối sau khi hoàn thành
        recommender.close_connection()

        return jsonify({"recommendations": result}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
