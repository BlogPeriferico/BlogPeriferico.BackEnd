package com.tcc.blogperiferico.services;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {
	
	private static final int CODE_LENGTH = 6;
    private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(15);

    // Aqui um map simples para armazenar temporariamente código + timestamp
    private ConcurrentHashMap<String, ResetCodeData> resetCodes = new ConcurrentHashMap<>();

    public String generateResetCode(String email) {
        String codigo = generateCodigo();
        resetCodes.put(email, new ResetCodeData(codigo, System.currentTimeMillis()));
        return codigo;
    }

    public boolean validateResetCode(String email, String codigo) {
        ResetCodeData data = resetCodes.get(email);
        if (data == null) {
            return false; // código não gerado para esse email
        }
        // Verifica validade e código correto
        boolean valid = data.codigo.equals(codigo)
                && (System.currentTimeMillis() - data.timestamp) <= EXPIRATION_TIME;

        if (valid) {
            resetCodes.remove(email); // código usado, remove
        }
        return valid;
    }

    private String generateCodigo() {
        Random random = new Random();
        int number = random.nextInt((int) Math.pow(10, CODE_LENGTH));
        return String.format("%0" + CODE_LENGTH + "d", number);
    }

    private static class ResetCodeData {
        String codigo;
        long timestamp;

        ResetCodeData(String codigo, long timestamp) {
            this.codigo = codigo;
            this.timestamp = timestamp;
        }
    }

}
