package pl.strzelecki.resumetracker.uploadData.csv;

import java.time.LocalDate;

public interface DateFromCSVGetter {
    LocalDate getDate(String dateString);
}
