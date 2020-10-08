package it.dhartacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePackage {

    private String name;
    private int price;
    private String includedCourses;
    private int hoursPerWeek;
    private int openTrainingDays;
    private String packageLogoURL;
    //private String cardBodyBackgroundImage;
}
