package progweb.promos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.promos.model.Promocao;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
	
	Promocao findByIdpromocao(Long id);
	
}
