package entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ATIVIDADE")
public class Atividade {
	
	@Id
	@Column(name="ID", nullable = false)
	@GeneratedValue(generator = "S_ID_ATIVIDADE")
	@SequenceGenerator(name="S_ID_ATIVIDADE", sequenceName = "S_ID_ATIVIDADE", allocationSize = 1)
	private Long id;
	
	@Column(name="TIPO", nullable = false)
	private String tipo;
	
	@Column(name="DATAENTREGA", nullable = false)
	private Date dataEntrega;
	
	@Column(name="PONTUACAO", nullable = false)
	private Long pontuacao;
	
	@Column(name="DETALHES", nullable = false)
	private String detalhes;
	
	@ManyToOne
	@JoinColumn(name="ID_MATERIA", referencedColumnName = "ID", nullable = false)
	private Materia materia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Long pontuacao) {
		this.pontuacao = pontuacao;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	

}
