package pl.strzelecki.resumetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.service.ResumeService;
import pl.strzelecki.resumetracker.uploadData.csv.SaveCSVToDatabaseService;
import pl.strzelecki.resumetracker.uploadData.xls.SaveXLSToDatabaseService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/resumes")
public class ResumeRestController {

    private ResumeService resumeService;
    private SaveCSVToDatabaseService saveCSVToDatabaseService;
    private SaveXLSToDatabaseService saveXLSToDatabaseService;

    @Autowired
    public ResumeRestController(ResumeService resumeService,
                                SaveCSVToDatabaseService saveCSVToDatabaseService,
                                SaveXLSToDatabaseService saveXLSToDatabaseService) {
        this.resumeService = resumeService;
        this.saveCSVToDatabaseService = saveCSVToDatabaseService;
        this.saveXLSToDatabaseService = saveXLSToDatabaseService;
    }

    @PostMapping("/uploadData")
    public ResponseEntity<?> uploadCsvFile(@RequestBody String data) {
        if (data.isEmpty()) {
            return new ResponseEntity<>("No data added!", HttpStatus.NO_CONTENT);
        }
        try {
            if (saveCSVToDatabaseService.saveDataToDatabase(data)) {
                return new ResponseEntity<>("Successfully added data!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No data added!", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadXlsFile(@RequestParam("file") MultipartFile uploadedFile) {
        if (uploadedFile.isEmpty()) {
            return new ResponseEntity<>("Select a file!", HttpStatus.NO_CONTENT);
        }
        try {
            if (saveXLSToDatabaseService.saveDataToDatabase(uploadedFile)) {
                return new ResponseEntity<>("Successfully uploaded - " +
                        uploadedFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No data added!", HttpStatus.UNPROCESSABLE_ENTITY);
            }

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
