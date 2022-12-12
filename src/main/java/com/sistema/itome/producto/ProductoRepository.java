package com.sistema.itome.producto;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;





public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query("SELECT p FROM Producto p join p.marca m where"
			+ " concat(p.idproducto,p.nombre, p.precio, p.codigo, p.sock, m.nombre)"
		    +"  LIKE %?1%")
	public List<Producto> findAll(String productoClave);
	
	
}
