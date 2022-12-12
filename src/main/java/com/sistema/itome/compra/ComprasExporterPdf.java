package com.sistema.itome.compra;

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

public class ComprasExporterPdf {
	
	private List<Compras> listaCompras;

	public ComprasExporterPdf(List<Compras> listaCompras) {
		super();
		this.listaCompras = listaCompras;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.RED);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Valor", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Cantidad", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Fecha", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Proveedor", fuente));
		tabla.addCell(celda);	
		celda.setPhrase(new Phrase("Producto", fuente));
		tabla.addCell(celda);	
	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Compras compras : listaCompras) {
			tabla.addCell(String.valueOf(compras.getIdcompras()));
			tabla.addCell(String.valueOf( compras.getValor()));
			tabla.addCell(String.valueOf(compras.getCantidad()));
			tabla.addCell(String.valueOf(compras.getFecha_Compra()));
			tabla.addCell(compras.getProveedor().getNombres());
			tabla.addCell(compras.getProducto().toString());
		
	
			
		}
	}
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
	 Font fuente= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	 fuente.setColor(Color.BLUE);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Lista de proveedores", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabla = new PdfPTable(6);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 2.3f, 2.3f, 4f, 2.9f, 2.9f });
		tabla.setWidthPercentage(110);

		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);
		documento.close();
	}
}
