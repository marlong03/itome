package com.sistema.itome.usuarios;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sistema.itome.prol.Rol;

import net.bytebuddy.utility.RandomString;



@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	 @Autowired
	 private JavaMailSender mailSender;
	 


	 
	@Override
	@Transactional
	public List<Usuario> findAll() {

		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(org.springframework.data.domain.Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Usuario usuario,  String siteURL) throws UnsupportedEncodingException, MessagingException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);
		String ramdomcode = RandomString.make(64);
		usuario.setVerificationCode(ramdomcode);
		usuario.setEnabled(false);
	
		sendVerificationEmail(usuario, siteURL);
		usuarioRepository.save(usuario);

	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void deleate(Integer id) {
		usuarioRepository.deleteById(id);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNombreusuario(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Nombre de usuario o password icorrecot ");
		}
		return new User(usuario.getNombreusuario(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNomre())).collect(Collectors.toList());

	}
	

	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listAll(String usuarioClave) {
		if(usuarioClave !=null) {
			return usuarioRepository.findAll(usuarioClave);	
			}
				
		return usuarioRepository.findAll();
	}

	@Override
	public void sendVerificationEmail(Usuario usuario, String siteURL) throws UnsupportedEncodingException, MessagingException {
	
	    String senderName = "Calzado lexter";
	    String subject = "Verificar cuenta";
	
	    String content = "Hola [[name]],<br>"
	            + "Por favor dar click abajo para poder verificar la cuenta:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">Verificar</a></h3>"
	            + "Gracias,<br>";
	  

	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("calzadolexter@gmail.com", senderName);
	    helper.setTo(usuario.getEmail());
	    helper.setSubject(subject);
	
	    content = content.replace("[[name]]", usuario.getNombre_apellido());
	    String verifyURL = siteURL + "/verify?code=" + usuario.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	    mailSender.send(message);
		
	}

	@Override
	public boolean verify(String verificationCode) {
		Usuario usuario = usuarioRepository.findByVerificationCode(verificationCode);
		if(usuario == null || usuario.isEnabled()) {
			return false;
		}else {
			usuarioRepository.enabled(usuario.getId());
			return true;
		}
	
	}


     
}
