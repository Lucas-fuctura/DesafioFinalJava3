package entidade;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MATERIA")
public class Materia {
	
	@Id
	@Column(name="ID", nullable = false)
	@GeneratedValue(generator = "S_ID_MATERIA")
	@SequenceGenerator(name="S_ID_MATERIA", sequenceName = "S_ID_MATERIA", allocationSize = 1)
	private Long id;
	
	@Column(name="NOME", nullable = false)
	private String nome;
	
	@Column(name="PROFESSOR", nullable = false)
	private String professor;
	
	@OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
	private List<Atividade> atividades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

}
