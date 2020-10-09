package it.dhartacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseTeacher {
        private String name;
        private String surname;
        private String artName;
        private String biography;
        private String experience;
        private String closeUpURL;

        private String facebook;
        private String instagram;
        private String youtube;
        private String twitter;
    }

    private String name;
    private String showcaseImageURL;
    private String showcaseMotivatorText;
    private List<CourseTeacher> teachers;
}
