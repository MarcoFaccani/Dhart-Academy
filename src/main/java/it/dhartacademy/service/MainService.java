package it.dhartacademy.service;

import it.dhartacademy.model.ContactForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MainService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.address.customer-service}")
    private String customerServiceAddress;

    public void sendNotification(final ContactForm contactForm) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(customerServiceAddress);
        email.setReplyTo(contactForm.getEmail());
        email.setSubject(contactForm.getSubject());
        email.setText(contactForm.getMessage());
        log.debug("Email {}", email);

        try{
            Thread thread = new Thread(() -> javaMailSender.send(email));
            thread.start();
            log.info("Messaggio inviato con successo");
        } catch (Exception ex){
            log.error("Errore! {}", ex.getMessage());
            throw ex;
        }
    }

}
