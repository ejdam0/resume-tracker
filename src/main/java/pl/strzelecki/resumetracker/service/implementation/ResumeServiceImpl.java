package pl.strzelecki.resumetracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.strzelecki.resumetracker.entity.Employer;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;
import pl.strzelecki.resumetracker.service.ResumeService;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.EmployerInDatabaseChecker;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.SingleDuplicateResumeFinder;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    private ResumeRepository resumeRepository;
    private EmployerInDatabaseChecker employerInDatabaseChecker;
    private SingleDuplicateResumeFinder singleDuplicateResumeFinder;

    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository,
                             EmployerInDatabaseChecker employerInDatabaseChecker,
                             SingleDuplicateResumeFinder singleDuplicateResumeFinder) {
        this.resumeRepository = resumeRepository;
        this.employerInDatabaseChecker = employerInDatabaseChecker;
        this.singleDuplicateResumeFinder = singleDuplicateResumeFinder;
    }

    @Override
    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    @Override
    public Resume findById(long theId) {
        Optional<Resume> result = resumeRepository.findById(theId);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume with id: " + theId + " not found.");
        }
        return result.get();
    }

    @Override
    public void save(Resume resume) {
        // check if employer exists in db
        Employer employer = employerInDatabaseChecker.searchForEmployer(resume.getEmployerId().getName());
        resume.setEmployerId(employer);
        // check if resume exists in db
        if (singleDuplicateResumeFinder.checkIfResumeExistsInDb(resume)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This resume already exists in the database!");
        } else {
            resumeRepository.save(resume);
        }
    }

    @Override
    public void deleteById(long theId) {
        Optional<Resume> result = resumeRepository.findById(theId);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume with id: " + theId + " not found.");
        }
        resumeRepository.deleteById(theId);
    }

    @Override
    public void deleteAll() {
        resumeRepository.deleteAll();
    }
}
