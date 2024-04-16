package com.ast.firmwareflow.controllers;

import com.ast.firmwareflow.models.ProjectsModel;
import com.ast.firmwareflow.models.VersionsModel;
import com.ast.firmwareflow.services.VersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/versions")
public class VersionsController {
    @Autowired
    private VersionsService versionsService;

    @GetMapping("/versions-view")
    public String showProjectsView(Model model) {
        List<VersionsModel> projects = versionsService.findAll();
        model.addAttribute("versions", projects);
        return "versions";
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<?>> getVersionsByProjectId(@PathVariable("projectId") Long projectId) {
        List<?> versions = versionsService.findByProjectModelId(projectId);
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VersionsModel>> getAllVersions() {
        List<VersionsModel> versions = versionsService.findAll();
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VersionsModel> getVersionById(@PathVariable("id") Long id) {
        Optional<VersionsModel> version = versionsService.findById(id);
        return version.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<VersionsModel> createVersion(@RequestBody VersionsModel version) {
        VersionsModel createdVersion = versionsService.createVersion(version);
        return new ResponseEntity<>(createdVersion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VersionsModel> updateVersion(@PathVariable("id") Long id, @RequestBody VersionsModel updatedVersion) {
        VersionsModel version = versionsService.updateVersion(id, updatedVersion);
        return version != null ? new ResponseEntity<>(version, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVersion(@PathVariable("id") Long id) {
        versionsService.deleteVersion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
