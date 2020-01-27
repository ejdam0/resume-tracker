package pl.strzelecki.resumetracker.uploadData.duplicateFinderService;

import pl.strzelecki.resumetracker.entity.Resume;

import java.util.List;

public interface ListDuplicateResumeFinder {
    List<Resume> removeDuplicatesFromList(List<Resume> uploadedResumes);
}
