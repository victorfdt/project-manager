package nl.victorfdt.projectmanagerbackend.controller;

import nl.victorfdt.projectmanagerbackend.entity.Project;
import nl.victorfdt.projectmanagerbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        projectService.save(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }
}
