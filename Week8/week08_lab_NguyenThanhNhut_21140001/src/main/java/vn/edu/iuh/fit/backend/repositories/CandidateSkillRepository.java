/*
 * @ (#) CandidateSkillRepository.java       1.0     03/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 03/11/2024
 * @version:    1.0
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.CandidateSkill;

import java.util.List;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
    CandidateSkill findByCandidate_IdAndSkill_Id(Long candidateId, Long skillId);
    List<CandidateSkill> findByCandidate_Id(Long candidateId);
}
