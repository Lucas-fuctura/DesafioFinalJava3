package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Atividade;
import entidade.Materia;
import util.JpaUtil;

public class MateriaDAOImpl implements MateriaDAO {
	
	public void inserirMat(Materia materia) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.persist(materia);
		
		tx.commit();
		ent.close();
	}
	
	public void removerMat(Materia materia) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.remove(materia);
		
		tx.commit();
		ent.close();
	}
	
	public void alterarMat(Materia materia) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.merge(materia);
		
		tx.commit();
		ent.close();
	}
	
	public Materia pesquisarMat(Long id) {
		EntityManager ent = JpaUtil.getEntityManager();
		Materia materia = ent.find(Materia.class, id);
		return materia;
	}
	
	public List<Materia> listarMaterias(){
		EntityManager ent = JpaUtil.getEntityManager();
		Query query = ent.createQuery("from Materia m");
		List<Materia> materias = query.getResultList();
		return materias;
	}
	
	public void inserirAtv(Atividade atividade) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		Atividade achou = ent.find(Atividade.class, atividade.getId());
		
		ent.persist(achou);
		
		tx.commit();
		ent.close();
	}

	public void removerAtv(Atividade atividade) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		Atividade achou = ent.find(Atividade.class, atividade.getId());
		
		ent.remove(achou);
		
		tx.commit();
		ent.close();
	}
	
	public Atividade pesquisarAtv(Long id) {
		EntityManager ent = JpaUtil.getEntityManager();
		Atividade atividade = ent.find(Atividade.class, id);
		return atividade;
	}
	
	public List<Atividade> listarAtv(){
		EntityManager ent = JpaUtil.getEntityManager();
		Query query = ent.createQuery("from Atividade a");
		List<Atividade> atividades = query.getResultList();
		return atividades;
	}
}
