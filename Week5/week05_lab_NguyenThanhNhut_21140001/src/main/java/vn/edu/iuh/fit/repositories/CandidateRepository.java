/*
 * @ (#) CandidateRepository.java       1.0     15/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 15/10/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.entity.Candidate;

import java.util.List;

public interface CandidateRepository {
    Candidate findById(int id);
    Candidate save(Candidate candidate);
    Candidate update(Candidate candidate);
    boolean delete(int id);
    List<Candidate> findAll();
}
