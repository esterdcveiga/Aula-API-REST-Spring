package com.aula.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.entities.Compromisso;
import com.aula.repository.CompromissoRepository;
import com.aula.services.CompromissoService;

@RestController
@RequestMapping("/")
public class CompromissoController {
	@Autowired
	CompromissoService service;
	
	@GetMapping("/compromissos")
	public ResponseEntity<List<Compromisso>> consultaCompromissos() {
		return ResponseEntity.ok(service.consultar());
	}
	
	@GetMapping("/compromissos/contato/{nome}")
	public ResponseEntity<List<Compromisso>> consultaCompromissosNomeContato(@PathVariable("nome") String nome){
		return ResponseEntity.ok(service.consultarUmNome(nome));
	}
	
	@PostMapping("/compromissos")
	public ResponseEntity<Compromisso> salvarCompromisso(@RequestBody Compromisso compromisso){
		return ResponseEntity.ok(service.salvar(compromisso));
	}
}
