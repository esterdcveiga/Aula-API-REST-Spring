package com.aula.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.entities.Contato;
import com.aula.repository.ContatoRepository;
import com.aula.services.exception.ValidacaoException;
import com.aula.services.dto.ContatoDTO;
import com.aula.services.exception.MinhaExcecao;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository repo;
	
	public ContatoDTO salvar(Contato contato) {
//		if(contato.getFone().length() != 14) {
//			throw new ValidacaoException("telefone inválido");
//		}
//		String email = contato.getEmail();
//		if(!email.contains("@")) {
//			throw new ValidacaoException("email inválido");
//		}
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
		//Contato contato = consultarUmContato(idContato);
		repo.deleteById(idContato);
		//repo.delete(contato);
//		Optional<Contato> opContato = repo.findById(idContato);
//		Contato contato = opContato.orElseThrow(( )-> new MinhaExcecao("Contato não existe não"));
//		return contato;
	}
	
	public ContatoDTO alterar(Long idContato, Contato contato) {
		//Contato ct = consultarUmContato(idContato);
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
