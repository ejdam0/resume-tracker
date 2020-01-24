package pl.strzelecki.resumetracker.constants;

import org.apache.commons.csv.CSVFormat;

public interface CSVFileFormat {

    String[] HEADERS = {"Title", "Employer", "Date", "Responded", "Url"};

    static CSVFormat getFormat() {
        return CSVFormat.EXCEL
                .withDelimiter(';')
                .withIgnoreSurroundingSpaces(true)
                .withIgnoreEmptyLines(true)
                .withHeader(HEADERS)
                .withFirstRecordAsHeader();
    }
}
