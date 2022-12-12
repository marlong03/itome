package com.sistema.itome.prol;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idrol;
	
	@Column(length = 128, nullable = false, unique = true)
	private String nomre;

	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public String getNomre() {
		return nomre;
	}

	public void setNomre(String nomre) {
		this.nomre = nomre;
	}

	public Rol() {
		super();
	}

	public Rol(Integer idrol, String nomre) {
		super();
		this.idrol = idrol;
		this.nomre = nomre;
	}

	public Rol(Integer idrol) {
		super();
		this.idrol = idrol;
	}

	public Rol(String nomre) {
		super();
		this.nomre = nomre;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nomre;
	}

	
    
	
	
	
	
}
