package com.dasa.exams.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Exame")
@NamedNativeQuery(name="buscarelacionados", query= "from LaboratorioModel as lab left join fetch lab.exames where lab.exames.id = :id")
public class ExameModel implements Serializable{

	/**
	 * Essa classe representa a entidade Exame
	 */
	private static final long serialVersionUID = 3172144804919891720L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Expose
	private String nome;
	
	@Expose
	@Enumerated(EnumType.STRING)
	private TipoExame tipoExame;
	
	@Expose
	private boolean status;
	
	@Expose
	@ManyToMany(mappedBy="exames", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<LaboratorioModel> laboratorios = new HashSet<LaboratorioModel>();
	
	/**
	 * Metodo responsavel por gerar um JSON com os atributos dessa classe
	 * 
	 * @return String
	 */
	public String toJSON() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	} 
	
	public Set<LaboratorioModel> getLaboratorios() {
		return laboratorios;
	}

	public void setLaboratorios(Set<LaboratorioModel> laboratorios) {
		this.laboratorios = laboratorios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((tipoExame == null) ? 0 : tipoExame.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExameModel other = (ExameModel) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		if (tipoExame != other.tipoExame)
			return false;
		return true;
	}

	
	

}
