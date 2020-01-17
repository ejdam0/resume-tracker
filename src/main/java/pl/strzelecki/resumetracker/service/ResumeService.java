package pl.strzelecki.resumetracker.service;

import pl.strzelecki.resumetracker.entity.Resume;

import java.util.List;

public interface ResumeService {
    List<Resume> findAll();

    Resume findById(long theId);

    void save(Resume resume);

    void deleteById(long theId);

    void deleteAll();
}
