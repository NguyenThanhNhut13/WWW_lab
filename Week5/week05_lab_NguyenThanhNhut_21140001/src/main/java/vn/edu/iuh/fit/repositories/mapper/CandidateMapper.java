/*
 * @ (#) CandidateMapper.java       1.0     15/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories.mapper;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 15/10/2024
 * @version:    1.0
 */

import org.springframework.jdbc.core.RowMapper;
import vn.edu.iuh.fit.entity.Candidate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateMapper implements RowMapper<Candidate> {

    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setCandidate_id(rs.getInt("candidate_id"));
        candidate.setFullName(rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name"));
        candidate.setEmail(rs.getString("email"));
        candidate.setPhone(rs.getString("phone"));
        candidate.setAddress(rs.getString("address"));
        candidate.setDob(rs.getDate("dob"));
        return candidate;
    }
}
