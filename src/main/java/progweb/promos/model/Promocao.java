package progweb.promos.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "promocoes")
public class Promocao implements Serializable{

	private static final long serialVersionUID = -4059047736590123778L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idpromocao;
	@NotBlank(message = "O nome do produto é obrigatório")
	@Size(min = 1, max = 255, message = "O nome do produto deve ter entre 1 e 255 caracteres")
	private String nomeproduto;
	@DecimalMin(value = "0", message = "O preço do produto deve ser maior ou igual a 0")
	private double precoproduto;
	@DecimalMin(value = "0", message = "A porcentagem de desconto do produto deve ser maior ou igual a 0")
	private int porcentagemdesconto;
	@Transient
	private List<Avaliacao> avaliacoes = new LinkedList<>();
	@Transient
	private float notamedia;
	
	public Promocao() {
		
	}
	
	public Promocao(String nomeproduto, double precoproduto, int porcentagemdesconto) {
		this.nomeproduto = nomeproduto;
		this.precoproduto = precoproduto;
		this.porcentagemdesconto = porcentagemdesconto;
	}
	
	public Promocao(long idpromocao, String nomeproduto, double precoproduto, int porcentagemdesconto) {
		this.idpromocao = idpromocao;
		this.nomeproduto = nomeproduto;
		this.precoproduto = precoproduto;
		this.porcentagemdesconto = porcentagemdesconto;
	}

	public long getIdpromocao() {
		return idpromocao;
	}

	public void setIdpromocao(long idpromocao) {
		this.idpromocao = idpromocao;
	}

	public String getNomeproduto() {
		return nomeproduto;
	}

	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}

	public double getPrecoproduto() {
		return precoproduto;
	}

	public void setPrecoproduto(double precoproduto) {
		this.precoproduto = precoproduto;
	}

	public int getPorcentagemdesconto() {
		return porcentagemdesconto;
	}

	public void setPorcentagemdesconto(int porcentagemdesconto) {
		this.porcentagemdesconto = porcentagemdesconto;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public float getNotamedia() {
		return notamedia;
	}

	public void setNotamedia(List<Avaliacao> avaliacoes) {
		int qnt = 0;
		float media = 0;
		for(Avaliacao a : avaliacoes) {
			media = media + a.getNota();
			qnt++;
		}
		this.notamedia = media/qnt;
	}

	@Override
	public String toString() {
		return "Promocao [idpromocao=" + idpromocao + ", nomeproduto=" + nomeproduto + ", precoproduto=" + precoproduto
				+ ", porcentagemdesconto=" + porcentagemdesconto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idpromocao ^ (idpromocao >>> 32));
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
		Promocao other = (Promocao) obj;
		if (idpromocao != other.idpromocao)
			return false;
		return true;
	}
	
}
