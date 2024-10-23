package com.sesi.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sesi.login.model.Papel;
import com.sesi.login.model.Usuario;
import com.sesi.login.repository.PapelRepository;
import com.sesi.login.repository.UsuarioRepository;

@Controller
public class AutenticacaoController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private PapelRepository papelRepository;
	
	@Autowired
	private BCryptPasswordEncoder encodeSenha;
	
	@GetMapping("/home")
	public String home() {
		return "/home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/registrar")
	public String registrar(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return"/registrar";
	}
	
	@GetMapping("/registrar")
	public String registrarUsuario(@ModelAttribute Usuario usuario, Model modelo) {
		if(usuarioRepository.findbyNomeUsuario(usuario.getNomeUsuario()) != null) {
			modelo.addAttribute("mensagem", "nome do usuario já existe");
			return"registrar";
		}
		
		usuario.setSenha(encodeSenha.encode(usuario.getSenha()));
		Papel papelUsuario = papelRepository.findbyNomePapel("ROLER_USER");
		usuario.getPapeis().add(papelUsuario);
		usuarioRepository.save(usuario);
		return "redirect:/login";
	}
			
}
