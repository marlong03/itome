package com.sistema.itome.perosnas;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;








@Controller
public class PersonasController2 {
	@Autowired
	private PersonasRepository PersonasRepository;
	
	
	
	@GetMapping("/personas/nuevo2")
	public String mostrarFormularioDeNuevaPersonas2(Model modelo) {
		modelo.addAttribute("personas", new Personas());
		return "personas_for2m";
	}
	@PostMapping("/personas/guardar2")
	public String guardarPersonas2(Personas personas) {
		PersonasRepository.save(personas);
		return "redirect:/proveedor/nuevo";
	}

}
