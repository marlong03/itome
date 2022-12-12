package com.sistema.itome.marca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmarca;
	
	@Column(length = 128, nullable = false, unique = true)
	private String nombre;

	public Integer getIdmarca() {
		return idmarca;
	}

	public void setIdmarca(Integer idmarca) {
		this.idmarca = idmarca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Marca() {
		super();
	}

	public Marca(Integer idmarca, String nombre) {
		super();
		this.idmarca = idmarca;
		this.nombre = nombre;
	}

	public Marca(Integer idmarca) {
		super();
		this.idmarca = idmarca;
	}

	public Marca(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	
}
