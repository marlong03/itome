package com.sistema.itome.usuarios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;







public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	

	public Usuario  findByNombreusuario(String nombreusuario);
	
	@Query("SELECT u FROM Usuario u   join u.roles r where"
			+ " concat(u.id, u.nombreusuario, u.password, u.nombre_apellido)"
		    +"  LIKE %?1% OR"
		    + " r.nomre LIKE %?1% ")
	public List<Usuario> findAll(String usuarioClave);
	
	@Query("UPDATE Usuario u  SET u.enabled = true  WHERE u.id = ?1")
	@Modifying
	@Transactional
	public void enabled(Integer id);

	
	
	 @Query("SELECT u FROM Usuario u WHERE "
	 		+ "u.verificationCode = ?1")
     public Usuario findByVerificationCode(String code);

	 @Query("SELECT u FROM Usuario u  join  Rol r WHERE  r.nomre in :nomre  ")
	 List<Usuario>  findByRolnomre(@Param("nomre")String nomre);
	
	 Usuario findByEmail(String email);
}
