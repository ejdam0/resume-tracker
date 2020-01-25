package pl.strzelecki.resumetracker.uploadData.xls;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public interface MultiPartFileToFileConverter {
    File convertToFile(MultipartFile multipartFile) throws IOException;
}
