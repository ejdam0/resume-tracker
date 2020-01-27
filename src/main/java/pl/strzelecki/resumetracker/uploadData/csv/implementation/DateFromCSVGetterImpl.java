package pl.strzelecki.resumetracker.uploadData.csv.implementation;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.uploadData.csv.DateFromCSVGetter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Service
public class DateFromCSVGetterImpl implements DateFromCSVGetter {
    private final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE;

    @Override
    public LocalDate getDate(String dateString) {
        String datePattern = "yyyy-MM-dd";
        if (dateString.isEmpty()) {
            return LocalDate.parse(LocalDate.of(2020, 1, 1).format(DateTimeFormatter.ISO_DATE));
        }
        if (isWrongFormat(dateString)) {
            String WRONG_DATE_PATTERN = "dd-MMM-yyyy";
            DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern(WRONG_DATE_PATTERN)
                    .toFormatter();
            LocalDate tempResult = LocalDate.parse(dateString, dateTimeFormatter);
            String tempStringResult = tempResult.format(ISO_FORMATTER);
            return LocalDate.parse(tempStringResult, ISO_FORMATTER);
        } else if (!GenericValidator.isDate(dateString, datePattern, true)) {
            throw new RuntimeException("Provided data is not a proper date: " + dateString + ". Date format: yyyy-MM-dd");
        } else {
            return LocalDate.parse(dateString, ISO_FORMATTER);
        }
    }

    private boolean isWrongFormat(String value) {
        try {
            LocalDate parsedDate = LocalDate.parse(value, ISO_FORMATTER);
            String result = parsedDate.format(ISO_FORMATTER);
            return !result.equals(value);
        } catch (Exception e) {
            return true;
        }
    }
}
