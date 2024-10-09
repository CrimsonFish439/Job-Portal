package com.crimsonlogic.onlinejobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.onlinejobportal.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {
	Optional<Skill> findBySkillId(String skillId);

	Optional<Skill> findBySkillName(String skillName);
}
