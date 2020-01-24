package pl.strzelecki.resumetracker.uploadData.duplicateFinderService;

import pl.strzelecki.resumetracker.entity.Employer;

public interface EmployerInDatabaseChecker {
    Employer searchForEmployer(String dataEmployerName);
}
