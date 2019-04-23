package com.dasa.exams.resource;

import java.util.List;

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
import com.dasa.exams.model.ExameModel;
import com.dasa.exams.model.LaboratorioModel;
@SpringBootApplication
@RestController
@RequestMapping("/apiExame")
public class ExameRestController {
	
	@Autowired
	private ExameDAO dao;
	
	@RequestMapping(value = "/exame/", method = RequestMethod.POST)
	public ResponseEntity<String> cadastrarExame(@RequestBody ExameModel exame){
		
		if (dao.isExameExist(exame)) {
			
			String msg = "Nao foi possivel incluir. O exame  " + exame.getNome() + " já existe.";
			return new ResponseEntity<String>( msg, HttpStatus.CONFLICT);
		}
		
		exame.setStatus(true);
		dao.insert(exame);
		return new ResponseEntity<String>(HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/exames/", method = RequestMethod.GET)
	public ResponseEntity<List<ExameModel>> listarTodosexames() {
		List<ExameModel> exames = dao.buscarTodosAtivos();
		if (exames.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<ExameModel>>(exames, HttpStatus.OK);
	}
	
	@RequestMapping(value = "exame/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> atualizarexame(@PathVariable("id") long id, @RequestBody ExameModel exame){
		
		ExameModel currentexame = dao.findById(id);
		
		if(currentexame == null) {
			String msg = "Nao foi possivel atualizar, o exame com o id " + id + " nao foi localizado";
			
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
		
		exame.setId(id);
		dao.update(exame);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/exame/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {

		ExameModel exame = dao.findById(id);
		if (exame == null) {
			String msg = "Nao foi possivel inativar. Exame nao encontrado";
			return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
		}
		
		exame.setStatus(false);
		dao.update(exame);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exame/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<LaboratorioModel>> buscaExamePorNome(@PathVariable("nome") String nome) {

		ExameModel exame = dao.buscaPorNome(nome);
		
		List<LaboratorioModel> relacionados = dao.buscaLaboratoriosRelacionados(exame);
		
		
		return new ResponseEntity<List<LaboratorioModel>>(relacionados, HttpStatus.OK);
	}
	
	

}
