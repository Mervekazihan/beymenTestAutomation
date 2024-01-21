package org.example;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static util.Constants.FILE_NAME;

public class ExcelControl {
    public Map<Integer, String> readExcel() throws IOException, BiffException{
        Map<Integer, String> data = new HashMap<>();
        FileInputStream inputStream = new FileInputStream(FILE_NAME);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            int counter = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING){
                    data.put(counter, cell.getStringCellValue());
                }
                counter++;
            }
            inputStream.close();
        }
            return data;
    }
}
