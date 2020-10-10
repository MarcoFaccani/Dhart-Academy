package it.dhartacademy.repository;

import it.dhartacademy.model.CourseModel;
import it.dhartacademy.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseTeacherRepository extends MongoRepository<CourseModel.CourseTeacher, String> {

    List<CourseModel.CourseTeacher> findByCoursesContaining(String courseName);
    Optional<CourseModel.CourseTeacher> findByNameAndSurname(String name, String surname);

}
