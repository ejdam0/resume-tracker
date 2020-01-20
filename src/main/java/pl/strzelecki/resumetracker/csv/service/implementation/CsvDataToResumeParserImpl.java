package pl.strzelecki.resumetracker.csv.service.implementation;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.csv.service.CSVFileHeaders;
import pl.strzelecki.resumetracker.csv.service.CsvDataToResumeParser;
import pl.strzelecki.resumetracker.entity.Employer;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.EmployerRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CsvDataToResumeParserImpl implements CsvDataToResumeParser {
    private List<Resume> resumes = new ArrayList<>();
    private EmployerRepository employerRepository;

    @Autowired
    public CsvDataToResumeParserImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public List<Resume> parseCSVRecords(List<CSVRecord> csvRecords) {
        csvRecords.forEach(this::addDataToResumesList);
        return resumes;
    }

    private void addDataToResumesList(CSVRecord r) {
        resumes.add(new Resume(
                r.get(CSVFileHeaders.RESUME_TITLE),
                checkIfProvidedEmployerAlreadyExistsInDb(r.get(CSVFileHeaders.RESUME_EMPLOYER)),
                getDate(r.get(CSVFileHeaders.RESUME_DATE)),
                Boolean.parseBoolean(r.get(CSVFileHeaders.RESUME_RESPONDED)),
                r.get(CSVFileHeaders.RESUME_URL)));
    }

    private LocalDate getDate(String dateString) {
        String datePattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        GenericValidator.isDate(dateString, datePattern, true);

        if (dateString.isEmpty()) {
            LocalDate defaultDate = LocalDate.now();
            return LocalDate.parse(defaultDate.format(DateTimeFormatter.ISO_DATE));
        } else if (GenericValidator.isDate(dateString, datePattern, true)) {
            throw new RuntimeException("Provided data is not a proper date");
        } else {
            return LocalDate.parse(dateString, formatter);
        }
    }

    private Employer checkIfProvidedEmployerAlreadyExistsInDb(String dataEmployerName) {
        Optional<Employer> employerByName = employerRepository.findEmployerByName(dataEmployerName);
        if (employerByName.isPresent()) {
            return employerByName.get();
        } else {
            Employer newEmployerInDb = new Employer(dataEmployerName);
            employerRepository.save(newEmployerInDb);
            return newEmployerInDb;
        }
    }
}