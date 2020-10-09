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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    public CourseModel getDancehallModel() {
        CourseModel model = CourseModel.builder()
            .name("dancehall")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1601812549/dhart-academy/dancehall-girls_mxovvy.jpg")
            .teachers(Arrays.asList(new CourseModel.CourseTeacher(
                    "Linda",
                    "Roscini",
                    "Linda",
                    "Inizia a studiare danza nel 2013 all'età di 15 anni a Terni.\n" +
                            "Studia Hip Hop, House, Dancehall, Modern, Contemporary, Waacking e in ultimo il Voguing, al quale si appassiona e decide di specializzarsi.\n" +
                            "Segue corsi a Roma con Annalisa Marcelli e Giorgia Ianniccheri, entrando a far parte della Kiki House of Munera.\n" +
                            "In seguito ne esce e attualmente rappresenta la scena italiana facendo parte della Kiki House of Louboutin, una Kiki parigina.\n" +
                            "Fa workshop studiando con Veronika Ninja, Danielle Polanco, Elena Ninja, Kiddy Smile, Rim Yamamoto, Keiona Mitchell.\n" +
                            "Ad Agosto 2019 insieme ad altri ragazzi della scena, organizza \"The Filthiest kiki ball\" la prima Ball in Umbria.",
                    "7",
                    "v1602192184/dhart-academy/dhalsim-volto_qqagj4.png",
                    "https://www.facebook.com/DhartAcademy",
                    "https://www.instagram.com/dhartacademy",
                    "https://www.youtube.com/DhartAcademy",
                    "https://twitter.com/dhartacademy")))
            .build();
        log.info("DancehallModel : {}", model);
        return model;
    }

    public CourseModel getDjModel() {
        CourseModel model = CourseModel.builder()
            .name("dj")
            .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1601812610/dhart-academy/dj-dhalsim_bxicp9.jpg")
            .build();
        log.info("DjModel : {}", model);
        return model;
    }

    public CourseModel getFloorworkModel() {
        CourseModel model = CourseModel.builder()
                .name("floorwork")
                .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1602283185/dhart-academy/floorwork-show_pdzycu.jpg")
                .build();
        log.info("DjModel : {}", model);
        return model;
    }

    public CourseModel getGraffitiModel() {
        CourseModel model = CourseModel.builder()
                .name("graffiti")
                .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1602285111/dhart-academy/graffiti2_uxkhzz.jpg")
                .teachers(Arrays.asList(new CourseModel.CourseTeacher(
                        "Emanuele",
                        "Longetti",
                        "Rem",
                        "Fin da piccolo appassionato ad ogni forma d'arte e alla musica, \n" +
                                "si è avvicinato al mondo dei graffiti nel 2011, affascinato dalla complessità e unicità di quelle che osservava sparse per la sua città.\n" +
                                "Esperto di calligraffiti, una forma d'arte che combina calligrafia, tipografia e graffiti in un unico elemento, ha conosciuto la cultura hip hop nel 2013 grazie ad un viaggio a new York, scoprendo la breakdance e la vera storia dei graffiti. Da allora ha iniziato a prendere lezioni di hip hop, breakdance, popping e locking.",
                        "9",
                        "v1602192184/dhart-academy/dhalsim-volto_qqagj4.png",
                        "https://www.facebook.com/DhartAcademy",
                        "https://www.instagram.com/dhartacademy",
                        "https://www.youtube.com/DhartAcademy",
                        "https://twitter.com/dhartacademy")))
                .build();
        log.info("DjModel : {}", model);
        return model;
    }

    public CourseModel getHipHopModel() {
        CourseModel model = CourseModel.builder()
                .name("hip hop")
                .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1601812759/dhart-academy/house_rp9lv4.jpg")
                .teachers(Arrays.asList(new CourseModel.CourseTeacher(
                        "Davide",
                        "Del Gaia",
                        "Collo",
                        "TODO",
                        "20",
                        "v1602192184/dhart-academy/dhalsim-volto_qqagj4.png",
                        "https://www.facebook.com/DhartAcademy",
                        "https://www.instagram.com/dhartacademy",
                        "https://www.youtube.com/DhartAcademy",
                        "https://twitter.com/dhartacademy")))
                .build();
        log.info("DjModel : {}", model);
        return model;
    }

    public CourseModel getHouseModel() {
        CourseModel model = CourseModel.builder()
                .name("house")
                .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "TODO")
                .teachers(Arrays.asList(new CourseModel.CourseTeacher(
                        "Linda",
                        "Roscini",
                        "Linda",
                        "Inizia a studiare danza nel 2013 all'età di 15 anni a Terni.\n" +
                                "Studia Hip Hop, House, Dancehall, Modern, Contemporary, Waacking e in ultimo il Voguing, al quale si appassiona e decide di specializzarsi.\n" +
                                "Segue corsi a Roma con Annalisa Marcelli e Giorgia Ianniccheri, entrando a far parte della Kiki House of Munera.\n" +
                                "In seguito ne esce e attualmente rappresenta la scena italiana facendo parte della Kiki House of Louboutin, una Kiki parigina.\n" +
                                "Fa workshop studiando con Veronika Ninja, Danielle Polanco, Elena Ninja, Kiddy Smile, Rim Yamamoto, Keiona Mitchell.\n" +
                                "Ad Agosto 2019 insieme ad altri ragazzi della scena, organizza \"The Filthiest kiki ball\" la prima Ball in Umbria.",
                        "7",
                        "v1602192184/dhart-academy/dhalsim-volto_qqagj4.png",
                        "https://www.facebook.com/DhartAcademy",
                        "https://www.instagram.com/dhartacademy",
                        "https://www.youtube.com/DhartAcademy",
                        "https://twitter.com/dhartacademy")))
                .build();
        log.info("DjModel : {}", model);
        return model;
    }

    public CourseModel getVougeModel() {
        CourseModel model = CourseModel.builder()
                .name("vouge")
                .showcaseImageURL(cloudinaryBaseUrl + cloudinaryTransfCommon + "/v1601812709/dhart-academy/vouge4_sf8wyn.jpg")
                .teachers(Arrays.asList(new CourseModel.CourseTeacher(
                    "Roberta",
                    "Carli",
                    "Robija",
                    "laureata alla triennale di scienze motorie UniPg e insegnante di Hip Hop e Dancehall in Umbria dal 2013. Venendo da backgroud sportivo vario inizia a studiare danza nel 2010. Partendo dal Breaking, approfondisce poi tutti gli stili Urban, in particolare Hip Hop e House Dance, studiando con insegnanti nazionali e internazionali. Si innamora della Dancehall nel 2013 e da quel momento ci si dedica completamente.",
                    "18",
                    "v1602192184/dhart-academy/dhalsim-volto_qqagj4.png",
                    "https://www.facebook.com/DhartAcademy",
                    "https://www.instagram.com/dhartacademy",
                    "https://www.youtube.com/DhartAcademy",
                    "https://twitter.com/dhartacademy")))
                .build();
        log.info("DjModel : {}", model);
        return model;
    }

    public List<CoursePackage> getPackages() {
        log.info("Packages: {}", packages);
        return packages;
    }


}
