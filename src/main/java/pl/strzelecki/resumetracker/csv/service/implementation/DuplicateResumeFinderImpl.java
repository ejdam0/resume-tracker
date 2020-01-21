package pl.strzelecki.resumetracker.csv.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.csv.service.DuplicateResumeFinder;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;

import java.util.Optional;

@Service
public class DuplicateResumeFinderImpl implements DuplicateResumeFinder {

    private ResumeRepository resumeRepository;

    @Autowired
    public DuplicateResumeFinderImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public boolean findDuplicate(Resume resume) {
        Optional<Resume> resumeFromDatabase = resumeRepository.findResumeByTitleAndEmployerIdAndPostDateAndResponded(
                resume.getTitle(),
                resume.getEmployerId(),
                resume.getPostDate(),
                resume.getResponded());
        return resumeFromDatabase.isPresent();
    }
}
