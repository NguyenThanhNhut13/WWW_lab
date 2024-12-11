/*
 * @ (#) JobRecommendationService.java       1.0     07/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/12/2024
 * @version:    1.0
 */

import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;


@Service
public class JobRecommendationService {
    private final SavedModelBundle model;

    public JobRecommendationService() {
        // Load the SavedModel
        String modelPath = "D:/IUH/HK7/WWW Programming (Java)/ThucHanh/WWW_lab/Week8/week08_lab_NguyenThanhNhut_21140001/src/main/resources/models/job_recommendation_model";
        this.model = SavedModelBundle.load(modelPath, "serve");
    }

    public float predictSkillMatch(float inputSkillWeight) {
        // Create a TensorFlow session
        try (Session session = model.session()) {
            // Prepare input tensor
            Tensor<Float> inputTensor = Tensor.create(new float[][]{{inputSkillWeight}}, Float.class);

            // Run the model
            Tensor<Float> result = session
                    .runner()
                    .feed("serving_default_input", inputTensor) // Replace with your input layer name
                    .fetch("StatefulPartitionedCall") // Replace with your output layer name
                    .run()
                    .get(0)
                    .expect(Float.class);

            // Extract prediction
            float[][] predictions = new float[1][1];
            result.copyTo(predictions);
            return predictions[0][0];
        }
    }

}