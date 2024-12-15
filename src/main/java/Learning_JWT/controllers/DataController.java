package Learning_JWT.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @GetMapping("/confidential")
    public ResponseEntity<?> getConfidentialData(){
        return new ResponseEntity<>(
                "This is confidential data",
                HttpStatus.OK
        );
    }
}
