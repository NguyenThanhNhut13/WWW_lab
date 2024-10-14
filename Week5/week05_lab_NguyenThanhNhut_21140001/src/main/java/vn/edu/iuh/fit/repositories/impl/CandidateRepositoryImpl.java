/*
 * @ (#) CandidateRepositoryImpl.java       1.0     15/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 15/10/2024
 * @version:    1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.entity.Candidate;
import vn.edu.iuh.fit.repositories.CandidateRepository;
import vn.edu.iuh.fit.repositories.mapper.CandidateMapper;
import vn.edu.iuh.fit.util.AppUtils;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public CandidateRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Candidate findById(int id) {
        String sql = "SELECT * FROM candidate WHERE candidate_id = ?";
        return jdbcTemplate.queryForObject(sql, new CandidateMapper(), id);
    }

    @Override
    public Candidate save(Candidate candidate) {
        try {
            String sql = "INSERT INTO candidate(first_name, middle_name, last_name, email, phone, address, dob) VALUES(?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, candidate.getFirstName(), candidate.getMiddleName(), candidate.getLastName(), candidate.getEmail(), candidate.getPhone(), candidate.getAddress(), candidate.getDob());
            return candidate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Candidate update(Candidate candidate) {
        String sql = "UPDATE candidate SET first_name = ?, middle_name = ?, last_name = ?, email = ?, phone = ?, address = ?, dob = ? WHERE candidate_id = ?";
        jdbcTemplate.update(sql, candidate.getFirstName(), candidate.getMiddleName(), candidate.getLastName(), candidate.getEmail(), candidate.getPhone(), candidate.getAddress(), candidate.getDob(), candidate.getCandidate_id());
        return candidate;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM candidate WHERE candidate_id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0;
    }

    @Override
    public List<Candidate> findAll() {
        String sql = "SELECT * FROM candidate";
        return jdbcTemplate.query(sql, new CandidateMapper());
    }

}
