package com.sistema.itome.usuarios;

import org.springframework.data.domain.Page;

import java.io.UnsupportedEncodingException;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;





@Service
public interface UsuarioService extends UserDetailsService {
	public List<Usuario> listarUsuarios();
	public List<Usuario> findAll();
	public Page<Usuario> findAll(Pageable pageable);
	public void save(Usuario usuario,  String siteURL) throws UnsupportedEncodingException, MessagingException;
	public Usuario findOne(Integer id);
	public void deleate(Integer id);
	public List<Usuario> listAll(String usuarioClave);
	public void sendVerificationEmail(Usuario user, String siteURL) throws UnsupportedEncodingException, MessagingException;
	public boolean verify(String verificationCode);
	

}
