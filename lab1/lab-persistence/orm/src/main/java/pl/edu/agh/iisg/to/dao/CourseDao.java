package pl.edu.agh.iisg.to.dao;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Student;

public class CourseDao extends GenericDao<Course> {

    public Optional<Course> create(final String name) {
        try {
            Course course = save(new Course(name));
            return Optional.of(course);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Course> findById(final int id) {
        try {
            return currentSession().createQuery("SELECT c FROM Course c WHERE c.id = :id", Course.class)
                    .setParameter("id", id).uniqueResultOptional();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Course> findByName(final String name) {
        try {
            return currentSession().createQuery("SELECT c FROM Course c WHERE c.name = :name", Course.class)
                    .setParameter("name", name).uniqueResultOptional();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean enrollStudent(final Course course, final Student student) {
        try{
            if(course.studentSet().contains(student)){
                return false;
            }
            Session session = currentSession().getSession();
            course.studentSet().add(student);
            student.courseSet().add(course);
            session.update(course);
            return true;
        }catch (PersistenceException e){
            e.printStackTrace();
        }
        return false;
    }
}
