package pl.strzelecki.resumetracker.uploadData.xls;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SaveXLSToDatabaseService {
    boolean saveDataToDatabase(MultipartFile uploadedFile) throws IOException;
}
