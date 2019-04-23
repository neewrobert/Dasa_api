package com.dasa.exams.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Laboratorio")
public class LaboratorioModel implements Serializable {

	/**
	 * Essa classe representa a entidade Laboratorio
	 * 
	 * @author Newton santos
	 * @since 0.0.1
	 */
	private static final long serialVersionUID = -5334192699191107933L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Expose
	private String nome;

	@Expose
	private String endereco;

	@Expose
	private boolean status;

	@Expose
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "laboratorio_has_exames", joinColumns = { @JoinColumn(name = "laboratorio_id") }, inverseJoinColumns = {
	@JoinColumn(name = "exame_id") })
	private Set<ExameModel> exames = new HashSet<ExameModel>();

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public Set<ExameModel> getExames() {
		return exames;
	}

	public void setExames(Set<ExameModel> exames) {
		this.exames = exames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (status ? 1231 : 1237);
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
		LaboratorioModel other = (LaboratorioModel) obj;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
