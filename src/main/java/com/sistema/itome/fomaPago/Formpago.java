package com.sistema.itome.fomaPago;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




@Entity
public class Formpago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 45, nullable = false, unique = true)
	private String nombre;

	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}







	

	


	public Formpago(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;

	}



	public Formpago(Integer id) {
		super();
		this.id = id;
	}



	public Formpago() {
		super();
	}



	public Formpago(String nombre) {
		super();
		this.nombre = nombre;
	}






	
	
	
	
	
	
}
