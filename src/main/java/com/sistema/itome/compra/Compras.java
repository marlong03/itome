package com.sistema.itome.compra;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.sistema.itome.fomaPago.Formpago;
import com.sistema.itome.perosnas.Personas;
import com.sistema.itome.producto.Producto;



@Entity
public class Compras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcompras;
	
	@Column(length = 45, nullable = false, unique = true)
	private Integer valor;
	
	@Column(length = 45, nullable = false, unique = true)
	private Integer cantidad;
	@Column(length = 45, nullable = false, unique = true)
	private Date fecha_Compra;
	
	
	@ManyToOne
	@JoinColumn(name = "personas_id")
	private Personas proveedor;

	@ManyToOne
	@JoinColumn(name = "formpago_id")
	private Formpago formpago;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "producto_has_compras", joinColumns = @JoinColumn(name = "compras_idcompras"),  
	inverseJoinColumns = @JoinColumn(name = "producto_idproducto"))
	private Collection<Producto> producto = new HashSet<>();



	

	public Integer getIdcompras() {
		return idcompras;
	}

	public void setIdcompras(Integer idcompras) {
		this.idcompras = idcompras;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha_Compra() {
		return fecha_Compra;
	}

	public void setFecha_Compra(Date fecha_Compra) {
		this.fecha_Compra = fecha_Compra;
	}

	public Personas getProveedor() {
		return proveedor;
	}

	public void setProveedor(Personas proveedor) {
		this.proveedor = proveedor;
	}

	public Formpago getFormpago() {
		return formpago;
	}

	public void setFormpago(Formpago formpago) {
		this.formpago = formpago;
	}


	public Compras(Integer idcompras) {
		super();
		this.idcompras = idcompras;
	}

	public Compras() {
		super();
	}

	public Compras(Integer idcompras, Integer valor, Integer cantidad, Date fecha_Compra, Personas proveedor,
			Formpago formpago, Collection<Producto> producto) {
		super();
		this.idcompras = idcompras;
		this.valor = valor;
		this.cantidad = cantidad;
		this.fecha_Compra = fecha_Compra;
		this.proveedor = proveedor;
		this.formpago = formpago;
		this.producto = producto;
	}

	public Collection<Producto> getProducto() {
		return producto;
	}

	public void setProducto(Collection<Producto> producto) {
		this.producto = producto;
	}
	


	
	


	



	



	
	
	
	
	
	
	
}
