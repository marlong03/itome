package com.sistema.itome.perosnas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface PersonasRepository extends JpaRepository<Personas, Integer>{
	
	@Query("SELECT p FROM Personas p where"
			+ " concat(p.id, p.nombres, p.apellidos, p.telefono, p.correo, p.direccion, p.Tipo_doc, p.num_doc)"
		    +"  LIKE %?1%")
	public List<Personas> findAll(String palabraClave);

}
