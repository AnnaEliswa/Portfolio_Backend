
package com.portfolio.backend.repository;

import com.portfolio.backend.entity.AboutMe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AboutMeRepository extends JpaRepository<AboutMe, UUID> {
}
