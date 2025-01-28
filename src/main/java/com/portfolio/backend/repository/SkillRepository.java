package com.portfolio.backend.repository;

import com.portfolio.backend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
}
