package com.dasa.exams.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.exams.dao.ExameDAO;
import com.dasa.exams.dao.LaboratorioDAO;
import com.dasa.exams.model.ExameModel;
import com.dasa.exams.model.LaboratorioModel;
import com.dasa.exams.util.StatusProcessoBatch;

@SpringBootApplication
@RestController
@RequestMapping("/batch")
public class BatchRestController {

	@Autowired
	private ExameDAO exameDao;

	@Autowired
	private LaboratorioDAO laboratorioDAO;

	@RequestMapping(value = "/cadastro/laboratorio", method = RequestMethod.POST)
	public ResponseEntity<List<StatusProcessoBatch>> cadastraLaboratorioBatch(
			@RequestBody List<LaboratorioModel> laboratorios) {

		List<StatusProcessoBatch> listaStatus = new ArrayList<>();

		for (LaboratorioModel lab : laboratorios) {

			if (!laboratorioDAO.isLaboratorioExist(lab)) {

				laboratorioDAO.insert(lab);
				StatusProcessoBatch status = new StatusProcessoBatch();
				status.setLaboratorio(lab);
				status.setStatus("Cadastrado");
				listaStatus.add(status);
			} else {
				StatusProcessoBatch status = new StatusProcessoBatch();
				status.setLaboratorio(lab);
				status.setStatus("Nao Cadastrado");
				status.setMotivo("Ja existe na base");
				listaStatus.add(status);
			}

		}

		return new ResponseEntity<List<StatusProcessoBatch>>(listaStatus, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/cadastro/exame", method = RequestMethod.POST)
	public ResponseEntity<List<StatusProcessoBatch>> cadastrarExameBatch(
			@RequestBody List<ExameModel> exames) {

		List<StatusProcessoBatch> listaStatus = new ArrayList<>();

		for (ExameModel exa : exames) {

			if (!exameDao.isExameExist(exa)) {

				exameDao.insert(exa);
				StatusProcessoBatch status = new StatusProcessoBatch();
				status.setExame(exa);
				status.setStatus("Cadastrado");
				listaStatus.add(status);
			} else {
				StatusProcessoBatch status = new StatusProcessoBatch();
				status.setExame(exa);
				status.setStatus("Nao Cadastrado");
				status.setMotivo("Ja existe na base");
				listaStatus.add(status);
			}

		}

		return new ResponseEntity<List<StatusProcessoBatch>>(listaStatus, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/atualizar/laboratorio", method = RequestMethod.POST)
	public ResponseEntity<List<StatusProcessoBatch>> atualizarLaboratorioBatch(
			@RequestBody List<LaboratorioModel> laboratorios) {

		List<StatusProcessoBatch> listaStatus = new ArrayList<>();

		for (LaboratorioModel lab : laboratorios) {
				laboratorioDAO.update(lab);
				StatusProcessoBatch status = new StatusProcessoBatch();
				status.setLaboratorio(lab);
				status.setStatus("Atualizado");
				listaStatus.add(status);

		}

		return new ResponseEntity<List<StatusProcessoBatch>>(listaStatus, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/atualizar/exame", method = RequestMethod.POST)
	public ResponseEntity<List<StatusProcessoBatch>> atualizarExameBatch(
			@RequestBody List<ExameModel> exames) {

		List<StatusProcessoBatch> listaStatus = new ArrayList<>();

		for (ExameModel exa : exames) {

				exameDao.update(exa);
				StatusProcessoBatch status = new StatusProcessoBatch();
				status.setExame(exa);
				status.setStatus("Atualizado");
				listaStatus.add(status);

		}

		return new ResponseEntity<List<StatusProcessoBatch>>(listaStatus, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/desativar/laboratorio", method = RequestMethod.POST)
	public ResponseEntity<List<StatusProcessoBatch>> desativarLaboratorioBatch(
			@RequestBody List<LaboratorioModel> laboratorios) {

		List<StatusProcessoBatch> listaStatus = new ArrayList<>();

		for (LaboratorioModel lab : laboratorios) {
				
				lab.setStatus(false);
				laboratorioDAO.update(lab);
				StatusProcessoBatch status = new StatusProcessoBatch();
				status.setLaboratorio(lab);
				status.setStatus("Desativado");
				listaStatus.add(status);

		}

		return new ResponseEntity<List<StatusProcessoBatch>>(listaStatus, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/desativar/exame", method = RequestMethod.POST)
	public ResponseEntity<List<StatusProcessoBatch>> desativarExameBatch(
			@RequestBody List<ExameModel> exames) {

		List<StatusProcessoBatch> listaStatus = new ArrayList<>();

		for (ExameModel exa : exames) {
				
				exa.setStatus(false);
				exameDao.update(exa);
				StatusProcessoBatch status = new StatusProcessoBatch();
				status.setExame(exa);
				status.setStatus("Desativado");
				listaStatus.add(status);

		}

		return new ResponseEntity<List<StatusProcessoBatch>>(listaStatus, HttpStatus.OK);

	}

}
