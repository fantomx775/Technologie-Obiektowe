package pl.edu.agh.to.school.student;

import javax.persistence.ManyToOne;
import java.util.List;

public class Course {
    private String name;

    @ManyToOne
    private List<String> students;

    public Course(String name) {
        this.name = name;
    }

    public void assignStudent(Student student) {

    }

    public void removeStudent(Student student) {

    }
}
