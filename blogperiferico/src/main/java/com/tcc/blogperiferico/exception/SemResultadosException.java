package com.tcc.blogperiferico.exception;

public class SemResultadosException extends RuntimeException {
	private final String operacao;

    public SemResultadosException(){
        super("Sem resultados.");
        this.operacao = null;
    }

    public SemResultadosException(String operacao) {
        super(String.format("Entidade não encontrada para %s.", operacao));
        this.operacao = operacao;
    }

    public String getOperacao() {
        return operacao;
    }
}
