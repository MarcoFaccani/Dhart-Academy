package it.dhartacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {

    private String subject;

    @NotBlank(message = "Nome non può essere vuoto")
    private String name;

    @NotBlank(message = "Cognome non può essere vuoto")
    private String surname;

    @Email(message = "Formato email invalido")
    private String email;

    @Size(min = 10, max = 13, message = "Telefono invalido: necessarie 10 cifre")
    private String phoneNumber;

    @NotBlank(message = "Selezionare motivo di contatto")
    private String reasonForContact;

    @NotBlank(message = "Messaggio non può essere vuoto")
    private String message;
}
