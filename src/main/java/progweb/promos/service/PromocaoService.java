package progweb.promos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import progweb.promos.model.Promocao;
import progweb.promos.repository.PromocaoRepository;

@Service
public class PromocaoService {

	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@Transactional
	public void salvar(Promocao promocao) {
		promocaoRepository.save(promocao);
	}
	
	@Transactional
	public void remover(Promocao promocao) {
		promocaoRepository.delete(promocao);
	}
	
	@Transactional
	public void alterar(Promocao promocao) {
		promocaoRepository.save(promocao);
	}
	
}
