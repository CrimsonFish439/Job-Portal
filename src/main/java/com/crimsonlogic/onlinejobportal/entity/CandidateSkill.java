package com.crimsonlogic.onlinejobportal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The CandidateSkill entity represents the skills possessed by a candidate.
 * It establishes a many-to-many relationship between Candidate and Skill.
 * This entity acts as a join table between Candidate and Skill.
 */
@Entity
@Table(name = "candidate_skills")
public class CandidateSkill {

    /**
     * The unique identifier for each CandidateSkill record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * A many-to-one relationship linking the CandidateSkill to a specific candidate.
     * Each candidate can have multiple CandidateSkill entries.
     */
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    /**
     * A many-to-one relationship linking the CandidateSkill to a specific skill.
     * Each skill can be possessed by multiple candidates.
     */
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
    
    
}
