package com.portfolio.backend.service;

import com.portfolio.backend.entity.Project;
import com.portfolio.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // GET all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // GET project by ID
    public Project getProjectById(UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // POST a new project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // PUT update an existing project
    public Project updateProject(UUID id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setTitle(projectDetails.getTitle());
        project.setDescription(projectDetails.getDescription());
        project.setHighlights(projectDetails.getHighlights());
        project.setImage(projectDetails.getImage());

        return projectRepository.save(project);
    }

    // DELETE project
    public void deleteProject(UUID id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.delete(project);
    }
}

