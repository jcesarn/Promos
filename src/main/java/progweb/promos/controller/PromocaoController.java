package progweb.promos.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import progweb.promos.model.Avaliacao;
import progweb.promos.model.Promocao;
import progweb.promos.repository.AvaliacaoRepository;
import progweb.promos.service.AvaliacaoService;
import progweb.promos.service.PromocaoService;

@Controller
public class PromocaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private PromocaoService promocaoService;
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@PostMapping("/visualizarpromo")
	public ModelAndView visualizarpromo(Promocao promocao, Avaliacao avaliacao) {
		ModelAndView mv = new ModelAndView("promocao/promocao");
		List<Avaliacao> avaliacoes = avaliacaoRepository.findByPromocao(promocao);
		promocao.setAvaliacoes(avaliacoes);
		promocao.setNotamedia(promocao.getAvaliacoes());
		logger.trace(avaliacoes.toString());
		mv.addObject("promocao", promocao);
		mv.addObject("avaliacao", avaliacao);
		return mv;
	}
	
	@GetMapping("/cadastrarpromo")
	public String direcionarParaInsercao(Promocao promocao) {
		return "promocao/novapromocao";
	}
	
	@PostMapping("/cadastrarpromo")
	public String cadastrarpromo(@Valid Promocao promocao, BindingResult result, RedirectAttributes atributos) {
		if (result.hasErrors()) {
			return "promocao/novapromocao";
		} else {
			promocaoService.salvar(promocao);
			atributos.addFlashAttribute("mensagem","Promoção cadastrada com sucesso!");
			return "redirect:/mostrarmensagem";
		}
	}
	
	@PostMapping("/confirmarremocao")
	public String confirmarremocao(Promocao promocao) {
		return "promocao/removerpromocao";
	}
	
	@PostMapping("/removerpromo")
	public ModelAndView removerpromo(Promocao promocao, RedirectAttributes atributos) {
		ModelAndView mv;
		avaliacaoService.removerComPromocao(promocao);
		promocaoService.remover(promocao);
		mv = new ModelAndView("redirect:/mostrarmensagem");
		atributos.addFlashAttribute("mensagem", "Promoção removida com sucesso!");
		return mv;
	}
	
	@PostMapping("/confirmaralteracao")
	public String alterarpromo(Promocao promocao) {
		return "promocao/alterarpromocao";
	}
	
	@PostMapping("/alterarpromo")
	public ModelAndView salvarpromoalterada(Promocao promocao, RedirectAttributes atributos) {
		ModelAndView mv;
		promocaoService.alterar(promocao);
		mv = new ModelAndView("redirect:/mostrarmensagem");
		atributos.addFlashAttribute("mensagem", "Promoção alterada com sucesso!");
		return mv;
	}

}
