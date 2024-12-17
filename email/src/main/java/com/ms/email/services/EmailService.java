package com.ms.email.services;

import com.jmtsu.ms.core.enums.StatusEmail;
import com.jmtsu.ms.core.model.EmailModel;
import com.jmtsu.ms.core.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDataTimeEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            // O texto agora é HTML, já montado no producer
            String htmlContent = emailModel.getText();

            // Cria e configura o MimeMessage
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true para permitir conteúdo HTML

            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(htmlContent, true); // Define como HTML

            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException | MessagingException e) {
            emailModel.setStatusEmail(StatusEmail.ERRO);
        } finally {
            return emailRepository.save(emailModel);
        }
    }
}