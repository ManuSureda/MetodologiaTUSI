package edu.utn.Controller;


import edu.utn.Dto.LoginDto;
import edu.utn.Dto.LoginResponseDto;
import edu.utn.Exceptions.UserNotExistsException;
import edu.utn.Model.User;
import edu.utn.Utils.ProjetUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    List<User> users;

    @Autowired
    public LoginController() {

        this.users = ProjetUtils.generateUsers();

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public ResponseEntity login (@RequestBody LoginDto loginDto ) throws UserNotExistsException {

        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        User u = users.stream()
                .filter(user -> (user.getUsername().equals(username)) && user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(UserNotExistsException::new);

        LoginResponseDto responseBody = LoginResponseDto.builder()
                .autorization(String.valueOf(u.getId()))
                .build();


        return ResponseEntity.ok().body(responseBody);
    }


    public boolean userExists(Integer id){
        User u = this.users.stream()
                .filter(user -> (user.getId() == id))
                .findFirst()
                .orElse(null);

        return (u != null);
    }
}
