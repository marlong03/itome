package com.sistema.itome.pedido;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.sistema.itome.fomaPago.Formpago;
import com.sistema.itome.producto.Producto;
import com.sistema.itome.usuarios.Usuario;







@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpedidos;
	

	private Integer total;
	
	@Column(length = 45, nullable = false)
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "formpago_id")
	private Formpago formpago;

	@ManyToMany
	@JoinTable(name = "pedido_has_producto", joinColumns = @JoinColumn(name = "pedido_idpedidos"),  
	inverseJoinColumns = @JoinColumn(name = "producto_idproducto"))
	private Collection<Producto> producto = new HashSet<>();




	public Pedido(Integer idpedidos, Integer total, Integer cantidad, Usuario usuario, Formpago formpago,
			Collection<Producto> producto) {
		super();
		this.idpedidos = idpedidos;
		this.total = total;
		this.cantidad = cantidad;
		this.usuario = usuario;
		this.formpago = formpago;
		this.producto = producto;
	}




	



	public Integer getIdpedidos() {
		return idpedidos;
	}








	public void setIdpedidos(Integer idpedidos) {
		this.idpedidos = idpedidos;
	}








	public Integer getTotal() {
		return total;
	}















	public void setTotal(Integer total) {
		this.total = total;
	}








	public Integer getCantidad() {
		return cantidad;
	}








	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}








	public Usuario getUsuario() {
		return usuario;
	}








	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}








	public Formpago getFormpago() {
		return formpago;
	}








	public void setFormpago(Formpago formpago) {
		this.formpago = formpago;
	}








	public Collection<Producto> getProducto() {
		return producto;
	}








	public void setProducto(Collection<Producto> producto) {
		this.producto = producto;
	}








	public Pedido() {
		super();
	}








	public void setTotal(Producto total2) {
		// TODO Auto-generated method stub
		
	}














	
	
	
	
	
	
}
