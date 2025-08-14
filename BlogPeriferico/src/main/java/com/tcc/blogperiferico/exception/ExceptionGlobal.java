package com.tcc.blogperiferico.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobal {

    @ExceptionHandler(SemResultadosException.class)
    public ResponseEntity<String> handler(SemResultadosException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(UsuarioDuplicadoException.class)
    public ResponseEntity<String> handler(UsuarioDuplicadoException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    @ExceptionHandler(DadoDuplicadoException.class)
    public ResponseEntity<String> handler (DadoDuplicadoException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handler(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Verifique o campo " + e.getMessage() + " e tente novamente.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handler (MethodArgumentNotValidException e){
        List<String> erros = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(erros);
    }

    // ✅ NOVO HANDLER PARA AUTENTICAÇÃO JWT
    @ExceptionHandler(AutenticacaoException.class)
    public ResponseEntity<String> handler(AutenticacaoException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno no servidor. Tente novamente mais tarde.");
    }
}
