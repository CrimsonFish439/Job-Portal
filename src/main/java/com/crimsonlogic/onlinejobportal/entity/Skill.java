package com.crimsonlogic.onlinejobportal.entity;

import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Skill entity represents a specific skill that a candidate may possess or a job may require.
 * Skills are linked to both candidates and jobs through the CandidateSkill and JobSkill entities.
 */
@Entity
@Table(name = "skills")
public class Skill {

    /**
     * The unique identifier for each skill, generated with the prefix "SKL".
     */
    @Id
    @Column(name = "skill_id")
    private String skillId = IDGenerator.generateID("SKL");

    /**
     * The name of the skill (e.g., Java, Python, Marketing).
     * This field is unique to prevent duplicate entries of the same skill.
     */
    @Column(name = "skill_name", unique = true)
    private String skillName;

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
    
    
}
