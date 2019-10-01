package br.org.catolicasc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.catolicasc.modelaux.RestauranteAux;
import br.org.catolicasc.models.CategoriaRestaurante;
import br.org.catolicasc.models.Restaurante;
import br.org.catolicasc.repository.CategoriaRestauranteRepository;
import br.org.catolicasc.repository.RestauranteRepository;

@Controller
public class RestauranteController {

	@Autowired
	@Qualifier("restauranteRepository")
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	@Qualifier("categoriaRestauranteRepository")
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	

	@RequestMapping(value = "/cadastrarRestaurante", method = RequestMethod.GET)
	public ModelAndView form() {
		
		ModelAndView mv = new ModelAndView("restaurante/formRestaurante");
		Iterable<CategoriaRestaurante> categoriasRestaurantes = categoriaRestauranteRepository.findAll();
		mv.addObject("categoriasRestaurantes",categoriasRestaurantes);
	
		return mv;
	}
	
	@RequestMapping("/restaurantes")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("restaurante/index");
		Iterable<Restaurante> restaurantes = restauranteRepository.findAll();
		mv.addObject("restaurantes",restaurantes);
		return mv;
	}
	
	@RequestMapping(value = "/editarRestaurante", method = RequestMethod.GET)
	public ModelAndView editarRestaurante(long codigo) {
		
		ModelAndView mv = new ModelAndView("restaurante/formEditRestaurante");
		Restaurante restaurante = restauranteRepository.findById(codigo);
		List<CategoriaRestaurante> categoriasRestaurantes = categoriaRestauranteRepository.findAll();
		
		categoriasRestaurantes.remove(restaurante.getCategoriaRestaurante());
		
		
		mv.addObject("categoriasRestaurantes",categoriasRestaurantes);
		mv.addObject("restaurante", restaurante);
		
		return mv;
	}
	
	@RequestMapping(value = "/editarRestaurante", method = RequestMethod.POST)
	public String editarRestaurante(RestauranteAux restaurante) {

		
		Restaurante findRestaurante = restauranteRepository.findById(restaurante.getId());
		findRestaurante.setNome(restaurante.getNome());
		findRestaurante.setEndereco(restaurante.getEndereco());
		findRestaurante.setCidade(restaurante.getCidade());
		findRestaurante.setTelefone(restaurante.getTelefone());
		CategoriaRestaurante cr = categoriaRestauranteRepository.findById(restaurante.getCategoriaRestaurante());
		findRestaurante.setCategoriaRestaurante(cr);
		
		restauranteRepository.save(findRestaurante);

		return "redirect:/restaurantes";
	}

	

	@RequestMapping(value = "/cadastrarRestaurante", method = RequestMethod.POST)
	public String form(RestauranteAux restaurante) {//, @RequestParam(value = "id") Integer ) {
		
		System.out.println("Codigo: "+restaurante.getCategoriaRestaurante());
		Restaurante res = new Restaurante ();
		
		CategoriaRestaurante cr = categoriaRestauranteRepository.findById(restaurante.getCategoriaRestaurante());
		
		
		res.setNome(restaurante.getNome());
		res.setCidade(restaurante.getCidade());
		res.setEndereco(restaurante.getEndereco());
		res.setTelefone(restaurante.getTelefone());
		res.setCategoriaRestaurante(cr);

		restauranteRepository.save(res);
		return "redirect:/restaurantes";
	}
	
	@RequestMapping("/deletarRestaurante")
	public String deletarRestaurante(long codigo){
		Restaurante restaurante = restauranteRepository.findById(codigo);
		restauranteRepository.delete(restaurante);
		return "redirect:/restaurantes";
	}

	public RestauranteRepository getRestauranteRepository() {
		return restauranteRepository;
	}

	public void setRestauranteRepository(RestauranteRepository restauranteRepository) {
		this.restauranteRepository = restauranteRepository;
	}



}
