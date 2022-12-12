package com.sistema.itome.perosnas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelUtils {


	public static ByteArrayInputStream customersToExcel(List<Personas> personas) throws IOException {
		String[] COLUMNs = {"ID", "Nombres", "Apellidos","Telefono", "Correo", "Direccion", "Tipo de documento",
				"Numero de documento"};
		try(
				Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
		){
			CreationHelper createHelper = workbook.getCreationHelper();
	 
			Sheet sheet = workbook.createSheet("Personas");
	 
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
	 
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
	 
			// Row for Header
			Row headerRow = sheet.createRow(0);
	 
			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}
	 
			// CellStyle for Age
			CellStyle ageCellStyle = workbook.createCellStyle();
			ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
	 
			int rowIdx = 1;
			for (Personas persona : personas) {
				Row row = sheet.createRow(rowIdx++);
	 
				row.createCell(0).setCellValue(persona.getId());
				row.createCell(1).setCellValue(persona.getNombres());
				row.createCell(2).setCellValue(persona.getApellidos());
				row.createCell(3).setCellValue(persona.getTelefono());
				row.createCell(4).setCellValue(persona.getCorreo());
				row.createCell(5).setCellValue(persona.getDireccion());
				row.createCell(6).setCellValue(persona.getTipo_doc());
				Cell ageCell = row.createCell(7);
				ageCell.setCellValue(persona.getNum_doc());
				ageCell.setCellStyle(ageCellStyle);
			}
	 
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	public static List<Personas> parseExcelFile(InputStream is) {
		try {
    		Workbook workbook = new XSSFWorkbook(is);
     
    		Sheet sheet = workbook.getSheet("Customers");
    		Iterator<Row> rows = sheet.iterator();
    		
    		List<Personas> lstCustomers = new ArrayList<Personas>();
    		
    		int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = rows.next();
    			
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			}
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();

    			Personas cust = new Personas();
    			
    			int cellIndex = 0;
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    				
    				if(cellIndex==0) { // ID
    					cust.setId((int) currentCell.getNumericCellValue());
    				} else if(cellIndex==1) { // Name
    					cust.setNombres(currentCell.getStringCellValue());
    				} else if(cellIndex==2) { // Address
    					cust.setApellidos(currentCell.getStringCellValue());
    				} else if(cellIndex==3) { // Age
    					cust.setTelefono((int) currentCell.getNumericCellValue());
    				} else if(cellIndex==4) { // Address
    					cust.setCorreo(currentCell.getStringCellValue());
    				} else if(cellIndex==5) { // Address
    					cust.setDireccion(currentCell.getStringCellValue());
    				} else if(cellIndex==6) { // Address
    					cust.setTipo_doc(currentCell.getStringCellValue());
    				} else if(cellIndex==7) { // Age
    					cust.setNum_doc((int) currentCell.getNumericCellValue());	
    					
    			} 
    				
    				cellIndex++;
    			}
    			
    			lstCustomers.add(cust);
    		}
    		
    		// Close WorkBook
    		workbook.close();
    		
    		return lstCustomers;
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }

	}}
