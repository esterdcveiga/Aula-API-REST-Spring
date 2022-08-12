package com.aula.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ContatoController {
	
	@GetMapping
	public String xpto() {
		return "index ce contato";
	}
	
	@GetMapping("/contatos")
	public String getContatos() {
		return "oiiie";
	}
	
	@GetMapping("/contatos/{idcontato}")
	public String getContatosById(@PathVariable("idcontato") int idContato) {
		return "oiiie contato " + idContato;
	}
	
	@PostMapping("/contatos")
	public String SaveContato(@RequestBody String contato) {
		return contato;
	}
}
