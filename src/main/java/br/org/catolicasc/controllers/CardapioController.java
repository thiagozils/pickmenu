package br.org.catolicasc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.catolicasc.modelaux.CardapioAux;
import br.org.catolicasc.models.Cardapio;
import br.org.catolicasc.models.Prato;
import br.org.catolicasc.models.Restaurante;
import br.org.catolicasc.repository.CardapioRepository;
import br.org.catolicasc.repository.PratoRepository;
import br.org.catolicasc.repository.RestauranteRepository;

@Controller
public class CardapioController {

	@Autowired
	@Qualifier("cardapioRepository")
	private CardapioRepository cardapioRepository;

	@Autowired
	@Qualifier("restauranteRepository")
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	@Qualifier("pratoRepository")
	private PratoRepository pratoRepository;
	

	@RequestMapping(value = "/cadastrarCardapio", method = RequestMethod.GET)
	public ModelAndView form() {
		
		ModelAndView mv = new ModelAndView("cardapio/formCardapio");
		
		Iterable<Restaurante> restaurantes = restauranteRepository.findAll();
		Iterable<Prato> pratos = pratoRepository.findAll();

		mv.addObject("restaurantes",restaurantes);
		mv.addObject("pratos",pratos);

		return mv;
	}
	
	@RequestMapping("/cardapios")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("cardapio/index");
		Iterable<Cardapio> cardapios = cardapioRepository.findAll();
		mv.addObject("cardapios",cardapios);
		return mv;
	}
	
	
	@RequestMapping(value = "/detalhes", method = RequestMethod.GET)
	public ModelAndView detalhes(long codigo) {
		
		ModelAndView mv = new ModelAndView("cardapio/detalhesCardapio");
		Cardapio cardapio = cardapioRepository.findById(codigo);
		

		List<Prato> pratos = pratoRepository.findAll();
		
		
		mv.addObject("cardapio", cardapio);

		return mv;
	}
	
	
	@RequestMapping(value = "/editarCardapio", method = RequestMethod.GET)
	public ModelAndView editarCardapio(long codigo) {
		
		ModelAndView mv = new ModelAndView("cardapio/formEditCardapio");
		Cardapio cardapio = cardapioRepository.findById(codigo);
		
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		restaurantes.remove(cardapio.getRestaurante());
		List<Prato> pratos = pratoRepository.findAll();
		
		
		mv.addObject("cardapio", cardapio);
		mv.addObject("restaurantes",restaurantes);
		mv.addObject("pratos",pratos);

		return mv;
	}
	
	@RequestMapping(value = "/editarCardapio", method = RequestMethod.POST)
	public String editarCardapio(CardapioAux cardapio) {
		

		Cardapio car = cardapioRepository.findById(cardapio.getId());
		
		System.out.println(cardapio.getId()+" é o id");
		
		car.setPreco(cardapio.getPreco());
		car.setData(cardapio.getData());
		
		Restaurante res = restauranteRepository.findById(cardapio.getRestaurante());
		car.setRestaurante(res);
	
		String pratosCar = cardapio.getPratos();
		String array[] = pratosCar.split(",");
		
		
		List<Prato> pratos = new ArrayList<Prato>();
		for (int i=0; i <= array.length-1;i++) {
		
			long cod = (long)Double.parseDouble(array[i].replaceAll(",", ""));
			Prato prato = pratoRepository.findById(cod);
			pratos.add(prato);	
		}

		car.setPratos(pratos);
		cardapioRepository.save(car);
		
		return "redirect:/cardapios";
	}

	@RequestMapping(value = "/cadastrarCardapio", method = RequestMethod.POST)
	public String form(CardapioAux cardapio) {
		
		Cardapio car = new Cardapio();
		
		car.setPreco(cardapio.getPreco());
		car.setData(cardapio.getData());
		
		Restaurante res = restauranteRepository.findById(cardapio.getRestaurante());
		car.setRestaurante(res);
	
		
		String pratosCar = cardapio.getPratos();
		String array[] = pratosCar.split(",");
		
		
		List<Prato> pratos = new ArrayList<Prato>();
		for (int i=0; i <= array.length-1;i++) {
		
			long cod = (long)Double.parseDouble(array[i].replaceAll(",", ""));
			Prato prato = pratoRepository.findById(cod);
			pratos.add(prato);
			
		}
		
		car.setPratos(pratos);
		cardapioRepository.save(car);
		return "redirect:/cardapios";
	}
	
	@RequestMapping("/deletarCardapio")
	public String deletarCardapio(long codigo){
		Cardapio cardapio = cardapioRepository.findById(codigo);
		cardapioRepository.delete(cardapio);
		return "redirect:/cardapios";
	}	

	public void setCardapioRepository(CardapioRepository cardapioRepository) {
		this.cardapioRepository = cardapioRepository;
	}

}
