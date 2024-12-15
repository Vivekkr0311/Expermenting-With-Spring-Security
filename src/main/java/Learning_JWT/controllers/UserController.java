package Learning_JWT.controllers;

import Learning_JWT.entities.User;
import Learning_JWT.services.UserServices;
import Learning_JWT.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userServices.createUser(user);
            return new ResponseEntity<>(
                    "User created",
                    HttpStatus.CREATED
            );
        }catch (Exception e){
            log.error("User not created " + e);
        }
        return new ResponseEntity<>(
                "User not created",
                HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllUsers(){
        List<User> allUsers = userServices.findAllUsers();
        if(allUsers != null && !allUsers.isEmpty()){
            return new ResponseEntity<>(
                    allUsers,
                    HttpStatus.FOUND
            );
        }
        return new ResponseEntity<>(
                "No user exists",
                HttpStatus.NO_CONTENT
        );
    }
}
