package pl.strzelecki.resumetracker.uploadData.xls.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;
import pl.strzelecki.resumetracker.uploadData.csv.CsvReaderService;
import pl.strzelecki.resumetracker.uploadData.xls.MultiPartFileToFileConverter;
import pl.strzelecki.resumetracker.uploadData.xls.SaveXLSToDatabaseService;
import pl.strzelecki.resumetracker.uploadData.xls.XlsToCsvConverterService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SaveXLSToDatabaseServiceImpl implements SaveXLSToDatabaseService {

    private ResumeRepository resumeRepository;
    private XlsToCsvConverterService xlsToCsvConverterService;
    private MultiPartFileToFileConverter multiPartFileToFileConverter;
    private CsvReaderService csvReaderService;

    @Autowired
    public SaveXLSToDatabaseServiceImpl(ResumeRepository resumeRepository,
                                        XlsToCsvConverterService xlsToCsvConverterService,
                                        MultiPartFileToFileConverter multiPartFileToFileConverter,
                                        CsvReaderService csvReaderService) {
        this.resumeRepository = resumeRepository;
        this.xlsToCsvConverterService = xlsToCsvConverterService;
        this.multiPartFileToFileConverter = multiPartFileToFileConverter;
        this.csvReaderService = csvReaderService;
    }

    @Override
    public boolean saveDataToDatabase(MultipartFile uploadedFile) throws IOException {
        File xlsDataFile;
        List<Resume> uploadedResumes;

        xlsDataFile = multiPartFileToFileConverter.convertToFile(uploadedFile);
        String convertedXlsFile = xlsToCsvConverterService.convertXslToCsv(xlsDataFile);
        uploadedResumes = csvReaderService.readCsvData(convertedXlsFile);

        if (uploadedResumes.isEmpty()) {
            return false;
        } else {
            resumeRepository.saveAll(uploadedResumes);
            return true;
        }
    }
}
