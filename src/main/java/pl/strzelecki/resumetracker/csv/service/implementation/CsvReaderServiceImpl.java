package pl.strzelecki.resumetracker.csv.service.implementation;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.csv.service.CsvDataToResumeParser;
import pl.strzelecki.resumetracker.csv.service.CsvReaderService;
import pl.strzelecki.resumetracker.entity.Resume;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReaderServiceImpl implements CsvReaderService {

    private final String[] HEADERS = {"Title", "Employer", "Date", "Responded", "Url"};

    private CsvDataToResumeParser csvDataToResumeParser;

    @Autowired
    public CsvReaderServiceImpl(CsvDataToResumeParser csvDataToResumeParser) {
        this.csvDataToResumeParser = csvDataToResumeParser;
    }

    @Override
    public List<Resume> readCsvData(String data) {
        StringReader reader = new StringReader(data);
        CSVParser csvFileParser;
        List<CSVRecord> csvRecords = new ArrayList<>();
        try {
            csvFileParser = new CSVParser(reader, getFormat());
            csvRecords = csvFileParser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvDataToResumeParser.parseCSVRecords(csvRecords);
    }

    private CSVFormat getFormat() {
        return CSVFormat.EXCEL
                .withDelimiter(';')
                .withIgnoreSurroundingSpaces(true)
                .withIgnoreEmptyLines(true)
                .withHeader(HEADERS)
                .withFirstRecordAsHeader();
    }
}