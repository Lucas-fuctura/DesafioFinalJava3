package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ESTUDANTE")
public class Estudante {
	
	@Column(name="NOME", nullable = false)
	private String nome;
	
	@Id
	@Column(name="MATRICULA", nullable = false)
	private String matricula;
	
	@Column(name="TURNO", nullable = false)
	private String turno;
	
	@Column(name="SENHA", nullable = false)
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
