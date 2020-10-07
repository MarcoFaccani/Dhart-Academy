package it.dhartacademy.controller;

import it.dhartacademy.model.ContactForm;
import it.dhartacademy.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

//____________________________________________________________________________________________________________________

    @GetMapping("/")
    public String getHomePage(Model model) {
        log.info("Recupero la home-page");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        model.addAttribute("reviews", mainService.getAllReviews());
        return "home";
    }


    @GetMapping("/courses/breakdance")
    public String getCourseBreakdance(Model model) {
        log.info("Recupero corsi/breakdance");
        model.addAttribute("cloudinaryBaseUrl", cloudinaryBaseUrl);
        model.addAttribute("cloudinaryTransfCommon", cloudinaryTransfCommon);
        return "breakdance";
    }



    @GetMapping("/contatti")
    public String getContactPage(Model model) {
        model.addAttribute("formContatti", new ContactForm());
        log.info("Recupero la pagina contatti");
        return "contact";
    }

    //TODO: aggiornare pagina contatti affinché rispecci il pojo ContactForm
    @PostMapping("/send-email")
    public String sendEmail(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Model model) {
        log.info("[START] send-email");

        if (bindingResult.hasErrors()) return "contact";

        try{
            mainService.sendNotification(contactForm);
        } catch (Exception ex){
            log.error("Errore: {}", ex.getMessage());
            //TODO: throw and handle exception
        }
        log.info("[END] send-email");
        return "redirect:contatti?sent=true";
    }


}
