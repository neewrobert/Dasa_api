package com.dasa.exams.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ComponentScan("com.dasa.exams")
@EntityScan("com.dasa.exams.model")
@EnableJpaRepositories("com.dasa.exams.dao")
@TestPropertySource(locations="classpath:test.properties")
@SpringBootTest(classes = {LaboratorioRestController.class, ExameRestController.class, AssociacaoRestController.class })
public class ApplicationTests {
	
	@Test
	public void contextLoads() {
		
	}

}
	