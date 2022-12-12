package com.sistema.itome.perosnas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;








@Controller
public class PersonasController3 {
	@Autowired
	private PersonasRepository PersonasRepository;
	
	
	
	@GetMapping("/personas/nuevo3")
	public String mostrarFormularioDeNuevaPersonas3(Model modelo) {
		modelo.addAttribute("personas", new Personas());
		return "personas_for3m";
	}
	@PostMapping("/personas/guardar3")
	public String guardarPersonas3(Personas personas) {
		try {
			PersonasRepository.save(personas);
			return "redirect:/personas";
		} catch (Exception e) {
			// TODO: handle exception
		}
		PersonasRepository.save(personas);
		return "redirect:/personas_for3m";
	}

}
