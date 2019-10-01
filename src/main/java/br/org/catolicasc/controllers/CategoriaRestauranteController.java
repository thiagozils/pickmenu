package br.org.catolicasc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.catolicasc.models.CategoriaRestaurante;
import br.org.catolicasc.repository.CategoriaRestauranteRepository;

@Controller
public class CategoriaRestauranteController {

	@Autowired
	@Qualifier("categoriaRestauranteRepository")
	private CategoriaRestauranteRepository categoriaRestauranteRepository;

	@RequestMapping(value = "/cadastrarCategoriaRestaurante", method = RequestMethod.GET)
	public String form() {
		return "categoriaRestaurante/formCategoriaRestaurante";
	}
	
	@RequestMapping("/categoriasRestaurantes")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("categoriaRestaurante/index");
		Iterable<CategoriaRestaurante> categoriasRestaurantes = categoriaRestauranteRepository.findAll();
		mv.addObject("categoriasRestaurantes", categoriasRestaurantes);
		return mv;
	}
	
	@RequestMapping(value = "/editarCategoriaRestaurante", method = RequestMethod.GET)
	public ModelAndView editarCategoriaRestaurante(long codigo) {
		
		ModelAndView mv = new ModelAndView("categoriaRestaurante/formEditCategoriaRestaurante");
		CategoriaRestaurante categoriaRestaurante = categoriaRestauranteRepository.findById(codigo);
		
		mv.addObject("categoriaRestaurante", categoriaRestaurante);
		
		return mv;
	}
	
	@RequestMapping(value = "/editarCategoriaRestaurante", method = RequestMethod.POST)
	public String editarCategoriaRestaurante(CategoriaRestaurante categoriaRestaurante) {
		
		CategoriaRestaurante findCategoriaRestaurante = categoriaRestauranteRepository.findById(categoriaRestaurante.getId());
		findCategoriaRestaurante.setNome(categoriaRestaurante.getNome());
		findCategoriaRestaurante.setDescricao(categoriaRestaurante.getDescricao());
				
		categoriaRestauranteRepository.save(findCategoriaRestaurante);
		
		return "redirect:/categoriasRestaurantes";
	}

	@RequestMapping(value = "/cadastrarCategoriaRestaurante", method = RequestMethod.POST)
	public String form(CategoriaRestaurante categoriaRestaurante) {
		
		System.out.println(categoriaRestaurante.getNome()+" TESTE"+categoriaRestaurante.getDescricao()+" "+categoriaRestaurante.getId());
		
		categoriaRestauranteRepository.save(categoriaRestaurante);
		return "redirect:/categoriasRestaurantes";
	}
	
	@RequestMapping("/deletarCategoriaRestaurante")
	public String deletarCategoriaRestaurante(long codigo){
		CategoriaRestaurante categoriaRestaurante = categoriaRestauranteRepository.findById(codigo);
		categoriaRestauranteRepository.delete(categoriaRestaurante);
		return "redirect:/categoriasRestaurantes";
	}
	
	

	public void setCategoriaRestauranteRepository(CategoriaRestauranteRepository categoriaRestauranteRepository) {
		this.categoriaRestauranteRepository = categoriaRestauranteRepository;
	}

}
