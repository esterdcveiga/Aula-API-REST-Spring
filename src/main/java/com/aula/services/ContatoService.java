package com.aula.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.entities.Contato;
import com.aula.repository.ContatoRepository;
import com.aula.services.dto.ContatoDTO;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository repo;
	
	public ContatoDTO salvar(Contato contato) {
		Contato ct = repo.save(contato);
		ContatoDTO ctDto = new ContatoDTO(ct);
		return ctDto;
	}
	
	public List<ContatoDTO> consultarContatos(){
		List<Contato> contatos = repo.findAll();
		List<ContatoDTO> contatosDTO = new ArrayList<>();
		for (Contato ct: contatos) {
			ContatoDTO ctDTO = new ContatoDTO(ct);
			contatosDTO.add(ctDTO);
		}
		return contatosDTO;
	}
	
	public ContatoDTO consultarUmContato(Long idContato) {
		Optional<Contato> opContato = repo.findById(idContato);
		Contato contato = opContato.orElseThrow(( )-> new EntityNotFoundException("Contato não encontardo"));
		ContatoDTO ctDTO = new ContatoDTO(contato);
		return ctDTO;
	}
	
	public void excluirContato (Long idContato){
		repo.deleteById(idContato);
	}
	
	public ContatoDTO alterar(Long idContato, Contato contato) {
		Contato ct = repo.findById(idContato).get();
		ct.setEmail(contato.getEmail());
		ct.setNome(contato.getNome());
		ct.setFone(contato.getFone());
		ContatoDTO ctDto = new ContatoDTO(repo.save(ct));
		return ctDto;
	}
	
	public List<ContatoDTO> consultaPorEmail(String email){
		return ContatoDTO.converteParaDTO(repo.findByEmail(email));
	}

}
