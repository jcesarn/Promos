package progweb.promos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -1941591922114812580L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@NotBlank(message = "O usuário é obrigatório")
	@Size(min = 1, max = 255, message = "O usuário deve ter entre 1 e 255 caracteres")
	private String nome;
	@NotBlank(message = "O e-mail é obrigatório")
	@Email(message = "O e-mail deve ser bem formatado")
	private String email;
	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 1, max = 120, message = "A senha deve ter entre 1 e 120 caracteres")
	private String senha;
	@Column(name = "usuario")
	@NotBlank(message = "O nome do usuário é obrigatório")
	@Size(min = 1, max = 255, message = "O nome do usuário deve ter entre 1 e 255 caracteres")
	private String nomeUsuario;
	@Column(name = "data_nascimento")
	private String dataNascimento;
	private boolean ativo;
	@ManyToMany
	@JoinTable(name="usuario_papel",
			joinColumns=@JoinColumn(name = "codigo_usuario"),	
	        inverseJoinColumns=@JoinColumn(name="codigo_papel"))
	private List<Papel> papeis = new ArrayList<>();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void adicionarPapel(Papel papel) {
		papeis.add(papel);
	}
	
	public void removerPapel(Papel papel) {
		papeis.remove(papel);
	}
	
	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "codigo: " + codigo + "\nnome: " + nome + "\nemail: " + email + "\nsenha: " + senha + "\nusuario: "
				+ nomeUsuario + "\ndataNascimento: " + dataNascimento + "\nativo: " + ativo;
	}

}
