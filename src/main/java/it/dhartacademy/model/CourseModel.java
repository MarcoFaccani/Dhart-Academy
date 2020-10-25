package it.dhartacademy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseTeacher {
        private String name;
        private String surname;
        private String artName;
        private List<String> courses;
        private String biography;
        private String experience;
        private String closeUpURL;
        private List<Award> awards;

        private String facebook;
        private String instagram;
        private String youtube;
        private String twitter;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Award {

            private String country;
            private String achievement;
            private String eventName;
        }
    }

    private String name;
    private String showcaseVideoURL;
    private String showcaseImageURL;
    private String showcaseMotivatorText;
    private List<CourseTeacher> teachers;
}
