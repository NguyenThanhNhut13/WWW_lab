package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.fit.backend.enums.SkillLevel;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "candidate_skill")
public class CandidateSkill {
    @EmbeddedId
    private CandidateSkillId id;

    @MapsId("candidateId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

    @Column(name = "skill_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    @Column(name = "applied_date")
    private LocalDate appliedDate;

}