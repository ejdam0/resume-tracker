package pl.strzelecki.resumetracker.uploadData.duplicateFinderService.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.DuplicateResumeFinder;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DuplicateResumeFinderImpl implements DuplicateResumeFinder {

    private ResumeRepository resumeRepository;

    @Autowired
    public DuplicateResumeFinderImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public List<Resume> removeDuplicatesFromList(List<Resume> uploadedResumes) {
        return uploadedResumes.stream()
                .filter(up -> {
                            Optional<Resume> resumeFromDb = resumeRepository.findResumeByTitleAndEmployerIdAndPostDateAndResponded(
                                    up.getTitle(),
                                    up.getEmployerId(),
                                    up.getPostDate(),
                                    up.getResponded());
                            return resumeFromDb.isEmpty();
                        }
                )
                .collect(Collectors.toList());
    }
}
