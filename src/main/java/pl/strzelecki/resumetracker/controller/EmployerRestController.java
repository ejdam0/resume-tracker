package pl.strzelecki.resumetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Employer>> findAll() {
        return new ResponseEntity<>(employerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{employerId}")
    public ResponseEntity<Employer> findById(@PathVariable("employerId") long theId) {
        return new ResponseEntity<>(employerService.findById(theId), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveEmployer(@RequestBody Employer employer) {
        // set id to 0, to force save
        employer.setId(0L);
        employerService.save(employer);
        return new ResponseEntity<>("Saved new employer:\n " + employer, HttpStatus.OK);
    }

    @PutMapping("/all")
    public ResponseEntity<Employer> updateEmployer(@RequestBody Employer employer) {
        employerService.save(employer);
        return new ResponseEntity<>(employer, HttpStatus.OK);
    }

    @DeleteMapping("/{employerId}")
    public ResponseEntity<?> deleteById(@PathVariable("employerId") long theId) {
        employerService.deleteById(theId);
        return new ResponseEntity<>("Deleted employer with id: " + theId, HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        employerService.deleteAll();
        return new ResponseEntity<>("Deleted all employers in the database.", HttpStatus.OK);
    }
}
