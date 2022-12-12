package com.sistema.itome.perosnas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PersonasService {
	
	@Autowired
	private PersonasRepository repository;
	

	public List<Personas> listAll(String palabraClave){
		if(palabraClave !=null) {
		return repository.findAll(palabraClave);	
		}
			
			
		return repository.findAll();
	}
	
	
	public List<Personas>  getAllUser() {
		return repository.findAll();
		
		
	}
	

}
