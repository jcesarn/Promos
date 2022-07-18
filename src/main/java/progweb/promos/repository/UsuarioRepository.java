package progweb.promos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import progweb.promos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNome(String nome);
	
}
