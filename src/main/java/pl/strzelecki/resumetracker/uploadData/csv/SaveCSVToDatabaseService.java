package pl.strzelecki.resumetracker.uploadData.csv;

import java.io.IOException;

public interface SaveCSVToDatabaseService {
    boolean saveDataToDatabase(String data) throws IOException;
}
