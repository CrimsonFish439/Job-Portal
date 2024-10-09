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
 * The JobSkill entity represents the skills required for a specific job.
 * It establishes a many-to-many relationship between Job and Skill.
 * This entity acts as a join table between Job and Skill.
 */
@Entity
@Table(name = "job_skills")
public class JobSkill {

    /**
     * The unique identifier for each JobSkill record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * A many-to-one relationship linking the JobSkill to a specific job.
     * Each job can have multiple JobSkill entries.
     */
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    /**
     * A many-to-one relationship linking the JobSkill to a specific skill.
     * Each skill can be associated with multiple jobs.
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
    
}
