package pl.strzelecki.resumetracker.uploadData.csv;

import pl.strzelecki.resumetracker.entity.Resume;

import java.io.IOException;
import java.util.List;

public interface CsvReaderService {
    List<Resume> readCsvData(String data) throws IOException;
}
