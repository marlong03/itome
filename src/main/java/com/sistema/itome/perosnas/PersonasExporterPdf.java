package com.sistema.itome.perosnas;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PersonasExporterPdf {
	
	private List<Personas> lisataPersonas;

	public PersonasExporterPdf(List<Personas> lisataPersonas) {
		super();
		this.lisataPersonas = lisataPersonas;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.RED);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Nombre", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Apellido", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Telefono", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Correo", fuente));
		tabla.addCell(celda);	
		celda.setPhrase(new Phrase("Direccion", fuente));
		tabla.addCell(celda);	
		celda.setPhrase(new Phrase("Tipo de documento", fuente));
		tabla.addCell(celda);	
		celda.setPhrase(new Phrase("Numero de documento", fuente));
		tabla.addCell(celda);	
	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Personas personas : lisataPersonas) {
			tabla.addCell(String.valueOf(personas.getId()));
			tabla.addCell(personas.getNombres());
			tabla.addCell(personas.getApellidos());
			tabla.addCell(String.valueOf(personas.getTelefono()));
			tabla.addCell(personas.getCorreo());
			tabla.addCell(personas.getDireccion());
			tabla.addCell(personas.getTipo_doc());
			tabla.addCell(String.valueOf(personas.getNum_doc()));
	
			
		}
	}
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
	 Font fuente= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	 fuente.setColor(Color.BLUE);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Lista de ´personas", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabla = new PdfPTable(8);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 2.3f, 2.3f, 4f, 2.9f, 2.9f, 2.9f, 2.9f });
		tabla.setWidthPercentage(110);

		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);
		documento.close();
	}
}
