package com.sistema.itome.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public List<Pedido> listAll(String pedidoClave){
		if(pedidoClave !=null) {
		return repository.findAll(pedidoClave);	
		}
			
			
		return repository.findAll();
	}
	public List<Pedido>  getAllUser() {
		return repository.findAll();
		
		
	}

}
