package br.org.catolicasc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.catolicasc.models.Cardapio;
import br.org.catolicasc.models.Prato;
import br.org.catolicasc.models.Restaurante;
import br.org.catolicasc.repository.CardapioRepository;
import br.org.catolicasc.repository.PratoRepository;
import br.org.catolicasc.repository.RestauranteRepository;

@Controller
public class IndexController {

	@Autowired
	@Qualifier("restauranteRepository")
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	@Qualifier("cardapioRepository")
	private CardapioRepository cardapioRepository;
	
	@Autowired
	@Qualifier("pratoRepository")
	private PratoRepository pratoRepository;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("index");
		Iterable<Restaurante> restaurantes = restauranteRepository.findAll();
		mv.addObject("restaurantes",restaurantes);
		
		return mv;
	}
	

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView index(int codigo) {
		
		
		
		ModelAndView mv = new ModelAndView("result");
		long id = (long) codigo;
		Restaurante restaurante = restauranteRepository.findById(id);
		
		mv.addObject("cardapios",restaurante.getCardapios());
		
		return mv;
	}

	
	@RequestMapping(value = "/detalhesPesquisa", method = RequestMethod.GET)
	public ModelAndView detalhes(long codigo) {
		
		ModelAndView mv = new ModelAndView("detalhesPesquisa");
		Cardapio cardapio = cardapioRepository.findById(codigo);
		

		List<Prato> pratos = pratoRepository.findAll();
		
		
		mv.addObject("cardapio", cardapio);

		return mv;
	}
	
	

}
