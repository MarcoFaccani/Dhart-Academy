package it.dhartacademy.controller;

import it.dhartacademy.model.ContactForm;
import it.dhartacademy.repository.CourseTeacherRepository;
import it.dhartacademy.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("reviews", mainService.getAllReviews());
        model.addAttribute("awards", mainService.getHomeAwards());
        model.addAttribute("modalTitle", "Tournée \"50 Al Centro\"");
        model.addAttribute("modalYoutubeURL", "https://www.youtube.com/embed/tqDG6lqpEWU");
        return "home";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        log.info("Recupero la pagina contatti");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @GetMapping("/courses/breakdance")
    public String getCourseBreakdance(Model model) {
        log.info("Recupero corsi/breakdance");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("courseModel", mainService.getBreakdanceModel());
        model.addAttribute("packages", mainService.getPackages());
        return "courses/breakdance";
    }

    @GetMapping("/courses/dancehall")
    public String getCourseDancehall(Model model) {
        log.info("Recupero corsi/dancehall");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("courseModel", mainService.getDancehallModel());
        model.addAttribute("packages", mainService.getPackages());
        return "courses/dancehall";
    }

    @GetMapping("/courses/dj")
    public String getCourseDj(Model model) {
        log.info("Recupero corsi/dj");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("courseModel", mainService.getDjModel());
        model.addAttribute("packages", mainService.getPackages());
        return "courses/dj";
    }

    @GetMapping("/courses/floorwork")
    public String getCourseFloorwork(Model model) {
        log.info("Recupero corsi/floorwork");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("courseModel", mainService.getFloorworkModel());
        model.addAttribute("packages", mainService.getPackages());
        return "courses/floorwork";
    }

    @GetMapping("/courses/graffiti")
    public String getCourseGraffiti(Model model) {
        log.info("Recupero corsi/graffiti");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("courseModel", mainService.getGraffitiModel());
        model.addAttribute("packages", mainService.getPackages());
        return "courses/graffiti";
    }

    @GetMapping("/courses/hiphop")
    public String getCourseHipHop(Model model) {
        log.info("Recupero corsi/hiphop");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("courseModel", mainService.getHipHopModel());
        model.addAttribute("packages", mainService.getPackages());
        return "courses/hiphop";
    }

    @GetMapping("/courses/house")
    public String getCourseHouse(Model model) {
        log.info("Recupero corsi/house");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("courseModel", mainService.getHouseModel());
        model.addAttribute("packages", mainService.getPackages());
        return "courses/house";
    }

    @GetMapping("/courses/vogue")
    public String getCourseVogue(Model model) {
        log.info("Recupero corsi/vogue");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("courseModel", mainService.getVogueModel());
        model.addAttribute("packages", mainService.getPackages());
        return "courses/vouge";
    }



    //____________________________________________________________________________________________________________________


    @PostMapping("/contact/send-email")
    public String sendEmail(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Model model) {
        log.info("[START] send-email");
        //if (bindingResult.hasErrors()) return "contact";
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
    public FileSystemResource getCalendarioLezioni() throws Exception {
        log.info("Recupero calendario lezioni");
        return new FileSystemResource(Paths.get("src/main/resources/static/files/calendario-lezioni.pdf").toFile());
        //Binary fileBinario = mainService.getCalendarioLezioni();
        //return new FileSystemResource(Files.write(Paths.get("calendario-lezioni"), fileBinario.getData()));
    }


}
