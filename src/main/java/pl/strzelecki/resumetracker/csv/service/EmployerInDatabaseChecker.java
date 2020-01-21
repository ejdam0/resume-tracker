package pl.strzelecki.resumetracker.csv.service;

import pl.strzelecki.resumetracker.entity.Employer;

public interface EmployerInDatabaseChecker {
    Employer searchForEmployer(String dataEmployerName);
}
