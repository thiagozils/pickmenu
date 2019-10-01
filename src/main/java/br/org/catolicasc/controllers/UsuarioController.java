package br.org.catolicasc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.catolicasc.models.Usuario;
import br.org.catolicasc.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
	public String form() {
		return "usuario/formUsuario";
	}
	
	@RequestMapping("/usuarios")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("usuario/index");
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		mv.addObject("usuarios",usuarios);
		return mv;
	}
	
	@RequestMapping(value = "/editarUsuario", method = RequestMethod.GET)
	public ModelAndView editarUsuario(long codigo) {
		
		ModelAndView mv = new ModelAndView("usuario/formEditUsuario");
		Usuario usuario = usuarioRepository.findById(codigo);
		
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@RequestMapping(value = "/editarUsuario", method = RequestMethod.POST)
	public String editarUsuario(Usuario user) {
		
		System.out.println(user.getId()+" "+user.getNome());
		
		Usuario findUser = usuarioRepository.findById(user.getId());
		findUser.setNome(user.getNome());
		findUser.setEmail(user.getEmail());
		findUser.setSenha(user.getSenha());
		
		
		usuarioRepository.save(findUser);
		
		

		return "redirect:/usuarios";
	}

	
	


	

	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
	public String form(Usuario usuario) {
	
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}
	
	@RequestMapping("/deletarUsuario")
	public String deletarUsuario(long codigo){
		Usuario usuario = usuarioRepository.findById(codigo);
		usuarioRepository.delete(usuario);
		return "redirect:/usuarios";
	}
	
	

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

}
