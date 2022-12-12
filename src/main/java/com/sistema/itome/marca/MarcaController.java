package com.sistema.itome.marca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
public class MarcaController {
	@Autowired
	private MarcaRepository marcaRepository;
	
	@GetMapping("/marca")
public String	listarMarca(Model modelo) {
	List<Marca> listaMarca =marcaRepository.findAll();
	modelo.addAttribute("listaMarca", listaMarca);
	return "marca";
	}
	
	@GetMapping("/marca/nuevo")
	public String mostrarFormularioDeNuevaMarca(Model modelo) {
		modelo.addAttribute("marca", new Marca());
		return "marca_form";
	}
	@PostMapping("/marcas/guardar")
	public String guardarMarca(Marca marca) {
		marcaRepository.save(marca);
		return "redirect:/marca";
	}
	
	
	
	
	
}