package pl.strzelecki.resumetracker.uploadData.csv.implementation;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.constants.CSVFileHeaders;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.uploadData.csv.CsvDataToResumeParser;
import pl.strzelecki.resumetracker.uploadData.csv.DateFromCSVGetter;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.EmployerInDatabaseChecker;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.ListDuplicateResumeFinder;

import java.util.ArrayList;
import java.util.List;

@Service
public class CsvDataToResumeParserImpl implements CsvDataToResumeParser {
    private EmployerInDatabaseChecker employerInDatabaseChecker;
    private ListDuplicateResumeFinder duplicateResumeFinder;
    private DateFromCSVGetter dateFromCSVGetter;

    @Autowired
    public CsvDataToResumeParserImpl(EmployerInDatabaseChecker employerInDatabaseChecker,
                                     ListDuplicateResumeFinder duplicateResumeFinder,
                                     DateFromCSVGetter dateFromCSVGetter) {
        this.employerInDatabaseChecker = employerInDatabaseChecker;
        this.duplicateResumeFinder = duplicateResumeFinder;
        this.dateFromCSVGetter = dateFromCSVGetter;
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
                            dateFromCSVGetter.getDate(r.get(CSVFileHeaders.RESUME_DATE)),
                            Boolean.parseBoolean(r.get(CSVFileHeaders.RESUME_RESPONDED)),
                            r.get(CSVFileHeaders.RESUME_URL));
                    uploadedResumes.add(resumeFromRecord);
                }
        );
        return uploadedResumes;
    }
}