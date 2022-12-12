package com.sistema.itome.pedido;

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

public class pedidoExporterPdf {
	
	private List<Pedido> listaPedido;

	public pedidoExporterPdf(List<Pedido> listaPedido) {
		super();
		this.listaPedido = listaPedido;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.RED);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Cantidad", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Total", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Comprador", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Forma de pago", fuente));
		tabla.addCell(celda);	
		celda.setPhrase(new Phrase("Producto", fuente));
		tabla.addCell(celda);	
	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Pedido pedido : listaPedido) {
			tabla.addCell(String.valueOf(pedido.getIdpedidos()));
			tabla.addCell(String.valueOf(pedido.getCantidad()));
			tabla.addCell(String.valueOf(pedido.getTotal()));
			tabla.addCell(pedido.getFormpago().getNombre());
			tabla.addCell(pedido.getUsuario().getNombreusuario());
			tabla.addCell(pedido.getProducto().toString());
			
		
	
			
		}
	}
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
	 Font fuente= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	 fuente.setColor(Color.BLUE);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Pedidos", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabla = new PdfPTable(6);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 2.3f, 2.3f, 3f, 2.9f, 4f });
		tabla.setWidthPercentage(110);

		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);
		documento.close();
	}
}
