package pl.strzelecki.resumetracker.csv.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.csv.service.CsvReaderService;
import pl.strzelecki.resumetracker.csv.service.SaveToDatabaseService;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;

import java.io.IOException;
import java.util.List;

@Service
public class SaveToDatabaseServiceImpl implements SaveToDatabaseService {

    private ResumeRepository resumeRepository;
    private CsvReaderService csvReaderService;

    @Autowired
    public SaveToDatabaseServiceImpl(ResumeRepository resumeRepository, CsvReaderService csvReaderService) {
        this.resumeRepository = resumeRepository;
        this.csvReaderService = csvReaderService;
    }

    @Override
    public boolean saveDataToDatabase(String data) throws IOException {
        List<Resume> uploadedResumes = csvReaderService.readCsvData(data);
        if (uploadedResumes.isEmpty()) {
            return false;
        }
        resumeRepository.saveAll(uploadedResumes);
        return true;
    }
}
