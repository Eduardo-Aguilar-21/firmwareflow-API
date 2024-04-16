package com.ast.firmwareflow.services;

import com.ast.firmwareflow.models.ProjectsModel;
import com.ast.firmwareflow.repositories.ProjectsRepository;
import com.ast.firmwareflow.utils.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private DirectoryService directoryService;

    public List<ProjectsModel> findAll() {
        return projectsRepository.findAll();
    }

    public Optional<ProjectsModel> findById(Long id) {
        return projectsRepository.findById(id);
    }

    public ProjectsModel saveProject(ProjectsModel projectModel) {
        ProjectsModel savedProject = projectsRepository.save(projectModel);
        directoryService.createDirectoryWithName(savedProject.getName());

        return savedProject;
    }

    public ProjectsModel updateProject(Long id, ProjectsModel updatedProject) {
        if (projectsRepository.existsById(id)) {
            updatedProject.setId(id);
            return projectsRepository.save(updatedProject);
        }
        return null;
    }

    public void deleteProject(Long id) {
        projectsRepository.deleteById(id);
    }
}
