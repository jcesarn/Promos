package progweb.promos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import progweb.promos.model.Avaliacao;
import progweb.promos.model.Usuario;
import progweb.promos.repository.PromocaoRepository;
import progweb.promos.repository.UsuarioRepository;
import progweb.promos.service.AvaliacaoService;

@Controller
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/avaliarpromo")
	public String avaliarpromo(@Valid Avaliacao avaliacao, BindingResult result, @RequestParam("idpromocao") String idpromocao, RedirectAttributes atributos) {
//		if (result.hasErrors()) {
//			atributos.addFlashAttribute("mensagem","Avaliação inválida!");
//			return "redirect:/mostrarmensagem";
//		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String nome = ((UserDetails)principal).getUsername();
			Usuario usuario = usuarioRepository.findByNome(nome);
			avaliacao.setUsuario(usuario);
			avaliacao.setPromocao(promocaoRepository.findByIdpromocao(Long.parseLong(idpromocao)));
			avaliacaoService.salvar(avaliacao);
			atributos.addFlashAttribute("mensagem","Avaliação feita com sucesso!");
			return "redirect:/mostrarmensagem";
//		}
	}
	
}
