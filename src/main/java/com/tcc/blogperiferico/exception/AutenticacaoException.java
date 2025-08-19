package com.tcc.blogperiferico.exception;

public class AutenticacaoException extends RuntimeException {
    public AutenticacaoException() {
        super("Falha na autenticação. Usuário ou senha inválidos.");
    }

    public AutenticacaoException(String mensagem) {
        super(mensagem);
    }
}
