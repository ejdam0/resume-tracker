package pl.strzelecki.resumetracker.uploadData.xls.implementation;

import lombok.Cleanup;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.uploadData.xls.XlsToCsvConverterService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class XlsToCsvConverterServiceImpl implements XlsToCsvConverterService {
    @Override
    public String convertXslToCsv(File file) throws IOException {
        @Cleanup FileInputStream fileInputStream = new FileInputStream(file);
        @Cleanup XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        // get the first sheet
        XSSFSheet firstSheet = workbook.getSheetAt(0);
        // iterate on rows
        Iterator<Row> rowIterator = firstSheet.iterator();
        StringBuilder result = new StringBuilder();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // iterate on cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                result.append(cell.toString()).append(";");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
