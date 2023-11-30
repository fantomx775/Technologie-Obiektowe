package pl.edu.agh.to.school.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.course.CourseRepository;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.grade.GradeRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfigurator {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository, GradeRepository gradeRepository) {
        return args -> {
            if (studentRepository.count() == 0) {
                Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
                studentRepository.save(kowalski);
                Student nowak = new Student("Adam", "Nowak", LocalDate.now(), "654321");
                studentRepository.save(nowak);
                Student kowalska = new Student("Anna", "Kowalska", LocalDate.now(), "123457");
                studentRepository.save(kowalska);
                Course math = new Course("Math");
                Course physics = new Course("Physics");
                courseRepository.save(math);
                courseRepository.save(physics);
                math.assignStudent(kowalski);
                math.assignStudent(nowak);
                physics.assignStudent(kowalska);
                physics.assignStudent(nowak);

                Grade grade1 = new Grade(5, math);
                Grade grade2 = new Grade(4, math);
                Grade grade3 = new Grade(3, physics);
                Grade grade4 = new Grade(2, physics);

                kowalska.giveGrade(grade1);
                kowalska.giveGrade(grade3);
                kowalski.giveGrade(grade2);
                nowak.giveGrade(grade4);

                gradeRepository.saveAll(List.of(grade1, grade2, grade3, grade4));
                studentRepository.saveAll(List.of(kowalski, nowak, kowalska));
                courseRepository.saveAll(List.of(math, physics));
        }
        };
    }
}
