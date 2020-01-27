package pl.strzelecki.resumetracker.uploadData.duplicateFinderService.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.SingleDuplicateResumeFinder;

@Service
public class SingleDuplicateResumeFinderImpl implements SingleDuplicateResumeFinder {

    private ResumeRepository resumeRepository;

    @Autowired
    public SingleDuplicateResumeFinderImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public boolean checkIfResumeExistsInDb(Resume resume) {
        return resumeRepository.existsResumeByTitleAndEmployerIdAndPostDateAndResponded(
                resume.getTitle(),
                resume.getEmployerId(),
                resume.getPostDate(),
                resume.getResponded());
    }
}
