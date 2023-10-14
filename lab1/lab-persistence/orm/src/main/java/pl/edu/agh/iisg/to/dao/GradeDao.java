package pl.edu.agh.iisg.to.dao;

import org.hibernate.Session;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;

public class GradeDao extends GenericDao<Grade> {

    public boolean gradeStudent(final Student student, final Course course, final float grade) {
        try{
            Session session = currentSession().getSession();
            Grade grade1 = new Grade(student, course, grade);
            student.gradeSet().add(grade1);
            course.gradeSet().add(grade1);
            session.update(grade);
        }catch (PersistenceException e){
            e.printStackTrace();
//            return false;
        }
        return true;
    }


}
