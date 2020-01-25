package pl.strzelecki.resumetracker.uploadData.xls.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.strzelecki.resumetracker.uploadData.xls.MultiPartFileToFileConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class MultiPartFileToFileConverterImpl implements MultiPartFileToFileConverter {
    @Override
    public File convertToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        convertedFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return convertedFile;
    }
}
