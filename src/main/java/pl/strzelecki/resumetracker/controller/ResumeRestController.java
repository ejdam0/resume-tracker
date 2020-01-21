package pl.strzelecki.resumetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.strzelecki.resumetracker.csv.service.SaveToDatabaseService;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.service.ResumeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/resumes")
public class ResumeRestController {

    private ResumeService resumeService;
    private SaveToDatabaseService saveToDatabaseService;

    @Autowired
    public ResumeRestController(ResumeService resumeService, SaveToDatabaseService saveToDatabaseService) {
        this.resumeService = resumeService;
        this.saveToDatabaseService = saveToDatabaseService;
    }

    @PostMapping("/uploadData")
    public String uploadCsvFile(@RequestBody String data) throws IOException {
        if (saveToDatabaseService.saveDataToDatabase(data)) {
            return "Saving resumes...";
        } else {
            return "No resumes saved.";
        }
    }

    @GetMapping("/all")
    public List<Resume> findAll() {
        return resumeService.findAll();
    }

    @GetMapping("/{resumeId}")
    public Resume findById(@PathVariable("resumeId") long theId) {
        return resumeService.findById(theId);
    }

    @PostMapping("/all")
    public Resume save(@RequestBody Resume resume) {
        // set id to 0, to force save
        resume.setId(0L);
        resumeService.save(resume);
        return resume;
    }

    @PutMapping("/all")
    public Resume update(@RequestBody Resume resume) {
        resumeService.save(resume);
        return resume;
    }

    @DeleteMapping("/{resumeId}")
    public String deleteById(@PathVariable("resumeId") long theId) {
        resumeService.deleteById(theId);
        return "Deleted one";
    }

    @DeleteMapping("/all")
    public String deleteAll() {
        resumeService.deleteAll();
        return "Deleted all";
    }
}
