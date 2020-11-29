package it.dhartacademy.controller;

import it.dhartacademy.model.ContactForm;
import it.dhartacademy.model.CoursePackage;
import it.dhartacademy.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class AppController {

    @Autowired
    private MainService mainService;

    @Value("${url.cloudinary.base}")
    private String cloudinaryBaseUrl;

    @Value("${cloudinary.transformation.common}")
    private String cloudinaryTransfCommon;

//____________________________________________PAGE GETTERS________________________________________________________________________

    @GetMapping("/")
    public String getHomePage(Model model) {
        log.info("Recupero la home-page");
        model.addAttribute("reviews", mainService.getAllReviews());
        model.addAttribute("awards", mainService.getHomeAwards());
        model.addAttribute("modalTitle", "Tournée \"50 Al Centro\"");
        model.addAttribute("modalYoutubeURL", "https://www.youtube.com/embed/tqDG6lqpEWU");
        return "home";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model, ContactForm contactForm) {
        log.info("Recupero la pagina contatti");
        return "contact";
    }

    @GetMapping("/courses/breakdance")
    public String getCourseBreakdance(Model model) {
        log.info("Recupero corsi/breakdance");
        model.addAttribute("courseModel", mainService.getBreakdanceModel());
        return "courses/breakdance";
    }

    @GetMapping("/courses/dancehall")
    public String getCourseDancehall(Model model) {
        log.info("Recupero corsi/dancehall");
        model.addAttribute("courseModel", mainService.getDancehallModel());
        return "courses/dancehall";
    }

    @GetMapping("/courses/dj")
    public String getCourseDj(Model model) {
        log.info("Recupero corsi/dj");
        model.addAttribute("courseModel", mainService.getDjModel());
        return "courses/dj";
    }

    @GetMapping("/courses/floorwork")
    public String getCourseFloorwork(Model model) {
        log.info("Recupero corsi/floorwork");
        model.addAttribute("courseModel", mainService.getFloorworkModel());
        return "courses/floorwork";
    }

    @GetMapping("/courses/graffiti")
    public String getCourseGraffiti(Model model) {
        log.info("Recupero corsi/graffiti");
        model.addAttribute("courseModel", mainService.getGraffitiModel());
        return "courses/graffiti";
    }

    @GetMapping("/courses/hiphop")
    public String getCourseHipHop(Model model) {
        log.info("Recupero corsi/hiphop");
        model.addAttribute("courseModel", mainService.getHipHopModel());
        return "courses/hiphop";
    }

    @GetMapping("/courses/house")
    public String getCourseHouse(Model model) {
        log.info("Recupero corsi/house");
        model.addAttribute("courseModel", mainService.getHouseModel());
        return "courses/house";
    }

    @GetMapping("/courses/vogue")
    public String getCourseVogue(Model model) {
        log.info("Recupero corsi/vogue");
        model.addAttribute("courseModel", mainService.getVogueModel());
        return "courses/vouge";
    }

    @ModelAttribute("cloudinaryBaseUrl")
    public String cloudinaryBaseUrl() { return cloudinaryBaseUrl; }
    
    @ModelAttribute("cloudinaryTransfCommon")
    public String cloudinaryTransfCommon() { return cloudinaryTransfCommon; }

    @ModelAttribute("packages")
    public List<CoursePackage> packages() { return mainService.getPackages(); }


    //____________________________________________________________________________________________________________________


    @PostMapping("/contact/send-email")
    public String sendEmail(ContactForm contactForm, Model model) {
        log.info("[START] send-email");
        try{
            mainService.sendNotification(contactForm);
        } catch (Exception ex){
            log.error("Errore: {}", ex.getMessage());
            return "redirect:?sent=false#email-form";
        }
        log.info("[END] send-email");
        return "redirect:?sent=true&recipientName=" + contactForm.getName() + "#email-form";
    }

    @ResponseBody
    @GetMapping(value = "/courses/calendar", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamSource> getCalendarioLezioni() throws Exception {
        log.info("Recupero calendario lezioni");
        ClassPathResource pdfFile = new ClassPathResource("/static/files/calendario-lezioni.pdf");
        return new ResponseEntity<InputStreamSource>(new InputStreamResource(pdfFile.getInputStream()), HttpStatus.OK);
    }


}
