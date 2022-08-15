package com.aula.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.entities.Contato;
import com.aula.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository repo;
	
	public Contato salvar(Contato contato) {
		return repo.save(contato);
	}
	
	public List<Contato> consultarContatos(){
		List<Contato> contatos = repo.findAll();
		return contatos;
	}
	
	public Contato consultarUmContato(Long idContato) {
		Optional<Contato> opContato = repo.findById(idContato);
		Contato contato = opContato.orElseThrow(( )-> new EntityNotFoundException("Contato não encontardo"));
		return contato;
	}
	
	public void excluirContato (Long idContato) {
		Contato contato = consultarUmContato(idContato);
		repo.delete(contato);
	}
	
	public Contato alterar(Long idContato, Contato contato) {
		Contato ct = consultarUmContato(idContato);
		ct.setEmail(contato.getEmail());
		ct.setNome(contato.getNome());
		ct.setFone(contato.getFone());
		return repo.save(ct);
	}

}
