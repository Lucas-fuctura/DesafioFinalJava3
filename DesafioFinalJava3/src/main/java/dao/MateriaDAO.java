package dao;

import java.util.List;

import entidade.Atividade;
import entidade.Materia;

public interface MateriaDAO {
	
	public void inserirMat(Materia materia);
	
	public void removerMat(Materia materia);
	
	public void alterarMat(Materia materia);
	
	public Materia pesquisarMat(Long id);
	
	public List<Materia> listarMaterias();
	
	public void inserirAtv(Atividade atividade);
	
	public void removerAtv(Atividade atividade);
	
	public List<Atividade> listarAtv();
	
	public Atividade pesquisarAtv(Long id);
	
	

}
