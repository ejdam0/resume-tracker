package pl.strzelecki.resumetracker.csv.service;

import java.io.IOException;

public interface SaveToDatabaseService {
    boolean saveDataToDatabase(String data) throws IOException;
}
