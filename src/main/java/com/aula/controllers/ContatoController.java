package com.aula.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.entities.Contato;
import com.aula.repository.ContatoRepository;
import com.aula.services.ContatoService;
import com.aula.services.dto.ContatoDTO;

@RestController
@RequestMapping
public class ContatoController {
	@Autowired
	ContatoRepository repo;
	
	@Autowired
	ContatoService service;
	
	@GetMapping
	public String xpto() {
		return "index de contato";
	}
	
	@GetMapping("/contatos")
	public ResponseEntity<List<ContatoDTO>> getContatos() {
		List<ContatoDTO> contatos = service.consultarContatos();
		return ResponseEntity.status(HttpStatus.OK).body(contatos);
	}
	
	@GetMapping("/contatos/{idcontato}")
	public ResponseEntity<ContatoDTO> getContatosById(@PathVariable("idcontato") Long idContato) {
		ContatoDTO ct = service.consultarUmContato(idContato);
		return ResponseEntity.ok(ct);
	}
	
	@PostMapping("/contatos")
	public ResponseEntity<ContatoDTO> SaveContato(@RequestBody Contato contato) {
		ContatoDTO ct = service.salvar(contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@DeleteMapping("/contatos/{idcontato}")
	public ResponseEntity<Void> deleteContato(@PathVariable("idcontato") Long idContato) {
		service.excluirContato(idContato);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/contatos/{idcontato}")
	public ResponseEntity<ContatoDTO> alterarContato(@PathVariable("idcontato") Long idContato, @RequestBody Contato contato) {
		return ResponseEntity.ok(service.alterar(idContato, contato));
	}
}
