package com.dasa.exams.util;

import com.dasa.exams.model.ExameModel;
import com.dasa.exams.model.LaboratorioModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class StatusProcessoBatch {

	@Expose
	private String status;
	@Expose
	private String motivo;
	@Expose
	private LaboratorioModel laboratorio;
	@Expose
	private ExameModel exame;

	/**
	 * Metodo responsavel por gerar um JSON com os atributos dessa classe
	 * 
	 * @return String
	 */
	public String toJSON() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public LaboratorioModel getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(LaboratorioModel laboratorio) {
		this.laboratorio = laboratorio;
	}

	public ExameModel getExame() {
		return exame;
	}

	public void setExame(ExameModel exame) {
		this.exame = exame;
	}

}
