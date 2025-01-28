package com.portfolio.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.portfolio.backend.entity.*;
import com.portfolio.backend.repository.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow React app access
public class AboutController {

    private final AboutMeRepository aboutMeRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;

    public AboutController(
        AboutMeRepository aboutMeRepository,
        EducationRepository educationRepository,
        SkillRepository skillRepository
    ) {
        this.aboutMeRepository = aboutMeRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
    }

    // ========== About Me APIs ==========
    @GetMapping("/about")
    public List<AboutMe> getAboutMe() {
        return aboutMeRepository.findAll();
    }

    @PostMapping("/about")
    public AboutMe createAboutMe(@RequestBody AboutMe aboutMe) {
        return aboutMeRepository.save(aboutMe);
    }

    @PutMapping("/about/{id}")
    public ResponseEntity<AboutMe> updateAboutMe(@PathVariable UUID id, @RequestBody AboutMe aboutMeDetails) {
        Optional<AboutMe> aboutMe = aboutMeRepository.findById(id);
        if (aboutMe.isPresent()) {
            AboutMe updatedAboutMe = aboutMe.get();
            updatedAboutMe.setName(aboutMeDetails.getName());
            updatedAboutMe.setDescription(aboutMeDetails.getDescription());
            return ResponseEntity.ok(aboutMeRepository.save(updatedAboutMe));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/about/{id}")
    public ResponseEntity<Void> deleteAboutMe(@PathVariable UUID id) {
        if (aboutMeRepository.existsById(id)) {
            aboutMeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== Education APIs ==========
    @GetMapping("/education")
    public List<Education> getEducation() {
        return educationRepository.findAll();
    }

    @PostMapping("/education")
    public Education createEducation(@RequestBody Education education) {
        return educationRepository.save(education);
    }

    @PutMapping("/education/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable UUID id, @RequestBody Education educationDetails) {
        Optional<Education> education = educationRepository.findById(id);
        if (education.isPresent()) {
            Education updatedEducation = education.get();
            updatedEducation.setDegree(educationDetails.getDegree());
            updatedEducation.setInstitution(educationDetails.getInstitution());
            updatedEducation.setYear(educationDetails.getYear());
            updatedEducation.setCgpa(educationDetails.getCgpa());
            return ResponseEntity.ok(educationRepository.save(updatedEducation));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/education/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable UUID id) {
        if (educationRepository.existsById(id)) {
            educationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== Skills APIs ==========
    @GetMapping("/skills")
    public List<Skill> getSkills() {
        return skillRepository.findAll();
    }

    @PostMapping("/skills")
    public Skill createSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable UUID id, @RequestBody Skill skillDetails) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isPresent()) {
            Skill updatedSkill = skill.get();
            updatedSkill.setName(skillDetails.getName());
            return ResponseEntity.ok(skillRepository.save(updatedSkill));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable UUID id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
