package progweb.promos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import progweb.promos.model.Avaliacao;
import progweb.promos.model.Promocao;
import progweb.promos.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Transactional
	public void salvar(Avaliacao avaliacao) {
		avaliacaoRepository.save(avaliacao);
	}
	
	@Transactional
	public void removerComPromocao(Promocao promocao) {
		avaliacaoRepository.removeByPromocao(promocao);
	}
	
}
