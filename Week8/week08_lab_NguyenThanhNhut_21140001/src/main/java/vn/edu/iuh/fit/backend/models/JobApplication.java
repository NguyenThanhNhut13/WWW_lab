/*
 * @ (#) JobApplication.java       1.0     17/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.models;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 17/11/2024
 * @version:    1.0
 */

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "job_id", nullable = false)
    @ToString.Exclude
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "candidate_id", nullable = false)
    @ToString.Exclude
    private Candidate candidate;

    @Column(name = "apply_at", nullable = false)
    private LocalDate applyAt;

    // -1: Rejected, 0: Pending, 1: Accepted
    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "match_percentage", nullable = false)
    private double matchPercentage;



}
