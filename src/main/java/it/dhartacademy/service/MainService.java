package it.dhartacademy.service;

import it.dhartacademy.model.ContactForm;
import it.dhartacademy.model.CoursePackage;
import it.dhartacademy.model.CourseModel;
import it.dhartacademy.model.Review;
import it.dhartacademy.repository.CourseTeacherRepository;
import it.dhartacademy.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Slf4j
@Service
public class MainService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CourseTeacherRepository courseTeacherRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.address.customer-service}")
    private String customerServiceAddress;

    //@Value("${url.cloudinary.base}") couldn't use static initializer or regular initializer with @Value
    private static String cloudinaryBaseUrl = "https://res.cloudinary.com/facca/image/upload/";

    //@Value("${cloudinary.transformation.common}") couldn't use static initializer or regular initializer with @Value
    private static String cloudinaryTransfCommon = "f_auto,q_auto:best,";

    private static List<CoursePackage> packages;

     static {
        String urlPrefix = cloudinaryBaseUrl + cloudinaryTransfCommon;
        CoursePackage bronzo = new CoursePackage("bronzo", 40, "1", "1", "1", urlPrefix + "/v1601812953/dhart-academy/BRONZE_zpoea3.png");
        CoursePackage argento = new CoursePackage("argento", 55, "1", "2", "2", urlPrefix + "/v1601812716/dhart-academy/SILVER_azup20.png");
        CoursePackage oro = new CoursePackage("oro", 75, "2", "3", "3", urlPrefix + "/v1601812950/dhart-academy/GOLD_dkyrnh.png");
        CoursePackage platino = new CoursePackage("platino", 105, "4", "4", "3", urlPrefix + "/v1601812721/dhart-academy/PLATINUM_nnncvo.png");
        CoursePackage diamante = new CoursePackage("diamante", 125, "5", "5", "3", urlPrefix + "/v1601812949/dhart-academy/DIAMOND_fqmt7d.png");
        CoursePackage special = new CoursePackage("special", 150, "illimitati", "", "3", urlPrefix + "/v1601812727/dhart-academy/SPECIAL_gthzhg.png");
        packages = Arrays.asList(bronzo, argento, oro, platino, diamante, special);
    }


    public List<Review> getAllReviews() {
        log.info("getAllReviews | Start");
        List<Review> reviews = reviewRepository.findAll();
        log.info("Review's number: {}", reviews.size());
        log.info("getAllReviews | End");
        return reviews;
    }


    public CourseModel getBreakdanceModel() {
        CourseModel model =  CourseModel.builder()
            .name("breakdance")
            .showcaseVideoURL("https://www.youtube.com/embed/tMciF6z4XVw")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "w_auto/v1601812943/dhart-academy/one-hand-babyfreeze_vfkuh5.webp")
            .showcaseMotivatorText("Esprimiti, partecipa ad eventi, battles ed entra a far parte della nostra famiglia!")
            .teachers(courseTeacherRepository.findByCoursesContaining("breakdance"))
            .build();
        log.info("BreakdanceModel: {}", model);
        return model;
    }

    public CourseModel getDancehallModel() {
        CourseModel model = CourseModel.builder()
            .name("dancehall")
            .showcaseVideoURL("https://www.youtube.com/embed/GkNF9iCi8NE")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "w_auto/v1601812549/dhart-academy/dancehall-girls_mxovvy.webp")
            .teachers(courseTeacherRepository.findByCoursesContaining("dancehall"))
            .build();
        log.info("DancehallModel : {}", model);
        return model;
    }

    public CourseModel getDjModel() {
        CourseModel model = CourseModel.builder()
            .name("dj")
            .showcaseVideoURL("https://www.youtube.com/embed/fE2kLZLh6MU")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "w_auto/v1601812610/dhart-academy/dj-dhalsim_bxicp9.webp")
            .teachers(courseTeacherRepository.findByCoursesContaining("dj"))
            .build();
        log.info("DjModel : {}", model);
        return model;
    }

    public CourseModel getFloorworkModel() {
        CourseModel model = CourseModel.builder()
            .name("floorwork")
            .showcaseVideoURL("https://www.youtube.com/embed/_vgBiQ208Xg")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "w_auto/v1602283185/dhart-academy/floorwork-show_pdzycu.webp")
            .teachers(courseTeacherRepository.findByCoursesContaining("floorwork"))
            .build();
        log.info("FloorworkModel : {}", model);
        return model;
    }

    public CourseModel getGraffitiModel() {
        CourseModel model = CourseModel.builder()
            .name("graffiti")
            .showcaseVideoURL("https://www.youtube.com/embed/HxQC3sbYHwM")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "w_auto/v1602285111/dhart-academy/graffiti2_uxkhzz.webp")
            .teachers(courseTeacherRepository.findByCoursesContaining("graffiti"))
            .build();
        log.info("GraffitiModel : {}", model);
        return model;
    }

    public CourseModel getHipHopModel() {
        CourseModel model = CourseModel.builder()
            .name("hip hop")
            .showcaseVideoURL("https://www.youtube.com/embed/zw_bg4jcY-I")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "w_auto/v1601812759/dhart-academy/house_rp9lv4.webp")
            .teachers(courseTeacherRepository.findByCoursesContaining("hip hop"))
            .build();
        log.info("HipHopModel : {}", model);
        return model;
    }

    public CourseModel getHouseModel() {
        CourseModel model = CourseModel.builder()
            .name("house")
            .showcaseVideoURL("https://www.youtube.com/embed/0l3sIYnRHPs")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "TODO")
            .teachers(courseTeacherRepository.findByCoursesContaining("house"))
            .build();
        log.info("HouseModel : {}", model);
        return model;
    }

    public CourseModel getVogueModel() {
         log.info("getVogueModel");
        CourseModel model = CourseModel.builder()
            .name("Vogue")
            .showcaseVideoURL("https://www.youtube.com/embed/mlXKQcFDFcM")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "w_auto/v1601812709/dhart-academy/vouge4_sf8wyn.webp")
            .teachers(courseTeacherRepository.findByCoursesContaining("vogue"))
            .build();
        log.info("VogueModel : {}", model);
        return model;
    }

    public List<CoursePackage> getPackages() {
        log.info("Packages: {}", packages);
        return packages;
    }

    public void sendNotification(final ContactForm contactForm) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(customerServiceAddress);
        email.setReplyTo(contactForm.getEmail());
        email.setSubject("Richiesta informazioni | dhartacademy.it");
        email.setText(contactForm.getMessage());
        log.debug("Email {}", email);

        try{
            /*Thread thread = new Thread(() -> javaMailSender.send(email));
            thread.start();*/
            javaMailSender.send(email);
            log.info("Messaggio inviato con successo");
        } catch (Exception ex){
            log.error("Errore! {}", ex.getMessage());
            throw ex;
        }
    }

    public List<CourseModel.CourseTeacher.Award> getHomeAwards() {
        log.info("getHomeAwards | Inizio");
        List<CourseModel.CourseTeacher.Award> awards = null;

        Optional<CourseModel.CourseTeacher> optionalAwards = courseTeacherRepository.findByNameAndSurname("Alessio", "Venditti");
        if (optionalAwards.isPresent() && optionalAwards.get().getAwards() != null) {
            awards = optionalAwards.get().getAwards();
            log.info("Awards: {}", awards);
        }
        log.info("getHomeAwards | Fine");
        return awards;
    }
}
