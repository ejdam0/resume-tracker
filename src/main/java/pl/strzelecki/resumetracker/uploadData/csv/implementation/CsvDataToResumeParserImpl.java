package pl.strzelecki.resumetracker.uploadData.csv.implementation;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.constants.CSVFileHeaders;
import pl.strzelecki.resumetracker.uploadData.csv.CsvDataToResumeParser;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.DuplicateResumeFinder;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.EmployerInDatabaseChecker;
import pl.strzelecki.resumetracker.entity.Resume;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvDataToResumeParserImpl implements CsvDataToResumeParser {
    private EmployerInDatabaseChecker employerInDatabaseChecker;
    private DuplicateResumeFinder duplicateResumeFinder;

    @Autowired
    public CsvDataToResumeParserImpl(EmployerInDatabaseChecker employerInDatabaseChecker, DuplicateResumeFinder duplicateResumeFinder) {
        this.employerInDatabaseChecker = employerInDatabaseChecker;
        this.duplicateResumeFinder = duplicateResumeFinder;
    }

    @Override
    public List<Resume> parseCSVRecords(List<CSVRecord> csvRecords) {
        List<Resume> uploadedResumes = addDataToResumeList(csvRecords);
        return duplicateResumeFinder.removeDuplicatesFromList(uploadedResumes);
    }


    private List<Resume> addDataToResumeList(List<CSVRecord> csvRecords) {
        List<Resume> uploadedResumes = new ArrayList<>();
        csvRecords.forEach(r -> {
                    Resume resumeFromRecord = new Resume(
                            r.get(CSVFileHeaders.RESUME_TITLE),
                            employerInDatabaseChecker.searchForEmployer(r.get(CSVFileHeaders.RESUME_EMPLOYER)),
                            getDate(r.get(CSVFileHeaders.RESUME_DATE)),
                            Boolean.parseBoolean(r.get(CSVFileHeaders.RESUME_RESPONDED)),
                            r.get(CSVFileHeaders.RESUME_URL));
                    uploadedResumes.add(resumeFromRecord);
                }
        );
        return uploadedResumes;
    }

    private LocalDate getDate(String dateString) {
        String datePattern = "yyyy-MM-dd";

        if (dateString.isEmpty()) {
            return LocalDate.parse(LocalDate.of(2020, 1, 1).format(DateTimeFormatter.ISO_DATE));
        } else if (!GenericValidator.isDate(dateString, datePattern, true)) {
            throw new RuntimeException("Provided data is not a proper date: " + dateString + ". Date format: yyyy-MM-dd");
        } else {
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        }
    }
}