package com.portfolio.backend.controller;

import com.portfolio.backend.entity.Project;
import com.portfolio.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // GET all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // GET project by ID
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable UUID id) {
        return projectService.getProjectById(id);
    }

    // POST create a new project
    @PostMapping
    public Project createProject(@RequestParam String title,
                                 @RequestParam String description,
                                 @RequestParam List<String> highlights,
                                 @RequestParam("image") MultipartFile imageFile) throws IOException {

        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setHighlights(highlights);
        project.setImage(imageFile.getBytes());

        return projectService.createProject(project);
    }

    // PUT update an existing project
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable UUID id,
                                 @RequestParam String title,
                                 @RequestParam String description,
                                 @RequestParam List<String> highlights,
                                 @RequestParam("image") MultipartFile imageFile) throws IOException {

        Project projectDetails = new Project();
        projectDetails.setTitle(title);
        projectDetails.setDescription(description);
        projectDetails.setHighlights(highlights);
        projectDetails.setImage(imageFile.getBytes());

        return projectService.updateProject(id, projectDetails);
    }

    // DELETE project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
