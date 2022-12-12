package com.sistema.itome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sistema.itome.producto.ProductoRepository;

@Controller
public class homeControler {

	@Autowired
	private ProductoRepository ProRepo;
	
	@GetMapping("index")
	public String listarProductos(Model model) {
		model.addAttribute("producto", ProRepo.findAll());
		
		return "index";
		
	}
}
