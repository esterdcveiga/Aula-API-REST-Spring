package com.aula.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.aula.entities.Contato;
import com.aula.repository.ContatoRepository;
import com.aula.services.ContatoService;
import com.aula.services.dto.ContatoDTO;

@ExtendWith(SpringExtension.class)
public class ContatoServiceTest {

	private Long idExistente;
	private Long idInexistente;
	//private String emailValido;
	//private String emailInvalido;

	Contato contatoValido;
	Contato contatoInvalido;

	@BeforeEach
	void setup() {
		idExistente = 1l;
		idInexistente = 1000l;

		contatoValido = new Contato("maria", "maria@mail", "(84)91111222");
		contatoInvalido = new Contato("paula", "mariamail", "(84)93311222");
		

		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idInexistente);
		
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idInexistente);
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(contatoValido));
		
		Mockito.when(repository.save(contatoValido)).thenReturn(contatoValido);
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(contatoInvalido);

	}

	@InjectMocks
	ContatoService service;

	@Mock
	ContatoRepository repository;

	@Test
	public void retornaNadaAoExcluirComIdExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirContato(idExistente);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(idExistente);

	}

	@Test
	public void retornaNadaAoExcluirComIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirContato(idInexistente);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(idInexistente);
	}

	@Test
	public void testConsultarUmContatoHappyPath() {
		ContatoDTO ct = service.consultarUmContato(idExistente);
		Assertions.assertNotNull(ct);
		Mockito.verify(repository).findById(idExistente);
	}

	@Test
	public void testConsultarUmContatoBadPath() {

		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarUmContato(idInexistente);
		});
		Mockito.verify(repository, Mockito.times(1)).findById(idInexistente);
	}
	
	@Test
	public void retornaContatoDTOQuandoSalvarConSucesso() {
		ContatoDTO ct = service.salvar(contatoValido);
		Assertions.assertNotNull(ct);
		Mockito.verify(repository).save(contatoValido);
	}
	
	@Test
	public void testSalvarContatoBadPath() {
		
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			service.salvar(contatoInvalido);
		});
		Mockito.verify(repository, Mockito.times(1)).save(contatoInvalido);
	}
}
