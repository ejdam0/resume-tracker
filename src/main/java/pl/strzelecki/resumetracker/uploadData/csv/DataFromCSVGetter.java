package pl.strzelecki.resumetracker.uploadData.csv;

import java.time.LocalDate;

public interface DataFromCSVGetter {
    LocalDate getDate(String dateString);
}
