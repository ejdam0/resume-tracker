package pl.strzelecki.resumetracker.uploadData.xls;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface XlsToCsvConverterService {
    String convertXslToCsv(File file) throws IOException;
}
