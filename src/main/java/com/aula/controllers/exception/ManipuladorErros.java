package com.aula.controllers.exception;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aula.services.exception.MinhaExcecao;
import com.aula.services.exception.ValidacaoException;

@ControllerAdvice
public class ManipuladorErros {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErroPadrao> entidadeNaoEncontrada(EntityNotFoundException e, HttpServletRequest req){
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("recurso não encontrado");
		erro.setMessage(e.getMessage());
		erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MinhaExcecao.class)
	public ResponseEntity<ErroPadrao> minhaException(MinhaExcecao e, HttpServletRequest req){
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		erro.setError("sem conteúdo");
		erro.setMessage(e.getMessage());
		erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErroPadrao> minhaExcecao(EmptyResultDataAccessException e, HttpServletRequest req){
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("recurso não existe");
		erro.setMessage(e.getMessage());
		erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<ErroPadrao> emailException(ValidacaoException e, HttpServletRequest req){
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Esse dado é inválido");
		erro.setMessage(e.getMessage());
		erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadrao> argException(MethodArgumentNotValidException e, HttpServletRequest req){
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Esse dado é inválido");
		erro.setMessage(e.getLocalizedMessage());
		erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	

}
