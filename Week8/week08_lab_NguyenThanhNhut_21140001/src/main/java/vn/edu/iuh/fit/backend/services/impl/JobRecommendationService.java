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
import org.tensorflow.*;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TFloat32;
import vn.edu.iuh.fit.backend.models.CandidateSkill;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;

import java.util.*;


@Service
public class JobRecommendationService {
    private final CandidateSkillRepository candidateSkillRepository;
    private final JobSkillRepository jobSkillRepository;
    private final SavedModelBundle model;

    public JobRecommendationService(CandidateSkillRepository candidateSkillRepository,
                                    JobSkillRepository jobSkillRepository) {
        this.candidateSkillRepository = candidateSkillRepository;
        this.jobSkillRepository = jobSkillRepository;

        // Load TensorFlow model
        String modelPath = "src/main/resources/models/job_recommendation_model";
        this.model = SavedModelBundle.loader(modelPath).withTags("serve").load();
    }

    public List<Map<String, Object>> recommendJobs(Long candidateId) {
        // Lấy kỹ năng ứng viên
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findByCandidate_Id(candidateId);
        if (candidateSkills.isEmpty()) {
            throw new IllegalArgumentException("Candidate not found or no skills available.");
        }

        // Lấy danh sách công việc và kỹ năng liên quan
        List<JobSkill> allJobSkills = jobSkillRepository.findAll();

        // Ghép kỹ năng công việc với ứng viên và chuẩn bị tensor đầu vào
        List<Map<String, Object>> inputData = prepareInputData(candidateSkills, allJobSkills);

        // Gọi TensorFlow model
        return callModel(inputData);
    }

    private List<Map<String, Object>> prepareInputData(List<CandidateSkill> candidateSkills, List<JobSkill> jobSkills) {
        List<Map<String, Object>> inputData = new ArrayList<>();

        for (JobSkill jobSkill : jobSkills) {
            Map<String, Object> jobData = new HashMap<>();
            int matchPoints = 0;

            for (CandidateSkill candidateSkill : candidateSkills) {
                if (candidateSkill.getSkill().getSkillName().equalsIgnoreCase(jobSkill.getSkill().getSkillName())) {
                    // Tính điểm dựa trên mức độ kỹ năng
                    matchPoints += Math.min(candidateSkill.getSkillLevel().ordinal(), jobSkill.getSkillLevel().ordinal());
                }
            }

            jobData.put("jobId", jobSkill.getJob().getId());
            jobData.put("matchPoints", matchPoints);
            inputData.add(jobData);
        }

        return inputData;
    }

    private List<Map<String, Object>> callModel(List<Map<String, Object>> inputData) {
        List<Map<String, Object>> recommendations = new ArrayList<>();

        // Chuyển đổi dữ liệu đầu vào thành mảng float[][]
        float[][] inputArray = inputData.stream()
                .map(data -> new float[]{((Number) data.get("matchPoints")).floatValue()})
                .toArray(float[][]::new);

        System.out.println("Available operations:");
        for (Iterator<GraphOperation> it = model.graph().operations(); it.hasNext(); ) {
            Operation op = it.next();
            System.out.println(op.name());
        }

        // Tạo tensor đầu vào
        try (TFloat32 inputTensor = TFloat32.tensorOf(StdArrays.ndCopyOf(inputArray))) {
            // Gọi TensorFlow model
            try (Result result = model.session()
                    .runner()
                    .feed("serving_default_input_candidate_skills", inputTensor) // Tên chính xác
                    .fetch("StatefulPartitionedCall")                           // Tên đầu ra
                    .run()) {

                // Xử lý tensor đầu ra
                try (TFloat32 resultTensor = (TFloat32) result.get(0)) {
                    for (int i = 0; i < resultTensor.shape().size(0); i++) {
                        Map<String, Object> recommendation = new HashMap<>();
                        recommendation.put("jobId", (long) resultTensor.getFloat(i, 0));
                        recommendation.put("matchPercentage", resultTensor.getFloat(i, 1));
                        recommendations.add(recommendation);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recommendations;
    }



}