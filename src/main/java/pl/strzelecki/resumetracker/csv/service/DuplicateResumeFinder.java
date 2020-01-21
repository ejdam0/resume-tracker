package pl.strzelecki.resumetracker.csv.service;

import pl.strzelecki.resumetracker.entity.Resume;

public interface DuplicateResumeFinder {
    boolean findDuplicate(Resume resume);
}
