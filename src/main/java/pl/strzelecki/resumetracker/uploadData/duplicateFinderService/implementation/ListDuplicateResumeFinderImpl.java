package pl.strzelecki.resumetracker.uploadData.duplicateFinderService.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.ListDuplicateResumeFinder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListDuplicateResumeFinderImpl implements ListDuplicateResumeFinder {

    private ResumeRepository resumeRepository;

    @Autowired
    public ListDuplicateResumeFinderImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public List<Resume> removeDuplicatesFromList(List<Resume> uploadedResumes) {
        return uploadedResumes.stream()
                .filter(up -> !resumeRepository.existsResumeByTitleAndEmployerIdAndPostDateAndResponded(
                        up.getTitle(),
                        up.getEmployerId(),
                        up.getPostDate(),
                        up.getResponded())
                )
                .collect(Collectors.toList());
    }
}
