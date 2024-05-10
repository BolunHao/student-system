package com.management.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class ExcelUtil {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * Export Excel
     * @param sheetName Sheet Name
     * @param title title
     * @param values element
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, String [][]values){

        // The first step is to create an HSSFWorkbook, which corresponds to an Excel file.
        HSSFWorkbook wb = new HSSFWorkbook();

        // The second step, in the workbook to add a sheet, corresponding to the Excel file in the sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // Step 3, in the sheet to add the table header line 0,
        // note that the old version of poi on the number of Excel rows and columns have restrictions
        HSSFRow row = sheet.createRow(0);

        // Step 4: Create the cell style and set the value header Set the header to be centred
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //Declaring Cells
        HSSFCell cell = null;

        //Create Title
        for(int i=0;i<title.length;i++){
            //Creating a cell
            cell = row.createCell(i);
            //Assigning values to cells
            cell.setCellValue(title[i]);
            //Styling cells
            cell.setCellStyle(style);
        }

        //Create Content
        if (values != null && values[0].length > 0){
            for(int i=0;i<values.length;i++){
                //Create data-filled rows starting from the second row with a subscript of 1
                row = sheet.createRow(i + 1);
                for(int j=0;j<values[i].length;j++){
                    //Assigns the contents to the corresponding column objects in sequence
                    row.createCell(j).setCellValue(values[i][j]);
                }
            }
        }
        return wb;
    }


    /**
     * Reads an excel file, parses it, and returns
     * @param file
     * @throws IOException
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
        //Inspection of documents
        checkFile(file);
        //Get Workbook object
        Workbook workbook = getWorkBook(file);
        //Creates a return object that returns the values in each row as an array and all rows as a collection
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //Get the current sheet
                org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //Get the start row of the current sheet
                int firstRowNum  = sheet.getFirstRowNum();
                //Get the end row of the current sheet
                int lastRowNum = sheet.getLastRowNum();
                //Loop through all rows except the first
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //Get current line
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //Get the start column of the current row
                    int firstCellNum = row.getFirstCellNum();
                    //Get the number of columns in the current row
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //Loop through the current line
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
                //  workbook.cloneSheet(sheetNum);
            }
//            workbook.close();
        }
        return list;
    }
    public static void checkFile(MultipartFile file) throws IOException{
        //Determine if a file exists
        if(null == file){
            System.out.println("The file does not exist!");
            throw new FileNotFoundException("The file does not exist!");
        }
        //Get filename
        String fileName = file.getOriginalFilename();
        //Determine if a file is an excel file
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
            System.out.println(fileName + "Not an excel file.");
            throw new IOException(fileName + "Not an excel file.");
        }
    }
    public static Workbook getWorkBook(MultipartFile file) {
        //Get filename
        String fileName = file.getOriginalFilename();
        //Create a Workbook object that represents the entire excel
        Workbook workbook = null;
        try {
            //Getting the io stream of an excel file
            InputStream is = file.getInputStream();
            //Get different Workbook implementation class objects based on different file extensions (xls and xlsx)
            if(fileName.endsWith(xls)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return workbook;
    }
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //Read the number as a String to avoid the situation where 1 is read as 1.0
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //Determining the type of data
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //digital
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //string
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //formulas
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //empty value
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //malfunctions
                cellValue = "illicit character";
                break;
            default:
                cellValue = "Unknown type";
                break;
        }
        return cellValue;
    }

}
