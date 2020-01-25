package pl.strzelecki.resumetracker.uploadData.csv.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;
import pl.strzelecki.resumetracker.uploadData.csv.CsvReaderService;
import pl.strzelecki.resumetracker.uploadData.csv.SaveCSVToDatabaseService;

import java.io.IOException;
import java.util.List;

@Service
public class SaveCSVToDatabaseServiceImpl implements SaveCSVToDatabaseService {

    private ResumeRepository resumeRepository;
    private CsvReaderService csvReaderService;

    @Autowired
    public SaveCSVToDatabaseServiceImpl(ResumeRepository resumeRepository, CsvReaderService csvReaderService) {
        this.resumeRepository = resumeRepository;
        this.csvReaderService = csvReaderService;
    }

    @Override
    public boolean saveDataToDatabase(String data) {
        List<Resume> uploadedResumes;
        try {
            uploadedResumes = csvReaderService.readCsvData(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read provided csv data.");
        }
        if (uploadedResumes.isEmpty()) {
            return false;
        } else {
            resumeRepository.saveAll(uploadedResumes);
            return true;
        }
    }
}
