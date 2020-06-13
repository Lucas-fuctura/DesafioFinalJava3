package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import entidade.Estudante;
import util.JpaUtil;

public class EstudanteDAOImpl implements EstudanteDAO {

	public void inserir(Estudante estudante) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.persist(estudante);
		
		tx.commit();
		ent.close();
	}
	
	public void alterar(Estudante estudante) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.merge(estudante);
		
		tx.commit();
		ent.close();
	}
	
	public void remover(Estudante estudante) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.remove(estudante);
		
		tx.commit();
		ent.close();
	}
	
	public Estudante pesquisar(String matricula) {
		EntityManager ent = JpaUtil.getEntityManager();
		Estudante estudante = ent.find(Estudante.class, matricula);
		return estudante;
	}
	
	public List<Estudante> listarTodos(){
		EntityManager ent = JpaUtil.getEntityManager();
		Query query = ent.createQuery("from Estudante e");
		List<Estudante> estudantes = query.getResultList();
		return estudantes;
	}
}
