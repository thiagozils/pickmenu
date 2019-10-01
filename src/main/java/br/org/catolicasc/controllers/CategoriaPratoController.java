package br.org.catolicasc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.catolicasc.models.CategoriaPrato;
import br.org.catolicasc.repository.CategoriaPratoRepository;

@Controller
public class CategoriaPratoController {

	@Autowired
	@Qualifier("categoriaPratoRepository")
	private CategoriaPratoRepository categoriaPratoRepository;

	@RequestMapping(value = "/cadastrarCategoriaPrato", method = RequestMethod.GET)
	public String form() {
		return "categoriaPrato/formCategoriaPrato";
	}
	
	@RequestMapping("/categoriasPratos")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("categoriaPrato/index");
		Iterable<CategoriaPrato> categoriasPratos = categoriaPratoRepository.findAll();
		mv.addObject("categoriasPratos",categoriasPratos);
		return mv;
	}
	
	@RequestMapping(value = "/editarCategoriaPrato", method = RequestMethod.GET)
	public ModelAndView editarCategoriaPrato(long codigo) {
		
		ModelAndView mv = new ModelAndView("categoriaPrato/formEditCategoriaPrato");
		CategoriaPrato categoriaPrato = categoriaPratoRepository.findById(codigo);
		
		mv.addObject("categoriaPrato", categoriaPrato);
		
		return mv;
	}
	
	@RequestMapping(value = "/editarCategoriaPrato", method = RequestMethod.POST)
	public String editarCategoriaPrato(CategoriaPrato categoriaPrato) {
		
		CategoriaPrato findCategoriaPrato = categoriaPratoRepository.findById(categoriaPrato.getId());
		findCategoriaPrato.setNome(categoriaPrato.getNome());
		findCategoriaPrato.setDescricao(categoriaPrato.getDescricao());
				
		categoriaPratoRepository.save(findCategoriaPrato);
		
		return "redirect:/categoriasPratos";
	}

	@RequestMapping(value = "/cadastrarCategoriaPrato", method = RequestMethod.POST)
	public String form(CategoriaPrato categoriaPrato) {
		categoriaPratoRepository.save(categoriaPrato);
		return "redirect:/categoriasPratos";
	}
	
	@RequestMapping("/deletarCategoriaPrato")
	public String deletarCategoriaPrato(long codigo){
		CategoriaPrato categoriaPrato = categoriaPratoRepository.findById(codigo);
		categoriaPratoRepository.delete(categoriaPrato);
		return "redirect:/categoriasPratos";
	}
	
	

	public void setCategoriaPratoRepository(CategoriaPratoRepository categoriaPratoRepository) {
		this.categoriaPratoRepository = categoriaPratoRepository;
	}

}
