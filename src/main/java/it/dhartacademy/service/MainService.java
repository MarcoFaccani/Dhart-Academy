package it.dhartacademy.service;

import it.dhartacademy.model.ContactForm;
import it.dhartacademy.model.CoursePackage;
import it.dhartacademy.model.CourseModel;
import it.dhartacademy.model.Review;
import it.dhartacademy.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    //@Value("${url.cloudinary.base}") couldn't use static initializer or regular initializer with @Value
    private static String cloudinaryBaseUrl = "https://res.cloudinary.com/facca/image/upload/";

    //@Value("${cloudinary.transformation.common}") couldn't use static initializer or regular initializer with @Value
    private static String cloudinaryTransfCommon = "f_auto,q_auto:best,e_sharpen,";

    private static List<CoursePackage> packages;

     static {
        String urlPrefix = cloudinaryBaseUrl + cloudinaryTransfCommon;
        CoursePackage bronzo = new CoursePackage("bronzo", 35, "1", 1, 1, urlPrefix + "/v1601812953/dhart-academy/BRONZE_zpoea3.png");
        CoursePackage argento = new CoursePackage("argento", 55, "1", 2, 1, urlPrefix + "/v1601812716/dhart-academy/SILVER_azup20.png");
        CoursePackage oro = new CoursePackage("oro", 65, "2", 2, 1, urlPrefix + "/v1601812950/dhart-academy/GOLD_dkyrnh.png");
        CoursePackage platino = new CoursePackage("platino", 90, "4", 4, 3, urlPrefix + "/v1601812721/dhart-academy/PLATINUM_nnncvo.png");
        CoursePackage diamante = new CoursePackage("diamante", 110, "5", 5, 3, urlPrefix + "/v1601812949/dhart-academy/DIAMOND_fqmt7d.png");
        CoursePackage special = new CoursePackage("special", 135, "illimitati", 5, 3, urlPrefix + "/v1601812727/dhart-academy/SPECIAL_gthzhg.png");
        packages = Arrays.asList(bronzo, argento, oro, platino, diamante, special);
    }

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


    public CourseModel getBreakdanceModel() {
        CourseModel model = new CourseModel();
        model.setName("breakdance");
        model.setShowcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1601812943/dhart-academy/one-hand-babyfreeze_vfkuh5.jpg");
        model.setShowcaseMotivatorText("Esprimiti, partecipa ad eventi, battles ed entra a far parte della nostra famiglia!");
        model.setTeachers(Arrays.asList(new CourseModel.CourseTeacher(
                "Alessio",
                "Venditti",
                " bboy Dhalsim",
                "Classe '89 nato e cresciuto ad Ostia, ora vive a Perugia. Da 18 anni attivo nella scena della Breakdance nazionale ed internazionale, fondatore e membro della crew lidense Ostia Street Rockerz e della Flexible Flav crew californiana",
                "18",
                "v1602192184/dhart-academy/dhalsim-volto_qqagj4.png",
                "https://www.facebook.com/DhartAcademy",
                "https://www.instagram.com/dhartacademy",
                "https://www.youtube.com/DhartAcademy",
                "https://twitter.com/dhartacademy"
                )));
        log.info("BreakdanceModel: {}", model);
        return model;
    }

    public CourseModel getShowcaseDancehall() {
        CourseModel model = new CourseModel();
        model.setName("dancehall");
        model.setShowcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1601812549/dhart-academy/dancehall-girls_mxovvy.jpg");
        log.info("DancehallModel : {}", model);
        return model;
    }

    public List<CoursePackage> getPackages() {
        log.info("Packages: {}", packages);
        return packages;
    }
}
