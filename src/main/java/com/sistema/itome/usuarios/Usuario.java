package com.sistema.itome.usuarios;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.lang.NonNull;

import com.sistema.itome.prol.Rol;



@Entity
public class Usuario  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	@NonNull
	@Column(length = 45, nullable = false,   unique = true)
	private String nombreusuario;
	@Column(length = 45, nullable = false,   unique = true)
	private String email;
	
	@Column(name = "verification_code", length = 64)
    private String verificationCode;
     
    private boolean enabled;
    
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre_apellido() {
		return nombre_apellido;
	}

	public void setNombre_apellido(String nombre_apellido) {
		this.nombre_apellido = nombre_apellido;
	}

	@Column(length = 65, nullable = false)
	private String password;

	@Column(length = 65, nullable = false)
	private String nombre_apellido;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rol_has_usuario", joinColumns = @JoinColumn(name = "usuario_id"),  
	inverseJoinColumns = @JoinColumn(name = "rol_idrol"))
	private Set<Rol> roles = new HashSet<>();

	  public boolean hasRole(String roleName) {
	        Iterator<Rol> iterator = this.roles.iterator();
	        while (iterator.hasNext()) {
	        	Rol role = iterator.next();
	            if (role.getNomre().equals(roleName)) {
	                return true;
	            }
	        }
	         
	        return false;
	    }
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	
	public Usuario() {
		super();
	}

	public Usuario(String nombreusuario) {
		super();
		this.nombreusuario = nombreusuario;
	}

	public Usuario(Integer id) {
		super();
		this.id = id;
	}
	



}
