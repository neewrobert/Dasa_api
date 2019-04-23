package com.dasa.exams.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dasa.exams.model.LaboratorioModel;

@Service("laboratorioDao")
@Transactional
@Repository
public class LaboratorioDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2859865746533394139L;

	@PersistenceContext
	EntityManager em;

	private GenericDAO<LaboratorioModel> dao;

	@PostConstruct
	public void init() {
		this.dao = new GenericDAO<>(this.em, LaboratorioModel.class);
	}

	public void insert(LaboratorioModel t) {
		dao.insert(t);
	}

	public void remove(LaboratorioModel t) {
		dao.remove(t);
	}

	public void update(LaboratorioModel t) {
		dao.update(t);
	}

	public List<LaboratorioModel> getAll() {
		return dao.getAll();
	}

	public LaboratorioModel findById(long id) {
		return dao.findById(id);
	}

	public boolean isLaboratorioExist(LaboratorioModel laboratorio) {
		List<LaboratorioModel> laboratorios = dao.getAll();
		if(laboratorios.isEmpty()){
			return false;
		}
		for (LaboratorioModel laboratoioModel : laboratorios) {
			if(laboratoioModel.equals(laboratorio)){
				return true;
			}
		}
		
		return false;
	}
	
	public List<LaboratorioModel> buscarTodosAtivos(){
		
		final Query query = em.createQuery(" SELECT l FROM LaboratorioModel as l WHERE l.status = :ativo", LaboratorioModel.class);
		query.setParameter("ativo", true);
		
		return query.getResultList();
		
	}
	
	public LaboratorioModel buscaPorIdLaboratorioAtivo(long id) {
		
		final Query query = em.createQuery(" SELECT l FROM LaboratorioModel as l WHERE l.status = :ativo and l.id = :id", LaboratorioModel.class);
		query.setParameter("ativo", true);
		query.setParameter("id", id);
		
		return (LaboratorioModel) query.getSingleResult();
		
	}

}
