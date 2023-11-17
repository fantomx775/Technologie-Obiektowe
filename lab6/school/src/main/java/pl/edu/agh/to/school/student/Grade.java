package pl.edu.agh.to.school.student;

import javax.persistence.OneToOne;

public class Grade {

    private int id;
    private int gradeValue;
    private Course course;

    public Grade(int gradeValue, Course course) {
        this.gradeValue = gradeValue;
        this.course = course;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    @OneToOne
    public Course getCourse() {
        return course;
    }
}
