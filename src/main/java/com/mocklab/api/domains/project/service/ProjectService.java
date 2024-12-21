package com.mocklab.api.domains.project.service;

import com.mocklab.api.adapter.input.dto.RequestNewProject;
import com.mocklab.api.domains.project.entities.Project;
import com.mocklab.api.domains.project.repositories.ProjectRepository;
import com.mocklab.api.shared.utils.DataBaseFunctions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProjectService {

    private final ProjectRepository repository;
    private final DataBaseFunctions dbFunctions;

    public ProjectService(ProjectRepository repository, DataBaseFunctions dbf){
        this.repository = repository;
        this.dbFunctions = dbf;
    }

    public Project saveNew(RequestNewProject newProject){
        try{
            Project project = new Project();
            project.setName(newProject.name());
            project.setObservation(newProject.observation());
            project.setDescription(newProject.description());
            project.setIdOrganization(UUID.fromString(newProject.idorganization()));
            project.setIdSequence(dbFunctions.nextProjectOnOrganization(project.getIdOrganization()));
            return this.repository.save(project);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
