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
    public ResponseEntity<List<Resume>> findAll() {
        return new ResponseEntity<>(resumeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<Resume> findById(@PathVariable("resumeId") long theId) {
        return new ResponseEntity<>(resumeService.findById(theId), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<?> save(@RequestBody Resume resume) {
        // set id to 0, to force save
        resume.setId(0L);
        resumeService.save(resume);
        return new ResponseEntity<>("Saved new resume:\n " + resume, HttpStatus.OK);
    }

    @PutMapping("/all")
    public ResponseEntity<Resume> update(@RequestBody Resume resume) {
        resumeService.save(resume);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<?> deleteById(@PathVariable("resumeId") long theId) {
        resumeService.deleteById(theId);
        return new ResponseEntity<>("Deleted resume with id: " + theId, HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        resumeService.deleteAll();
        return new ResponseEntity<>("Deleted all resumes in the database.", HttpStatus.OK);
    }
}
