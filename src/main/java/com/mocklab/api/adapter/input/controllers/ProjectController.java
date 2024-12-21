package com.mocklab.api.adapter.input.controllers;

import com.mocklab.api.adapter.input.dto.RequestNewProject;
import com.mocklab.api.domains.project.entities.Project;
import com.mocklab.api.domains.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service){
        this.service = service;
    }

    @PostMapping("/")
    private ResponseEntity<Project> salvar(@RequestBody RequestNewProject newProject){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveNew(newProject));
    }

}
