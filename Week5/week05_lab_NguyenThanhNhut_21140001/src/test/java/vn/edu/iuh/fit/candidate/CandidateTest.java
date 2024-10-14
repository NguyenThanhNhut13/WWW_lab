/*
 * @ (#) CondidateTest.java       1.0     15/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.candidate;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 15/10/2024
 * @version:    1.0
 */

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.entity.Candidate;
import vn.edu.iuh.fit.repositories.CandidateRepository;

import java.sql.Date;

@SpringBootTest
public class CandidateTest {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateTest(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Test
    void findById() {
        Candidate candidate = candidateRepository.findById(1);
        assert candidate != null;
    }

    @Test
    void testSave() {
        Candidate candidate = Candidate.builder()
                .fullName("Nguyen Van A")
                .email("nguyenvana@gmail.com")
                .phone("0123456789")
                .address("Ho Chi Minh")
                .dob(Date.valueOf("1999-10-15"))
                .build();
        Candidate insertedCandidate = candidateRepository.save(candidate);
        assert insertedCandidate != null;
    }

    @Test
    void testUpdate() {
        Candidate candidate = Candidate.builder()
                .candidate_id(8)
                .fullName("Nguyen Van B")
                .email("nguyenvanb@gmail.com")
                .phone("0123456789")
                .address("Ho Chi Minh")
                .dob(Date.valueOf("1999-10-15"))
                .build();
        Candidate updatedCandidate = candidateRepository.update(candidate);

        assert candidateRepository.findById(8).getFullName().equals("Nguyen Van B");
    }

    @Test
    void testDelete() {
        boolean result = candidateRepository.delete(8);
        assert result;
    }

    @Test
    void testFindAll() {
        assert candidateRepository.findAll().size() == 2;
    }
}
