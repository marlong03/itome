package com.sistema.itome.producto;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;



import com.sistema.itome.marca.Marca;



@Entity
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproducto;
	
	@Column(length = 45, nullable = false, unique = true)
	private String nombre;
	
	@Column(length = 45, nullable = false, unique = true)
	private String codigo;
	
	@Column(length = 45, nullable = false, unique = true)
	private float precio;
	
	@Column(length = 45, nullable = false, unique = true)
	private float sock;
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Column(nullable = true, length = 64)
	private String foto;
	
	
	@ManyToOne
	@JoinColumn(name = "marca_idmarca")
	private Marca marca;

	@Transient
	public Integer getIdproducto() {
		return idproducto;
	}
	private String rutaFoto;


	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public float getSock() {
		return sock;
	}


	public void setSock(float sock) {
		this.sock = sock;
	}


	public Marca getMarca() {
		return marca;
	}


	public void setMarca(Marca marca) {
		this.marca = marca;
	}


	


	


	public String getFoto() {
		return foto;
	}


	

	public Producto() {
		super();
	}


	public Producto(Integer idproducto) {
		super();
		this.idproducto = idproducto;
	}


	public Producto(String nombre) {
		super();
		this.nombre = nombre;
	}


	public String getRutaFoto() {
		return rutaFoto;
	}


	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}


	public Producto(Integer idproducto, String nombre, String codigo, float precio, float sock
			) {
		super();
		this.idproducto = idproducto;
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.sock = sock;
		

	}


	



	

	
	
	
	
	
	
}
