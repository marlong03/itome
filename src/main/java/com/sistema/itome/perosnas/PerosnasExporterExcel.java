package com.sistema.itome.perosnas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PerosnasExporterExcel {

	private XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Personas> listaPersonas;



	public  PerosnasExporterExcel(List<Personas> listaPersonas) {
		this.listaPersonas = listaPersonas;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Personas");
	}

	private void escribirCabeceraDeTabla() {
		Row fila = hoja.createRow(0);
		
		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setBold(true);
		fuente.setFontHeight(16);
		estilo.setFont(fuente);
		
		Cell celda = fila.createCell(0);
		celda.setCellValue("ID");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(1);
		celda.setCellValue("Nombres ");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(2);
		celda.setCellValue("Apellidos");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(3);
		celda.setCellValue("Telefono");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(4);
		celda.setCellValue("Correo");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(5);
		celda.setCellValue("Direccion");
		celda.setCellStyle(estilo);
		
		
		
		celda = fila.createCell(6);
		celda.setCellValue("Tipo de documento");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(7);
		celda.setCellValue("Numero de documento");
		celda.setCellStyle(estilo);
		
		
		
		
	}
	
	private void escribirDatosDeLaTabla() {
		int nueroFilas = 1;
		
		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
	
		for(Personas personas : listaPersonas) {
			Row fila = hoja.createRow(nueroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(personas.getId());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(personas.getNombres());
			hoja.autoSizeColumn(1);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(personas.getApellidos());
			hoja.autoSizeColumn(2);
			celda.setCellStyle(estilo);
			celda = fila.createCell(3);
			celda.setCellValue(personas.getTelefono());
			hoja.autoSizeColumn(3);
			celda.setCellStyle(estilo);
			celda = fila.createCell(4);
			celda.setCellValue(personas.getCorreo());
			hoja.autoSizeColumn(4);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(5);
			celda.setCellValue(personas.getDireccion());
			hoja.autoSizeColumn(5);
			celda.setCellStyle(estilo);
			
			
			
			celda = fila.createCell(6);
			celda.setCellValue(personas.getTipo_doc());
			hoja.autoSizeColumn(6);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(7);
			celda.setCellValue(personas.getNum_doc());
			hoja.autoSizeColumn(7);
			celda.setCellStyle(estilo);
			
			
			
		}
	}
	
	public void exportar(HttpServletResponse response) throws IOException {
		escribirCabeceraDeTabla();
		escribirDatosDeLaTabla();
		
		ServletOutputStream outPutStream = response.getOutputStream();
		libro.write(outPutStream);
		
		libro.close();
		outPutStream.close();
	}
}