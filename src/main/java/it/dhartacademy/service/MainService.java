package it.dhartacademy.service;

import it.dhartacademy.model.ContactForm;
import it.dhartacademy.model.CourseShowcase;
import it.dhartacademy.model.Review;
import it.dhartacademy.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MainService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.address.customer-service}")
    private String customerServiceAddress;

    @Value("${url.cloudinary.base}")
    private String cloudinaryBaseUrl;

    @Value("${cloudinary.transformation.common}")
    private String cloudinaryTransfCommon;

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

    public List<Review> getAllReviews() {
        log.info("getAllReviews | Start");
        List<Review> reviews = reviewRepository.findAll();
        log.info("Review's number: {}", reviews.size());
        log.info("getAllReviews | End");
        return reviews;
    }


    public CourseShowcase getShowcaseBreakdance() {
        CourseShowcase showcase = new CourseShowcase();
        showcase.setName("breakdance");
        showcase.setImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1601812943/dhart-academy/one-hand-babyfreeze_vfkuh5.jpg");
        showcase.setMotivatorText("Esprimiti, partecipa ad eventi, battles ed entra a far parte della nostra famiglia!");
        log.info("BreakdanceShowcase object: {}", showcase);
        return showcase;
    }

    public CourseShowcase getShowcaseDancehall() {
        CourseShowcase showcase = new CourseShowcase();
        showcase.setName("dancehall");
        showcase.setImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1601812549/dhart-academy/dancehall-girls_mxovvy.jpg");
        log.info("DancehallShowcase object: {}", showcase);
        return showcase;
    }
}
