package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SkillTest {

    private Skill skill;

    @BeforeEach
    void setUp() {
        skill = new Skill();
        skill.setSkillId("SKL12345");
        skill.setSkillName("Java");
    }

    @Test
    void testGetters() {
        assertEquals("SKL12345", skill.getSkillId());
        assertEquals("Java", skill.getSkillName());
    }

    @Test
    void testSetters() {
        skill.setSkillId("SKL67890");
        skill.setSkillName("Python");

        assertEquals("SKL67890", skill.getSkillId());
        assertEquals("Python", skill.getSkillName());
    }
}
