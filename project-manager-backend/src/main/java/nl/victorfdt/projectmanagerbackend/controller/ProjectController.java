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

@RestController
@RequestMapping("/api/project")
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
     * @param result  It contains the validation results.
     * @return ResponseEntity with the result of the request.
     */
    @PostMapping("")
    public ResponseEntity<?> add(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> responseEntity = mapValidationErrorService.map(result);

        if (responseEntity != null) {
            return responseEntity;
        }

        projectService.add(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> responseEntity = mapValidationErrorService.map(result);

        if (responseEntity != null) {
            return responseEntity;
        }

        projectService.update(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    /**
     * Returns a Product by the given product identifier.
     * The name of the variable at the @GetMapping and at the method's parameter MUST BE EXACTLY the same.
     *
     * @param identifier - The identifier from the project
     * @return ResponseEntity with the object
     */
    @GetMapping("/{identifier}")
    public ResponseEntity<?> getByIdentifier(@PathVariable String identifier) {
        Project project = projectService.findByIdentifier(identifier);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<?> deleteByIdentifier(@PathVariable String identifier) {
        projectService.deleteByIdentifier(identifier);
        return new ResponseEntity<>(String.format("The project with identifier '%s' was deleted", identifier.toUpperCase()), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllProducts() {
        var listProducts = projectService.findAll();

        return new ResponseEntity<>(listProducts, HttpStatus.CREATED);
    }
}
