/*
 * @ (#) JobRepository.java       1.0     03/11/2024
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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j.id, j.jobName, COUNT(ja.id) AS applicationCount " +
            "FROM JobApplication ja " +
            "JOIN ja.job j " +
            "WHERE ja.status = 1 " +
            "GROUP BY j.id " +
            "ORDER BY applicationCount DESC")
    List<Object[]> findPopularJobs(Pageable pageable);
    Page<Job> findJobsByCompany_Id(Long companyId, Pageable pageable);
}
