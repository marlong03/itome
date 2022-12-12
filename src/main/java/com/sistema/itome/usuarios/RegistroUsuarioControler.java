package com.sistema.itome.usuarios;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistroUsuarioControler {

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	@Autowired
	private UsuarioService usuarioservice;

	@GetMapping("/registro")
	public String MostrarFormularioRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());

		return "registro";
	}

	@PostMapping("/registro/guardar")
	public String registro( ModelAndView modelAndView,Usuario usuario, BindingResult bindingResult,
			HttpServletRequest request, String siteURL) throws UnsupportedEncodingException, MessagingException {
		Usuario usarioNombre = usuarioRepositorio.findByNombreusuario(usuario.getNombreusuario());
		if (usarioNombre != null) {

			modelAndView.addObject("alreadyRegisteredMessage",
					"Opps! Al parecer ya existe un usuario con este nombre ");
			modelAndView.setViewName("registro");
		} else {

			usuarioservice.save(usuario, getSiteURL(request));

			return "login";
		}
		return "registro";
	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code,Model model) {
		boolean verified = usuarioservice.verify(code);
		String pageTittle = verified ? "Verificacion exitosa!" : "Verificacion fallida";
		model.addAttribute("pageTittle", pageTittle);
	    return (verified? "verify_success":"verify_fail");
	}
}
