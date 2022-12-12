package com.sistema.itome.compra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




public interface ComprasRepository extends JpaRepository<Compras, Integer>{
	
	@Query("SELECT c FROM Compras c  join c.producto p  join c.proveedor pe where"
			+ " concat(c.idcompras, c.valor, c.cantidad, c.fecha_Compra,pe.nombres )"
		    +"  LIKE %?1% OR "
		    + "p.nombre LIKE %?1% " )
	public List<Compras> findAll(String comprasClave);

}
