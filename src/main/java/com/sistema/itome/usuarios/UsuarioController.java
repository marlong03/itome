package com.sistema.itome.usuarios;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.sistema.itome.perosnas.Personas;
import com.sistema.itome.perosnas.PersonasRepository;
import com.sistema.itome.prol.Rol;
import com.sistema.itome.prol.RolRepository;







@Controller
public class UsuarioController {


	@Autowired
	private UsuarioRepository  usuarioRepository; 
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolRepository  rolRepository; 
	@Autowired
	private PersonasRepository personasRepository;
	

	

	@GetMapping("usuarios/nuevo")
	public String mostrarFormularioDeRegistroDeUsuario(Model modelo) {
		List<Personas> listaPerosnas = personasRepository.findAll();
		List<Rol> listaRoles = rolRepository.findAll();
		modelo.addAttribute("listaRoles", listaRoles);
		modelo.addAttribute("usuarios", new Usuario());
		modelo.addAttribute("listaPerosnas", listaPerosnas);
		return "usuarios_form";
	}
	
	@GetMapping("/usuarios")
	public String listaUsuarios(Model modelo,@Param("usuarioClave")String usuarioClave) {
		List<Usuario> listaUsuario = usuarioService.listAll(usuarioClave);
		modelo.addAttribute("listaUsuario", listaUsuario);
		modelo.addAttribute("usuarioClave", usuarioClave);
		return "usuarios";
	}
	
	@PostMapping("/usuarios/guardar")
	public String registro(Model modelo,@Param("usuarioClave")String usuarioClave, ModelAndView modelAndView, Usuario usuario, BindingResult bindingResult,
			HttpServletRequest request, String siteURL) throws UnsupportedEncodingException, MessagingException {
		Usuario usarioNombre = usuarioRepository.findByNombreusuario(usuario.getNombreusuario());
		if (usarioNombre != null) {

			modelAndView.addObject("alreadyRegisteredMessage",
					"Opps! Al parecer ya existe un usuario con este nombre ");
			modelAndView.setViewName("registro");
		} else {
			List<Usuario> listaUsuario = usuarioService.listAll(usuarioClave);
			modelo.addAttribute("listaUsuario", listaUsuario);
			modelo.addAttribute("usuarioClave", usuarioClave);

			usuarioService.save(usuario, getSiteURL(request));

						return "redirect:/usuarios";
		}
		List<Personas> listaPerosnas = personasRepository.findAll();
		List<Rol> listaRoles = rolRepository.findAll();
		modelo.addAttribute("listaRoles", listaRoles);
		modelo.addAttribute("usuarios", new Usuario());
		modelo.addAttribute("listaPerosnas", listaPerosnas);
		return "usuarios_form";
	}
	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	
	@GetMapping("/usaurios/editar/{id}")
	public String mostrarFormularioDeModificarUsuarios(@PathVariable("id") Integer id,Model modelo) {
	Usuario usuarios= usuarioRepository.findById(id).get();

	modelo.addAttribute("usuarios", usuarios);
	List<Rol> listaRoles = rolRepository.findAll();
	modelo.addAttribute("listaRoles", listaRoles);

	List<Personas> listaPersonas = personasRepository.findAll();


	modelo.addAttribute("listaPerosnas", listaPersonas);
	return "usuarios_form";
	}


	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarUsuarios(@PathVariable("id")Integer id, Model modelo) {
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios";
		
	}
	@GetMapping("/exportarPDF")
	public void exportarListadeUsuariosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Usaurios_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Usuario> usuario = usuarioService.findAll();
		
		UsuarioExporterPdf exporter = new UsuarioExporterPdf(usuario);
		exporter.exportar(response);
	}
	
	@GetMapping("/exportarExcel")
	public void exportarListadoDeEmpleadosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Usuarios_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Usuario> usuario = usuarioService.findAll();
		
		UsuarioExporterExcel exporter = new UsuarioExporterExcel(usuario);
		exporter.exportar(response);
	}

	
}
