package com.sistema.itome.prol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;







@Controller
public class RolController {

	@Autowired
	private RolRepository rolRepository;
	
	@GetMapping("/rol")
public String	listarRol(Model modelo) {
	List<Rol> listaRol =rolRepository.findAll();
	modelo.addAttribute("listaRol", listaRol);
	return "rol";
	}

	@GetMapping("/rol/nuevo")
	public String mostrarFormularioDeNuevaRol(Model modelo) {
		modelo.addAttribute("rol", new Rol());
		return "rol_form";
	}
	@PostMapping("/rol/guardar")
	public String guardarRol(Rol rol) {
		rolRepository.save(rol);
		return "redirect:/rol";
	}
	
}
