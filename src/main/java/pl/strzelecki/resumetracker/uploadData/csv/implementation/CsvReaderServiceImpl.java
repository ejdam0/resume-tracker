package pl.strzelecki.resumetracker.uploadData.csv.implementation;

import lombok.Cleanup;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.constants.CSVFileFormat;
import pl.strzelecki.resumetracker.uploadData.csv.CsvDataToResumeParser;
import pl.strzelecki.resumetracker.uploadData.csv.CsvReaderService;
import pl.strzelecki.resumetracker.entity.Resume;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Service
public class CsvReaderServiceImpl implements CsvReaderService {

    private CsvDataToResumeParser csvDataToResumeParser;

    @Autowired
    public CsvReaderServiceImpl(CsvDataToResumeParser csvDataToResumeParser) {
        this.csvDataToResumeParser = csvDataToResumeParser;
    }

    @Override
    public List<Resume> readCsvData(String data) throws IOException {
        StringReader reader = new StringReader(data);
        @Cleanup CSVParser csvFileParser = new CSVParser(reader, CSVFileFormat.getFormat());
        List<CSVRecord> csvRecords;
        csvRecords = csvFileParser.getRecords();
        return csvDataToResumeParser.parseCSVRecords(csvRecords);
    }
}