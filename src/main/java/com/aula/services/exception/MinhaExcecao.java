package com.aula.services.exception;

public class MinhaExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public MinhaExcecao(String mensagem) {
		super(mensagem);
	}
}
