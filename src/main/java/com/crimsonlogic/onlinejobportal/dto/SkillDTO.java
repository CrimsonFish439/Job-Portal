package com.crimsonlogic.onlinejobportal.dto;

public class SkillDTO {
    private String skillId;
    private String skillName;

    public SkillDTO(String skillId, String skillName) {
        this.skillId = skillId;
        this.skillName = skillName;
    }

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
