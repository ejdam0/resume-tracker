package pl.strzelecki.resumetracker.csv.service;

import pl.strzelecki.resumetracker.entity.Resume;

import java.util.List;

public interface DuplicateResumeFinder {
    List<Resume> removeDuplicatesFromList(List<Resume> uploadedResumes);
}
