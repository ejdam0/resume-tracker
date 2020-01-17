package pl.strzelecki.resumetracker.service;

import pl.strzelecki.resumetracker.entity.Employer;

import java.util.List;

public interface EmployerService {
    List<Employer> findAll();

    Employer findById(long theId);

    void save(Employer employer);

    void deleteById(long theId);

    void deleteAll();
}
