package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class  ExelReader {
    private Workbook workbook;

    public  ExelReader(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
    }

    public String getCellData(String sheetName, int row, int col) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            return null; // Sheet not found
        }
        Row rowData = sheet.getRow(row);
        if (rowData == null) {
            return null; // Row not found
        }
        Cell cell = rowData.getCell(col);
        if (cell == null) {
            return null; // Cell not found
        }
        return cell.toString();
    }
}
