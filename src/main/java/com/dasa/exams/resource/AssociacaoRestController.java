package com.dasa.exams.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.exams.dao.ExameDAO;
import com.dasa.exams.dao.LaboratorioDAO;
import com.dasa.exams.model.ExameModel;
import com.dasa.exams.model.LaboratorioModel;

@SpringBootApplication
@RestController
@RequestMapping("/apiAssociacao")
public class AssociacaoRestController {
	
	@Autowired
	private ExameDAO exameDao;
	
	@Autowired
	private LaboratorioDAO laboratorioDao;
	
	
	@RequestMapping(value = "/associar/{idExame}/{idLaboratorio}", method = RequestMethod.PUT)
	public ResponseEntity<String> associarExame(@PathVariable("idExame") long idExame, @PathVariable("idLaboratorio") long idLaboratorio){
		
		ExameModel exame = exameDao.buscaPorIdExameAtivo(idExame);
		LaboratorioModel laboratorio = laboratorioDao.buscaPorIdLaboratorioAtivo(idLaboratorio);
		
		if(exame == null || laboratorio == null) {
			String msg =  "Os objetos Laboratorio e Exame devem estar Ativos";
			
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
		
		
		laboratorio.getExames().add(exame);
		laboratorioDao.update(laboratorio);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/desassociar/{idExame}/{idLaboratorio}", method = RequestMethod.PUT)
	public ResponseEntity<String> desassociarExame(@PathVariable("idExame") long idExame, @PathVariable("idLaboratorio") long idLaboratorio){
		
		LaboratorioModel laboratorio = laboratorioDao.findById(idLaboratorio);
		
		Set<ExameModel> exames = laboratorio.getExames();
		
		Iterator<ExameModel> i = exames.iterator();
		while (i.hasNext()) {
			ExameModel e = i.next();
			if(e.getId().equals(idExame)) {
				i.remove();
			}
		}
		
		laboratorio.setExames(exames);
		
		laboratorioDao.update(laboratorio);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
