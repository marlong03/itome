package com.sistema.itome.fomaPago;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;







@Controller
public class FormaPagoController {


	@Autowired

	private FormaPagoRepository  formaPagoRepository; 
	
	@GetMapping("formaPago/nuevo")
	public String mostrarFormularioDeNuevoFormaPago(Model modelo) {
		
		
		modelo.addAttribute("formapago", new Formpago());

		return "formPago_form";
	}
	
	
	@GetMapping("/formaPago")
	public String listaFormaPago(Model modelo) {
		List<Formpago> listaFormaPago = formaPagoRepository.findAll();
		modelo.addAttribute("listaFormaPago", listaFormaPago);
		return "formaPago";
	}
	@PostMapping("/formapago/guardar")
	public String guardarFormaPago(Formpago formaPago) {
		formaPagoRepository.save(formaPago);
		return "redirect:/formaPago";
	}

	
	@GetMapping("/formapago/editar/{id}")
	public String mostrarFormularioDeModificarFormaPago(@PathVariable("id") Integer id,Model modelo) {
		Formpago formPago= formaPagoRepository.findById(id).get();
	modelo.addAttribute("formapago", formPago);

	return "formPago_form";
	}


	@GetMapping("/formaPago/eliminar/{id}")
	public String eliminarFormPago(@PathVariable("id")Integer id, Model modelo) {
		formaPagoRepository.deleteById(id);
		return "redirect:/formaPago";
		
	}
	
	
	

	
}
