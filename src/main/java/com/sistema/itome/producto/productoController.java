package com.sistema.itome.producto;


import java.nio.file.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;
import com.sistema.itome.marca.Marca;
import com.sistema.itome.marca.MarcaRepository;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;






@Controller
public class productoController {


	@Autowired

	private ProductoRepository  productoRepository; 

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private ProductoService productoService;

	
	@GetMapping("producto/nuevo")
	public String mostrarFormularioDeNuevoProducto(Model modelo) {
		
		List<Marca> listaMarcas = marcaRepository.findAll();
		
		modelo.addAttribute("producto", new Producto());
		modelo.addAttribute("listaMarcas", listaMarcas);
		return "producto_form";
	}

	
	@GetMapping("/producto")
	public String listaProducto(Producto producto,Model modelo,@Param("productoClave")String productoClave) {
		List<Producto> listaProducto = productoService.listAll(productoClave);
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		for(int i=0;i>listaProducto.size();i++) {
		
		};
		
		modelo.addAttribute("listaProducto", listaProducto);
		modelo.addAttribute("productoClave",productoClave);
		return "producto";
	}
	@PostMapping( "/producto/guardar" )
	public String  guardarProducto(@RequestParam(name="file", required = false)MultipartFile foto, Producto producto,
			RedirectAttributes flash) {
		
		if(!foto.isEmpty()) {
			String folder="uploads//";
			try {
				byte[] bytes = foto.getBytes();
				Path RutaAbsoluta = Paths.get(folder+foto.getOriginalFilename());
				Files.write(RutaAbsoluta, bytes);
				producto.setFoto(foto.getOriginalFilename());
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			productoRepository.save(producto);
			flash.addFlashAttribute("Exito", "foto subida!");
		
	     }
		return "redirect:/producto";
	
	}
	
	@GetMapping("/producto/editar/{idproducto}")
	public String mostrarFormularioDeModificarProducto(@PathVariable("idproducto") Integer id,Model modelo) {
		
		
	Producto producto= productoRepository.findById(id).get();
	modelo.addAttribute("producto", producto);

	List<Marca> listaMarcas = marcaRepository.findAll();


	modelo.addAttribute("listaMarcas", listaMarcas);


	return "producto_form";
	}


	@GetMapping("/producto/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id")Integer id, Model modelo) {
		productoRepository.deleteById(id);
		return "redirect:/producto";
		
	}
	@GetMapping("/exportarProductoExcel")
	public void exportarListadoDeProveedorEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Producto_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Producto> producto = productoService.getAllUser();
		
		ProductoExporterExcel exporter = new ProductoExporterExcel(producto);
		exporter.exportar(response);
	}

	
	
	@GetMapping("/exportarProductoPDF")
	public void exportarListadeProveedorEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Producto_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Producto> producto = productoService.getAllUser();
		ProductoExporterPdf exporter = new ProductoExporterPdf(producto);
		exporter.exportar(response);
	}
	
	

	
}
