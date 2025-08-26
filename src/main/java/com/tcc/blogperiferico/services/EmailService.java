package com.tcc.blogperiferico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCodigoRedefinicao(String para, String codigo) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(para);
            helper.setFrom("no-reply@blogperiferico.com");
            helper.setSubject("Blog Periférico - Redefinição de Senha");

            String htmlContent = String.format("""
                <html>
                <body style="font-family: Arial, sans-serif; background-color: #fff8f0; padding: 20px; color: #333;">
                    <div style="max-width: 600px; margin: auto; background: white; border-radius: 10px; overflow: hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
                        <div style="background: linear-gradient(90deg, #FF6A00, #FFAE74); padding: 20px; text-align: center; color: white;">
                            <h1 style="margin: 0;">Blog Periférico</h1>
                        </div>
                        <div style="padding: 30px;">
                            <h2 style="color: #FF6A00;">Redefinição de Senha</h2>
                            <p>Olá,</p>
                            <p>Recebemos uma solicitação para redefinir a senha da sua conta no <strong>Blog Periférico</strong>.</p>
                            <p>Use o código abaixo para continuar o processo de redefinição:</p>
                            <div style="text-align: center; margin: 20px 0;">
                                <span style="font-size: 24px; font-weight: bold; background: #fff3e6; padding: 10px 20px; border: 2px dashed #FF6A00; border-radius: 8px; display: inline-block; color: #FF6A00;">
                                    %s
                                </span>
                            </div>
                            <p><em>⚠ Este código expira em 15 minutos.</em></p>
                            <p>Se você não solicitou essa alteração, por favor ignore este e-mail.</p>
                            <br>
                            <p>Atenciosamente,</p>
                            <p><strong>Equipe Blog Periférico</strong></p>
                        </div>
                        <div style="background: #fff3e6; padding: 15px; text-align: center; font-size: 12px; color: #777;">
                            © 2025 Blog Periférico. Todos os direitos reservados.
                        </div>
                    </div>
                </body>
                </html>
                """, codigo);

            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage(), e);
        }
    }
}