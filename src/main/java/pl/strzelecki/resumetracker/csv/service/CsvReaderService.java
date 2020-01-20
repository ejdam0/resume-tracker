package pl.strzelecki.resumetracker.csv.service;

import pl.strzelecki.resumetracker.entity.Resume;

import java.io.IOException;
import java.util.List;

public interface CsvReaderService {
    List<Resume> readCsvData(String data) throws IOException;
}
