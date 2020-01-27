package pl.strzelecki.resumetracker.uploadData.duplicateFinderService;

import pl.strzelecki.resumetracker.entity.Resume;

public interface SingleDuplicateResumeFinder {
    boolean checkIfResumeExistsInDb(Resume resume);
}
