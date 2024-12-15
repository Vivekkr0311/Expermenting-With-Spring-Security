package Learning_JWT.controllers;

import Learning_JWT.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
@Slf4j
public class DataController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth")
    public ResponseEntity<?> getAuthToken(Authentication authentication){
        try{
            return new ResponseEntity<>(
                    jwtUtils.generateToken(authentication.getName()),
                    HttpStatus.CREATED
            );
        }catch (Exception e){
            log.error("Invalid credentials: ", e);
        }
        return new ResponseEntity<>(
                "Invalid credentials",
                HttpStatus.valueOf(401)
        );
    }

    @GetMapping("/confidential")
    public ResponseEntity<?> getConfidentialData(){
        return new ResponseEntity<>(
                "This is confidential data",
                HttpStatus.OK
        );
    }
}
