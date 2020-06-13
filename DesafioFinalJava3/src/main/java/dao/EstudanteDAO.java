package dao;

import java.util.List;

import entidade.Estudante;

public interface EstudanteDAO {

	public void inserir(Estudante estudante);
	
	public void alterar(Estudante estudante);
	
	public void remover(Estudante estudante);
	
	public Estudante pesquisar(String matricula);
	
	public List<Estudante> listarTodos();
}
