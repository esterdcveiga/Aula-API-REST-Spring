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

@RestController
@RequestMapping
public class ContatoController {
	@Autowired
	ContatoRepository repo;
	
	@GetMapping
	public String xpto() {
		return "index de contato";
	}
	
	@GetMapping("/contatos")
	public ResponseEntity<List<Contato>> getContatos() {
		List<Contato> contatos = (List) repo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(contatos);
	}
	
	@GetMapping("/contatos/{idcontato}")
	public ResponseEntity<Contato> getContatosById(@PathVariable("idcontato") Long idContato) {
		Contato ct = repo.findById(idContato).get();
		return ResponseEntity.status(HttpStatus.OK).body(ct);
	}
	
	@PostMapping("/contatos")
	public ResponseEntity<Contato> SaveContato(@RequestBody Contato contato) {
		Contato ct = repo.save(contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@DeleteMapping("/contatos/{idcontato}")
	public ResponseEntity<Void> deleteContato(@PathVariable("idcontato") Long idContato) {
		repo.deleteById(idContato);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/contatos/{idcontato}")
	public ResponseEntity<Contato> alterarContato(@PathVariable("idcontato") int idContato, @RequestBody Contato contato) {
		return ResponseEntity.ok(repo.save(contato));
	}
}
