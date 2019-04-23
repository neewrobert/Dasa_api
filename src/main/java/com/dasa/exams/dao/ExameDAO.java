package com.dasa.exams.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dasa.exams.model.ExameModel;
import com.dasa.exams.model.LaboratorioModel;

@Service("exameDao")
@Transactional
@Repository
public class ExameDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3188842289702199233L;

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<ExameModel> dao;

	@PostConstruct
	private void init() {

		this.dao = new GenericDAO<>(em, ExameModel.class);

	}

	public void insert(ExameModel t) {
		dao.insert(t);
	}

	public void remove(ExameModel t) {
		dao.remove(t);
	}

	public void update(ExameModel t) {
		dao.update(t);
	}

	public List<ExameModel> getAll() {
		return dao.getAll();
	}

	public ExameModel findById(long id) {
		return dao.findById(id);
	}

	public boolean isExameExist(ExameModel exame) {
		List<ExameModel> exames = dao.getAll();
		if (exames.isEmpty()) {
			return false;
		}
		for (ExameModel exameModel : exames) {
			if (exameModel.equals(exame)) {
				return true;
			}
		}

		return false;
	}

	public List<ExameModel> buscarTodosAtivos() {

		final Query query = em.createQuery(" SELECT e FROM ExameModel as e WHERE e.status = :ativo", ExameModel.class);
		query.setParameter("ativo", true);

		return query.getResultList();
	}

	public ExameModel buscaPorNome(String nome) {

		final Query query = em.createQuery(" SELECT e FROM ExameModel as e WHERE e.nome = :nome AND e.status = :ativo", ExameModel.class);
		query.setParameter("nome", nome);
		query.setParameter("ativo", true);

		return (ExameModel) query.getSingleResult();

	}

	public  List<LaboratorioModel> buscaLaboratoriosRelacionados(ExameModel exame) {

		final Query query = em.createQuery(" SELECT l from LaboratorioModel l inner join l.exames e where e.id = :id ");
		query.setParameter("id", exame.getId());

		return query.getResultList();

	}
	
	public ExameModel buscaPorIdExameAtivo(long id) {
		
		final Query query = em.createQuery(" SELECT e FROM ExameModel as e WHERE e.status = :ativo and e.id = :id", ExameModel.class);
		query.setParameter("ativo", true);
		query.setParameter("id", id);
		
		return (ExameModel) query.getSingleResult();
		
	}

}
