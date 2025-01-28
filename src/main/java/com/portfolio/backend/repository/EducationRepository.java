package com.portfolio.backend.repository;

import com.portfolio.backend.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EducationRepository extends JpaRepository<Education, UUID> {
}
