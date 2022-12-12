package com.sistema.itome.producto;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







@Service
public class ProductoService{
	


	@Autowired
	private ProductoRepository repository;

	

	public List<Producto> listAll(String productoClave) {
		if (productoClave != null) {
			return repository.findAll(productoClave);
		}

		return repository.findAll();
	}


	

	public List<Producto> getAllUser() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


	

	

	
}
