package progweb.promos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao implements Serializable{

	private static final long serialVersionUID = -2469553889335928303L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idavaliacao;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpromocao", foreignKey = @ForeignKey(name = "fk_promo_aval"))
	private Promocao promocao;
	@ManyToOne
	@JoinColumn(name = "idusuario", foreignKey = @ForeignKey(name = "fk_usuario_aval"))
	private Usuario usuario;
//	@DecimalMin(value = "0", message = "A nota da avaliacção deve ser maior ou igual a 0")
//	@DecimalMax(value = "10", message = "A nota da avaliação deve ser menor ou igual a 10")
	private int nota;
//	@Size(min = 0, max = 600, message = "O comentário deve ter entre 0 e 600 caracteres")
	private String comentario;
	
	public Avaliacao() {
		
	}
	
	public Avaliacao(Promocao promocao, Usuario usuario, int nota, String comentario) {
		this.nota = nota;
		this.comentario = comentario;
		this.promocao = promocao;
		this.usuario = usuario;
	}
	
	public Avaliacao(long idavaliacao, Promocao promocao, Usuario usuario, int nota, String comentario) {
		this.idavaliacao = idavaliacao;
		this.nota = nota;
		this.comentario = comentario;
		this.promocao = promocao;
		this.usuario = usuario;
	}

	public long getIdavaliacao() {
		return idavaliacao;
	}

	public void setIdavaliacao(long idavaliacao) {
		this.idavaliacao = idavaliacao;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setcomentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Avaliacao [idavaliacao=" + idavaliacao + ", nota=" + nota + ", comentario=" + comentario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idavaliacao ^ (idavaliacao >>> 32));
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
		Avaliacao other = (Avaliacao) obj;
		if (idavaliacao != other.idavaliacao)
			return false;
		return true;
	}
	
}
