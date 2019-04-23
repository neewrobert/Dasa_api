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

import com.dasa.exams.model.ExameModel;
import com.dasa.exams.model.TipoExame;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ComponentScan("com.dasa.exams")
public class ExameRestControllerTest extends ApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private ExameRestController controller;

	public final String REST_SERVICE_URI = "http://localhost:8080/apiExame";

	private ExameModel exame;
	
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
		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/exames/"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	public void test2_cadastrarExame() throws Exception {

		exame = new ExameModel();
		exame.setId(1L);
		exame.setNome("biopsia");
		exame.setTipoExame(TipoExame.ANALISE_CLINICA);
		exame.setStatus(true);

		String json = exame.toJSON();

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(REST_SERVICE_URI + "/exame/")
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void test2_listarExames() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get(REST_SERVICE_URI + "/exames/"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void test3_atualizarExame() throws Exception {

		exame = new ExameModel();
		exame.setNome("biopsia");
		exame.setTipoExame(TipoExame.IMAGEM);
		exame.setStatus(true);

		String json = exame.toJSON();

		this.mockMvc
				.perform(MockMvcRequestBuilders.put(REST_SERVICE_URI + "/exame/1")
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void test4_desativarExame() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete(REST_SERVICE_URI + "/exame/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}


}
