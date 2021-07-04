package flore.cristi.project.controller;

import flore.cristi.project.model.entity.UID;
import flore.cristi.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/authenticate/{uid}")
    public UID login(@PathVariable("uid") String uid) throws InterruptedException {
        return userService.login(uid);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/users")
    public List<UID> getAllUsers() throws InterruptedException {
        return userService.getAllUsers();
    }

}
