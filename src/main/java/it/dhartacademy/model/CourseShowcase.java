package it.dhartacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseShowcase {

    private String name;
    private String imageURL;
    private String motivatorText;
}
