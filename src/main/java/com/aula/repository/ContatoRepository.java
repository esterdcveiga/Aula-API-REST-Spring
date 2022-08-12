package com.aula.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aula.entities.Contato;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, Long> {
	
}
