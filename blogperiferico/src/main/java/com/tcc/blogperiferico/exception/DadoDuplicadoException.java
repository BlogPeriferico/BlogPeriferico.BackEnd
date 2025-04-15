package com.tcc.blogperiferico.exception;

public class DadoDuplicadoException extends RuntimeException {
	private final String dado;

    public DadoDuplicadoException() {
        super("Dado(s) já existente(s).");
        this.dado = null;
    }

    public DadoDuplicadoException (String dado) {
        super(String.format("%s já existente.",  dado));
        this.dado = dado;
    }

    public String getDado() {
        return dado;
    }
}
