package com.sesi.login.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sesi.login.model.Usuario;
import com.sesi.login.repository.UsuarioRepository;

public class DetalhesUsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findbyNomeUsuario();
		
		if(usuario == null) {
			throw new UsernameNotFoundException("usuario não encontrado");	
		}
		return User(usuario.getNomeUsuario(),
				usuario.getSenha(),
				
			);
				
	}

}