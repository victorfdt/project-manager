package nl.victorfdt.projectmanagerbackend.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> map(BindingResult result) {
        if (result.hasErrors()) {

            //Map the fields and their errors
            var errorMap = new HashMap<String, String>();

            // Iterate over the errors and put them into the map
            result.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
