package com.ast.firmwareflow.controllers;

import com.ast.firmwareflow.models.ProjectsModel;
import com.ast.firmwareflow.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/projects")
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;

    @GetMapping("/projects-view")
    public String showProjectsView(Model model) {
        List<ProjectsModel> projects = projectsService.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/add-project-view")
    public String showAddProjectView() {
        return "add-project";
    }

    @GetMapping("/edit-project-view/{id}")
    public String showEditProject(@PathVariable Long id, Model model) {
        // Aquí deberías cargar los datos del proyecto con el ID proporcionado
        Optional<ProjectsModel> optionalProject = projectsService.findById(id);

        // Verificar si se encontró el proyecto
        if (optionalProject.isPresent()) {
            ProjectsModel project = optionalProject.get();
            // Agregar el proyecto al modelo para que pueda ser utilizado en la página
            model.addAttribute("project", project);
            return "edit-project";
        } else {
            // Si no se encuentra el proyecto, puedes redirigir a una página de error o manejarlo según tus necesidades
            return "redirect:/error";
        }
    }

    @PostMapping("/add-project")
    public String addProject(@ModelAttribute ProjectsModel projectModel) {
        projectsService.saveProject(projectModel);
        return "redirect:/api/projects/projects-view";
    }

    @PutMapping("/edit-project")
    public String editProject(@ModelAttribute ProjectsModel projectModel) {
        projectsService.updateProject(projectModel.getId(), projectModel);
        return "redirect:/api/projects/projects-view";
    }


    @GetMapping
    public ResponseEntity<List<ProjectsModel>> getAllProjects() {
        List<ProjectsModel> projects = projectsService.findAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectsModel> getProjectById(@PathVariable Long id) {
        Optional<ProjectsModel> project = projectsService.findById(id);
        return project.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProjectsModel> createProject(@RequestBody ProjectsModel projectModel) {
        ProjectsModel savedProject = projectsService.saveProject(projectModel);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectsModel> updateProject(@PathVariable Long id, @RequestBody ProjectsModel updatedProject) {
        ProjectsModel project = projectsService.updateProject(id, updatedProject);
        if (project != null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectsService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
