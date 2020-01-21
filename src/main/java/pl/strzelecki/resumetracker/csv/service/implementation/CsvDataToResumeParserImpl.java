package pl.strzelecki.resumetracker.csv.service.implementation;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.csv.constants.CSVFileHeaders;
import pl.strzelecki.resumetracker.csv.service.CsvDataToResumeParser;
import pl.strzelecki.resumetracker.csv.service.DuplicateResumeFinder;
import pl.strzelecki.resumetracker.csv.service.EmployerInDatabaseChecker;
import pl.strzelecki.resumetracker.entity.Resume;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvDataToResumeParserImpl implements CsvDataToResumeParser {
    private List<Resume> resumes = new ArrayList<>();
    private EmployerInDatabaseChecker employerInDatabaseChecker;
    private DuplicateResumeFinder duplicateResumeFinder;

    @Autowired
    public CsvDataToResumeParserImpl(EmployerInDatabaseChecker employerInDatabaseChecker, DuplicateResumeFinder duplicateResumeFinder) {
        this.employerInDatabaseChecker = employerInDatabaseChecker;
        this.duplicateResumeFinder = duplicateResumeFinder;
    }

    @Override
    public List<Resume> parseCSVRecords(List<CSVRecord> csvRecords) {
        csvRecords.forEach(this::addDataToResumeList);
        return resumes;
    }

    private void addDataToResumeList(CSVRecord r) {
        Resume resumeFromRecord = new Resume(
                r.get(CSVFileHeaders.RESUME_TITLE),
                employerInDatabaseChecker.searchForEmployer(r.get(CSVFileHeaders.RESUME_EMPLOYER)),
                getDate(r.get(CSVFileHeaders.RESUME_DATE)),
                Boolean.parseBoolean(r.get(CSVFileHeaders.RESUME_RESPONDED)),
                r.get(CSVFileHeaders.RESUME_URL));
        if (duplicateResumeFinder.findDuplicate(resumeFromRecord)) {
            System.out.println(resumeFromRecord + " already exists in the database!");
        } else {
            resumes.add(resumeFromRecord);
        }
    }

    private LocalDate getDate(String dateString) {
        String datePattern = "yyyy-MM-dd";
        GenericValidator.isDate(dateString, datePattern, true);

        if (dateString.isEmpty()) {
            return LocalDate.parse(LocalDate.of(2020, 1, 1).format(DateTimeFormatter.ISO_DATE));
        } else if (!GenericValidator.isDate(dateString, datePattern, true)) {
            throw new RuntimeException("Provided data is not a proper date");
        } else {
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        }
    }
}