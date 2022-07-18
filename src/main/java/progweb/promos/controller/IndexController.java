package progweb.promos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import progweb.promos.repository.PromocaoRepository;

@Controller
public class IndexController {
	
	@Autowired
	private PromocaoRepository promoRepository;
	
	@GetMapping(value = {"/", "/index.html"} )
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("promocoes", promoRepository.findAll());
		return mv;
	}
	
	@GetMapping("/mostrarmensagem")
	public ModelAndView mostratMensagem() {
		return new ModelAndView("mostrarmensagem");
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}