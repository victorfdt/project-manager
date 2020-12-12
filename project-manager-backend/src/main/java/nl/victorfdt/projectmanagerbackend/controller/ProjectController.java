package nl.victorfdt.projectmanagerbackend.controller;

import nl.victorfdt.projectmanagerbackend.entity.Project;
import nl.victorfdt.projectmanagerbackend.service.MapValidationErrorService;
import nl.victorfdt.projectmanagerbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    /**
     * Adds a new project.
     * The @Valid annotations returns the result of the validation that was defined at the entity.
     * The BindingResult checks the validation and returns the errors.
     *
     * @param project The provided project
     * @param result It contains the validation results.
     * @return ResponseEntity with the result of the request.
     */
    @PostMapping("/project")
    public ResponseEntity<?> addProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> responseEntity = mapValidationErrorService.map(result);

        if (responseEntity != null) {
            return responseEntity;
        }
        
        projectService.save(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }
}
