package pl.strzelecki.resumetracker.uploadData.csv;

import org.apache.commons.csv.CSVRecord;
import pl.strzelecki.resumetracker.entity.Resume;

import java.util.List;

public interface CsvDataToResumeParser {
    List<Resume> parseCSVRecords(List<CSVRecord> csvRecords);
}
