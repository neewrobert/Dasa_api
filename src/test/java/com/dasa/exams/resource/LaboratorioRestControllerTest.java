package com.dasa.exams.resource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dasa.exams.model.LaboratorioModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ComponentScan("com.dasa.exams")
public class LaboratorioRestControllerTest extends ApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private LaboratorioRestController controller;

	public final String REST_SERVICE_URI = "http://localhost:8080/apiLaboratorio";

	private LaboratorioModel lab;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * Testa se a conexao esta funcionando
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1_GetIndexRestController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/laboratorios/"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	public void test2_cadastrarLaboratorio() throws Exception {

		lab = new LaboratorioModel();
		lab.setId(1L);
		lab.setNome("Ferdinando Costa");
		lab.setEndereco("Rua Dr. Jose queiros de Aranha, 400");

		String labJson = lab.toJSON();

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(REST_SERVICE_URI + "/laboratorio/")
						.contentType(MediaType.APPLICATION_JSON).content(labJson))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void test2_listarLaboratorios() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/laboratorios/"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void test3_atualizarLaboratorio() throws Exception {

		lab = new LaboratorioModel();
		lab.setNome("Ferdinando Costa updated");
		lab.setEndereco("Rua Dr. Jose queiros de Aranha, 400");
		lab.setStatus(true);

		String json = lab.toJSON();

		this.mockMvc
				.perform(MockMvcRequestBuilders.put(REST_SERVICE_URI + "/laboratorio/1")
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void test4_desativarLaboratorio() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete(REST_SERVICE_URI + "/laboratorio/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
