package pl.strzelecki.resumetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.strzelecki.resumetracker.entity.Authorization;
import pl.strzelecki.resumetracker.service.AuthorizationService;

import java.util.List;

@RestController
@RequestMapping("/authorizations")
public class AuthorizationRestController {

    private AuthorizationService authorizationService;

    @Autowired
    public AuthorizationRestController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/all")
    public List<Authorization> findAll() {
        return authorizationService.findAll();
    }

    @GetMapping("/{authorizationId}")
    public Authorization findById(@PathVariable("authorizationId") long theId) {
        return authorizationService.findById(theId);
    }

    @PostMapping("/all")
    public Authorization save(@RequestBody Authorization authorization) {
        // set id to 0, to force save
        authorization.setId(0L);
        authorizationService.save(authorization);
        return authorization;
    }

    @PutMapping("/all")
    public Authorization update(@RequestBody Authorization authorization) {
        authorizationService.save(authorization);
        return authorization;
    }

    @DeleteMapping("/{authorizationId}")
    public String deleteById(@PathVariable("authorizationId") long theId) {
        authorizationService.deleteById(theId);
        return "Deleted one";
    }

    @DeleteMapping("/all")
    public String deleteAll() {
        authorizationService.deleteAll();
        return "Deleted all";
    }
}
