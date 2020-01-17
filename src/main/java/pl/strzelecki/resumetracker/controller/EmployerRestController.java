package pl.strzelecki.resumetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.strzelecki.resumetracker.entity.Employer;
import pl.strzelecki.resumetracker.service.EmployerService;

import java.util.List;

@RestController
@RequestMapping("/employers")
public class EmployerRestController {

    private EmployerService employerService;

    @Autowired
    public EmployerRestController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/all")
    public List<Employer> findAll() {
        return employerService.findAll();
    }

    @GetMapping("/{employerId}")
    public Employer findById(@PathVariable("employerId") long theId) {
        return employerService.findById(theId);
    }

    @PostMapping("/all")
    public Employer saveEmployer(@RequestBody Employer employer) {
        // set id to 0, to force save
        employer.setId(0L);
        employerService.save(employer);
        return employer;
    }

    @PutMapping("/all")
    public Employer updateEmployer(@RequestBody Employer employer) {
        employerService.save(employer);
        return employer;
    }

    @DeleteMapping("/{employerId}")
    public String deleteById(@PathVariable("employerId") long theId) {
        employerService.deleteById(theId);
        return "Deleted employer";
    }

    @DeleteMapping("/all")
    public String deleteAll() {
        employerService.deleteAll();
        return "Deleted all employers";
    }
}
