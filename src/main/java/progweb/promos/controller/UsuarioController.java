package progweb.promos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import progweb.promos.model.Papel;
import progweb.promos.model.Usuario;
import progweb.promos.repository.PapelRepository;
import progweb.promos.service.CadastroUsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/cadastrarusuario")
	public ModelAndView direcionarCadastroUsuario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/cadastrousuario");
		List<Papel> papeis = papelRepository.findAll();
		mv.addObject("todosPapeis", papeis);
		return mv;
	}
	
	@PostMapping("/cadastrarusuario")
	public ModelAndView cadastrarNovoUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes atributos) {
		ModelAndView mv;
		if (result.hasErrors()) {
			mv = new ModelAndView("usuario/cadastrousuario");
			if (usuario.getPapeis().isEmpty()) {
				List<Papel> papeis = papelRepository.findAll();
				mv = new ModelAndView("usuario/cadastrousuario");
				mv.addObject("todosPapeis", papeis);
			}
		} else {
			if (!usuario.getPapeis().isEmpty()) {
				usuario.setAtivo(true);
				usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
				cadastroUsuarioService.salvar(usuario);
				atributos.addFlashAttribute("mensagem","Usuário(a) cadastrado(a) com sucesso!");
				mv = new ModelAndView ("redirect:/mostrarmensagem");
			} else {
				List<Papel> papeis = papelRepository.findAll();
				mv = new ModelAndView("usuario/cadastrousuario");
				mv.addObject("todosPapeis", papeis);
			}
		}
		return mv;
	}
	
}
