package br.org.catolicasc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.catolicasc.modelaux.PratoAux;
import br.org.catolicasc.modelaux.RestauranteAux;
import br.org.catolicasc.models.CategoriaPrato;
import br.org.catolicasc.models.CategoriaRestaurante;
import br.org.catolicasc.models.Prato;
import br.org.catolicasc.models.Restaurante;
import br.org.catolicasc.repository.CategoriaPratoRepository;
import br.org.catolicasc.repository.PratoRepository;
import br.org.catolicasc.repository.RestauranteRepository;

@Controller
public class PratoController {

	@Autowired
	@Qualifier("pratoRepository")
	private PratoRepository pratoRepository;
	
	@Autowired
	@Qualifier("categoriaPratoRepository")
	private CategoriaPratoRepository categoriaPratoRepository;
	

	@RequestMapping(value = "/cadastrarPrato", method = RequestMethod.GET)
	public ModelAndView form() {
		
		ModelAndView mv = new ModelAndView("prato/formPrato");
		Iterable<CategoriaPrato> categoriasPratos = categoriaPratoRepository.findAll();
		mv.addObject("categoriasPratos",categoriasPratos);
	
		return mv;
	}
	
	@RequestMapping("/pratos")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("prato/index");
		Iterable<Prato> pratos = pratoRepository.findAll();
		mv.addObject("pratos",pratos);
		return mv;
	}
	
	@RequestMapping(value = "/editarPrato", method = RequestMethod.GET)
	public ModelAndView editarPrato(long codigo) {
		
		ModelAndView mv = new ModelAndView("prato/formEditPrato");
		Prato prato = pratoRepository.findById(codigo);
		List<CategoriaPrato> categoriasPratos = categoriaPratoRepository.findAll();
		
		categoriasPratos.remove(prato.getCategoriaPrato());
		
		
		mv.addObject("categoriasPratos",categoriasPratos);
		mv.addObject("prato", prato);
		
		return mv;
	}
	
	@RequestMapping(value = "/editarPrato", method = RequestMethod.POST)
	public String editarPrato(PratoAux prato) {

		
		Prato findPrato = pratoRepository.findById(prato.getId());

		findPrato.setNome(prato.getNome());
		findPrato.setDescricao(prato.getDescricao());
		findPrato.setCalorias(prato.getCalorias());
		
		CategoriaPrato cp = categoriaPratoRepository.findById(prato.getCategoriaPrato());
		findPrato.setCategoriaPrato(cp);
		
		pratoRepository.save(findPrato);

		return "redirect:/pratos";
	}

	

	@RequestMapping(value = "/cadastrarPrato", method = RequestMethod.POST)
	public String form(PratoAux prato) {//, @RequestParam(value = "id") Integer ) {
		

		Prato pra = new Prato ();
		
		CategoriaPrato cp = categoriaPratoRepository.findById(prato.getCategoriaPrato());
		
		pra.setNome(prato.getNome());
		pra.setDescricao(prato.getDescricao());
		pra.setCalorias(prato.getCalorias());
		pra.setCategoriaPrato(cp);
		
		pratoRepository.save(pra);
		
		
		return "redirect:/pratos";
	}
	
	@RequestMapping("/deletarPrato")
	public String deletarPrato(long codigo){
		Prato prato = pratoRepository.findById(codigo);
		pratoRepository.delete(prato);
		return "redirect:/pratos";
	}



}
