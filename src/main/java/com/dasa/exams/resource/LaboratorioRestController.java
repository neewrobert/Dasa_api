package com.dasa.exams.resource;

import java.io.Serializable;
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

import com.dasa.exams.dao.LaboratorioDAO;
import com.dasa.exams.model.LaboratorioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Laboratorio")
@SpringBootApplication
@RestController
@RequestMapping("/apiLaboratorio")
public class LaboratorioRestController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5505028781740727885L;
	
	
	
	@Autowired
	private LaboratorioDAO dao;
	
	
	@ApiOperation(value = "Cadastra um laboratorio")
	@RequestMapping(value = "/laboratorio/", method = RequestMethod.POST)
	public ResponseEntity<String> cadastraLaboratorio(@RequestBody LaboratorioModel laboratorio){
		
		if (dao.isLaboratorioExist(laboratorio)) {
			
			String msg = "Nao foi possivel incluir. O Laboratorio  " + laboratorio.getNome() + " já existe.";
			return new ResponseEntity<String>( msg, HttpStatus.CONFLICT);
		}
		laboratorio.setStatus(true);
		dao.insert(laboratorio);
		return new ResponseEntity<String>(HttpStatus.CREATED);
		
	}
	
	@ApiOperation(value = "Lista todos os laboratorios ativos")
	@RequestMapping(value = "/laboratorios/", method = RequestMethod.GET)
	public ResponseEntity<List<LaboratorioModel>> listarTodosLaboratorios() {
		List<LaboratorioModel> laboratorios = dao.buscarTodosAtivos();
		if (laboratorios.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<LaboratorioModel>>(laboratorios, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Atualiza um laboratorio, caso nao exista, ira ser incluido um novo")
	@RequestMapping(value = "laboratorio/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> atualizarLaboratorio(@PathVariable("id") long id, @RequestBody LaboratorioModel laboratorio){
		
		LaboratorioModel currentLaboratorio = dao.findById(id);
		
		if(currentLaboratorio == null) {
			String msg = "Nao foi possivel atualiza, o laboratorio com o id " + id + " nao foi localizado";
			
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
		
		laboratorio.setId(id);
		dao.update(laboratorio);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Desativa um laboratorio")
	@RequestMapping(value = "/laboratorio/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {

		LaboratorioModel laboratorio = dao.findById(id);
		if (laboratorio == null) {
			String msg = "Nao foi possivel inativar. Laboratorio nao encontrado";
			return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
		}
		
		laboratorio.setStatus(false);
		dao.update(laboratorio);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	

}
