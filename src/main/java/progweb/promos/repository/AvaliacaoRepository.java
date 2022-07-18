package progweb.promos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.promos.model.Avaliacao;
import progweb.promos.model.Promocao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
	
	List<Avaliacao> findByPromocao(Promocao promocao);
	List<Avaliacao> removeByPromocao(Promocao promocao);
	
}
