package com.sistema.itome.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginControlador {
	@Autowired
	private UsuarioService servicio;
	
	@GetMapping("/login")
	public String iniciarSesion(Model model) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
	            return "login";
	        }
	        model.addAttribute("usuarios", servicio.listarUsuarios());
	        return "redirect:/usuarios";

	}
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuario", servicio.listarUsuarios());
		return "index";
	}
}
