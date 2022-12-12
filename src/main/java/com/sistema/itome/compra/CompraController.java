package com.sistema.itome.compra;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lowagie.text.DocumentException;
import com.sistema.itome.fomaPago.FormaPagoRepository;
import com.sistema.itome.fomaPago.Formpago;
import com.sistema.itome.perosnas.Personas;
import com.sistema.itome.perosnas.PersonasRepository;






@Controller
public class CompraController {


	@Autowired

	private ComprasRepository  comprasRepository; 

	@Autowired
	private PersonasRepository proveedorRepository;
	
	@Autowired
	private FormaPagoRepository formaPagoRepository;
	
	@Autowired
	private PersonasRepository producotRepository;
	
	@Autowired
	private ComprasService comprasService;
	
	@GetMapping("compras/nuevo")
	public String mostrarFormularioDeNuevoCompras(Model modelo) {
		
		List<Personas> listaProveedor= proveedorRepository.findAll();
		List<Formpago> listaFormpago= formaPagoRepository.findAll();
		List<Personas> listaProducto= producotRepository.findAll();
		modelo.addAttribute("compras", new Compras());
		modelo.addAttribute("listaProveedor", listaProveedor);
		modelo.addAttribute("listaFormpago", listaFormpago);
		modelo.addAttribute("listaProducto", listaProducto);
		return "compras_form";
	}
	
	
	@GetMapping("/compras")
	public String listaCompras(Model modelo ,@Param("comprasClave")String comprasClave) {
		List<Compras> listacompras = comprasService.listAll(comprasClave);
		modelo.addAttribute("listacompras", listacompras);
		modelo.addAttribute("comprasClave;",comprasClave);
		return "compras";
	}
	@PostMapping("/compras/guardar")
	public String guardarCompras(Compras compras) {
		comprasRepository.save(compras);
		return "redirect:/compras";
	}

	
	@GetMapping("/compras/editar/{idcompras}")
	public String mostrarFormularioDeModificarCompras(@PathVariable("idcompras") Integer id,Model modelo) {
	Compras compras= comprasRepository.findById(id).get();
	modelo.addAttribute("compras", compras);

	List<Personas> listaProducto= producotRepository.findAll();
	List<Personas> listaProveedor= proveedorRepository.findAll();
	List<Formpago> listaFormpago= formaPagoRepository.findAll();
	modelo.addAttribute("listaProveedor", listaProveedor);
	modelo.addAttribute("listaFormpago", listaFormpago);
	modelo.addAttribute("listaProducto", listaProducto);

	return "compras_form";
	}


	@GetMapping("/compras/eliminar/{idcompras}")
	public String eliminarCompras(@PathVariable("idcompras")Integer id, Model modelo) {
		comprasRepository.deleteById(id);
		return "redirect:/compras";
		
	}
	
	@GetMapping("/exportarComprasExcel")
	public void exportarListadoDeComprasEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Compras_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Compras> compras = comprasService.getAllUser();
		
		ComprasExporterExcel exporter = new ComprasExporterExcel(compras);
		exporter.exportar(response);
	}
	
	@GetMapping("/exportarComprasPDF")
	public void exportarListadeUsuariosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Compras_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Compras> compras = comprasService.getAllUser();
		
		ComprasExporterPdf exporter = new ComprasExporterPdf(compras);
		exporter.exportar(response);
	}
	

	
}
