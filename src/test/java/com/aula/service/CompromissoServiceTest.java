package com.aula.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aula.entities.Compromisso;
import com.aula.entities.Contato;
import com.aula.repository.CompromissoRepository;
import com.aula.services.CompromissoService;

@ExtendWith(SpringExtension.class)
public class CompromissoServiceTest {
	
	Compromisso compromissoValido;
	Compromisso compromissoInvalido;
	List<Compromisso> compromissos;
	String nomeValido;
	String nomeInvalido;
	
	Contato contato;
	//Date data;
	
	@InjectMocks
	CompromissoService service;

	@Mock
	CompromissoRepository repo;
	
	@BeforeEach
	public void setUp() {
		contato = new Contato("maria", "maria@mail", "(84)91111222");
		
		compromissoValido = new Compromisso("Natal, RN", null, contato);
		compromissoInvalido = new Compromisso(null, null, contato);
		
		nomeValido = "Patricia";
		nomeInvalido = "Carina";
		
		compromissos = new ArrayList<>();
		
		Mockito.when(repo.save(compromissoValido)).thenReturn(compromissoValido);
		Mockito.when(repo.save(compromissoInvalido)).thenReturn(null);
		
		Mockito.when(repo.findAll()).thenReturn(compromissos);
		
		Mockito.when(repo.consultaPorNomeContato(nomeValido)).thenReturn(compromissos);
		Mockito.when(repo.consultaPorNomeContato(nomeInvalido)).thenReturn(null);
	}
	
	//@Test
	public void testSalvarCompromissoComSuccesso() {
		Compromisso cp = service.salvar(compromissoValido);
		Assertions.assertNotNull(cp);
		Mockito.verify(repo).save(compromissoValido);
	}
	
	//@Test
	public void testSalvarCompromissoSemSucesso() {
		Compromisso cp = service.salvar(compromissoInvalido);
		Assertions.assertNull(cp);
		Mockito.verify(repo).save(compromissoInvalido);
	}
	
	@Test
	public void testConsultarTodosComSucesso() {
		compromissos = service.consultar();
		Assertions.assertNotNull(compromissos);
		Mockito.verify(repo).findAll();
	}
	
//	@Test
	public void testConsultarPorNomeComSucesso() {
		compromissos = service.consultarUmNome(nomeValido);
		Assertions.assertNotNull(compromissos);
		Mockito.verify(repo).consultaPorNomeContato(nomeValido);
	}
	
//	@Test
	public void testConsultarPorNomeSemSucesso() {
		compromissos = service.consultarUmNome(nomeInvalido);
		Assertions.assertNull(compromissos);
		Mockito.verify(repo).consultaPorNomeContato(nomeInvalido);
	}
	
}
