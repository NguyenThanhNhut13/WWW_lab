/*
 * @ (#) Experience.java       1.0     03/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 03/11/2024
 * @version:    1.0
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "company_name")
    private String companyName;

    private String role;

    @Column(name = "work_description", length = 2000)
    private String workDescription;
}
