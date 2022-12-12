package com.sistema.itome.perosnas;

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






@Controller
public class PersonasController {
	@Autowired
	private PersonasRepository PersonasRepository;
	
	@Autowired
	private PersonasService personasService;
	
	@Autowired
	private PersonasService service;
	
	@GetMapping("/personas/nuevo")
	public String mostrarFormularioDeNuevaPersonas(Model modelo ) {
		modelo.addAttribute("personas", new Personas());
		return "personas_form";
	}

		
	@GetMapping("/personas")
	public String listaPersonas(Model modelo ,@Param("palabraClave")String palabraClave) {
		List<Personas> listaPersonas = service.listAll(palabraClave);
		modelo.addAttribute("listaPersonas", listaPersonas);
		modelo.addAttribute("palabraClave;",palabraClave);
		return "personas";
	}
	@PostMapping("/personas/guardar")
	public String guardarPersonas(Personas personas) {
		PersonasRepository.save(personas);
		return "redirect:/usuarios/nuevo";
	}

	
	@GetMapping("/personas/editar/{id}")
	public String mostrarFormularioDeModificarPersonas(@PathVariable("id") Integer id,Model modelo) {
	Personas personas= PersonasRepository.findById(id).get();
	modelo.addAttribute("personas", personas);

	List<Personas> listaPersonas = PersonasRepository.findAll();


	modelo.addAttribute("listaPersonas", listaPersonas);


	return "personas_form";
	}

	@GetMapping("/personas/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id")Integer id, Model modelo) {
		PersonasRepository.deleteById(id);
		return "redirect:/personas";
		
	}
	
	@GetMapping("/exportarPersonasPDF")
	public void exportarListadeUsuariosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Personas_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Personas> personas = personasService.getAllUser();
		PersonasExporterPdf exporter = new PersonasExporterPdf(personas);
		exporter.exportar(response);
	}
	
	@GetMapping("/exportarPersonasExcel")
	public void exportarListadoDeEmpleadosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Personas_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Personas> personas = personasService.getAllUser();
		
		PerosnasExporterExcel exporter = new PerosnasExporterExcel(personas);
		exporter.exportar(response);
	}

}
