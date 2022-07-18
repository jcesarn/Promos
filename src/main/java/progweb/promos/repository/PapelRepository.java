package progweb.promos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.promos.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	
}
