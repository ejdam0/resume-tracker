package pl.strzelecki.resumetracker.uploadData.xls.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.strzelecki.resumetracker.repository.ResumeRepository;
import pl.strzelecki.resumetracker.uploadData.csv.CsvReaderService;
import pl.strzelecki.resumetracker.uploadData.xls.SaveXLSToDatabaseService;

import java.io.IOException;

@Service
public class SaveXLSToDatabaseServiceImpl implements SaveXLSToDatabaseService {

    private ResumeRepository resumeRepository;
    private CsvReaderService csvReaderService;

    @Autowired
    public SaveXLSToDatabaseServiceImpl(ResumeRepository resumeRepository, CsvReaderService csvReaderService) {
        this.resumeRepository = resumeRepository;
        this.csvReaderService = csvReaderService;
    }

    @Override
    public boolean saveDataToDatabase(MultipartFile uploadedFile) throws IOException {
//        List<Resume> uploadedResumes = csvReaderService.readCsvData(data);
//        if (uploadedResumes.isEmpty()) {
//            return false;
//        } else {
//            resumeRepository.saveAll(uploadedResumes);
//            return true;
//        }
    }
}
