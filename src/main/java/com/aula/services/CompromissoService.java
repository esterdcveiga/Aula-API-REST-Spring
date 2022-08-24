package com.aula.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.entities.Compromisso;
import com.aula.repository.CompromissoRepository;

@Service
public class CompromissoService {
	
	@Autowired
	CompromissoRepository repo;
	
	public Compromisso salvar(Compromisso compromisso) {
		return repo.save(compromisso);
	}
	
	public List<Compromisso> consultar(){
		return repo.findAll();
	}
	
	public List<Compromisso> consultarUmNome(String nome) {
		List<Compromisso> comps = repo.consultaPorNomeContato(nome);
		return comps;
	}

}
