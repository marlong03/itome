package com.sistema.itome.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComprasService {
	
	@Autowired
	private ComprasRepository repository;
	
	public List<Compras> listAll(String comprasClave){
		if(comprasClave !=null) {
		return repository.findAll(comprasClave);	
		}
			
			
		return repository.findAll();
	}
	public List<Compras>  getAllUser() {
		return repository.findAll();
		
		
	}

}
