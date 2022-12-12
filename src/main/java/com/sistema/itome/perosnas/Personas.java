package com.sistema.itome.perosnas;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 45, nullable = false, unique = true)
	private String nombres;
	@Column(length = 45, nullable = false, unique = true)
	private String apellidos;
	@Column(length = 45, nullable = false, unique = true)
	private Integer telefono;
	@Column(length = 45, nullable = false, unique = true)
	private String correo;
	@Column(length = 45, nullable = false, unique = true)
	private String direccion;
	
	@Column(length = 45, nullable = false, unique = true)
	private String Tipo_doc;
	@Column(length = 45, nullable = false, unique = true)
	private Integer num_doc;

	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public Integer getTelefono() {
		return telefono;
	}



	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}






	public String getTipo_doc() {
		return Tipo_doc;
	}



	public void setTipo_doc(String tipo_doc) {
		Tipo_doc = tipo_doc;
	}



	public Integer getNum_doc() {
		return num_doc;
	}



	public void setNum_doc(Integer num_doc) {
		this.num_doc = num_doc;
	}



	public Personas() {
		super();
	}



	public Personas(Integer id, String nombres, String apellidos, Integer telefono, String correo, String direccion,
			String tipo_doc, Integer num_doc) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
		this.Tipo_doc = tipo_doc;
		this.num_doc = num_doc;
	}



	public Personas(String nombres) {
		super();
		this.nombres = nombres;
	}
	
	
	
	
	
	
}
