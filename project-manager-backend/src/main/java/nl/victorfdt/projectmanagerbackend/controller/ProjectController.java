package nl.victorfdt.projectmanagerbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.victorfdt.projectmanagerbackend.converter.DozerConverter;
import nl.victorfdt.projectmanagerbackend.data.entity.Project;
import nl.victorfdt.projectmanagerbackend.data.vo.ProjectVO;
import nl.victorfdt.projectmanagerbackend.service.MapValidationErrorService;
import nl.victorfdt.projectmanagerbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Project", description = "API from the Project")
@RestController
@RequestMapping("/api/project/v1")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Qualifier("mvcValidator")
    @Autowired
    Validator validator;

    /**
     * Adds a new project.
     * The @Valid annotations returns the result of the validation that was defined at the entity.
     * The BindingResult checks the validation and returns the errors.
     *
     * @param projectVO The provided project
     * @param result    It contains the validation results.
     * @return ResponseEntity with the result of the request.
     */
    @Operation(method = "Test", description = "Add a new Project")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody ProjectVO projectVO, BindingResult result) {
        // Because I receive a VO and not the Entity, it requires to call the Validation manually.
        validator.validate(DozerConverter.parse(projectVO, Project.class), result);

        ResponseEntity<?> responseEntity = mapValidationErrorService.map(result);

        if (responseEntity != null) {
            return responseEntity;
        }

        projectService.add(projectVO);

        // HATEOAS - Adding a link in the response
        projectVO.add(linkTo(ProjectController.class).slash(projectVO.getIdentifier()).withSelfRel());

        return new ResponseEntity<>(projectVO, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody ProjectVO projectVO, BindingResult result) {
        // Because I receive a VO and not the Entity, it requires to call the Validation manually.
        validator.validate(DozerConverter.parse(projectVO, Project.class), result);
        ResponseEntity<?> responseEntity = mapValidationErrorService.map(result);

        if (responseEntity != null) {
            return responseEntity;
        }

        projectService.update(projectVO);
        projectVO.add(linkTo(ProjectController.class).slash(projectVO.getIdentifier()).withSelfRel());

        return new ResponseEntity<>(projectVO, HttpStatus.OK);
    }

    /**
     * Returns a Product by the given product identifier.
     * The name of the variable at the @GetMapping and at the method's parameter MUST BE EXACTLY the same.
     *
     * @param identifier - The identifier from the project
     * @return ResponseEntity with the object
     */
    @GetMapping("{identifier}")
    public ResponseEntity<?> getByIdentifier(@PathVariable String identifier) {
        ProjectVO projectVO = projectService.findByIdentifier(identifier);
        projectVO.add(linkTo(ProjectController.class).slash(projectVO.getIdentifier()).withSelfRel());

        return new ResponseEntity<>(projectVO, HttpStatus.OK);
    }

    @DeleteMapping("{identifier}")
    public ResponseEntity<?> deleteByIdentifier(@PathVariable String identifier) {
        projectService.deleteByIdentifier(identifier);
        return new ResponseEntity<>(String.format("The project with identifier '%s' was deleted", identifier.toUpperCase()), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllProducts() {
        var listProducts = projectService.findAll();
        listProducts.forEach(vo -> vo.add(linkTo(methodOn(ProjectController.class).getByIdentifier(vo.getIdentifier())).withSelfRel()));
        return new ResponseEntity<>(listProducts, HttpStatus.CREATED);
    }
}
