package com.sistema.itome.pedido;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;






public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	@Query("SELECT pd FROM Pedido pd  join pd.producto p join pd.usuario u join pd.formpago fp  where"
			+ " concat(pd.idpedidos, pd.cantidad, pd.total, u.nombreusuario, fp.nombre )"
		    +"  LIKE %?1% OR "
		    + "p.nombre LIKE %?1% " )
	public List<Pedido> findAll(String pedidoClave);


}
