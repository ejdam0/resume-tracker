package pl.strzelecki.resumetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.strzelecki.resumetracker.entity.User;
import pl.strzelecki.resumetracker.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable("userId") long theId) {
        return userService.findById(theId);
    }

    @PostMapping("/all")
    public User save(@RequestBody User user) {
        // set id to 0, to force save
        user.setId(0L);
        userService.save(user);
        return user;
    }

    @PutMapping("/all")
    public User update(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @DeleteMapping("/{userId}")
    public String deleteById(@PathVariable("userId") long theId) {
        userService.deleteById(theId);
        return "Deleted one";
    }

    @DeleteMapping("/all")
    public String deleteAll() {
        userService.deleteAll();
        return "Deleted all";
    }
}
