package pl.edu.agh.iisg.to.dao;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;

public class StudentDao extends GenericDao<Student> {

    public Optional<Student> create(final String firstName, final String lastName, final int indexNumber) {
        Session session = currentSession().getSession();
        Transaction tx = session.beginTransaction();
        Student student = new Student(firstName, lastName, indexNumber);
        try {
            session.save(student);
            tx.commit();
            return Optional.of(student);

        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Student> findByIndexNumber(final int indexNumber) {
        Session session = currentSession().getSession();
        Transaction tx = session.beginTransaction();
        try {
            Student student = session.createQuery("SELECT s FROM Student s WHERE s.indexNumber = :indexNumber", Student.class)
                    .setParameter("indexNumber", indexNumber)
                    .getSingleResult();
            tx.commit();
            return Optional.of(student);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Map<Course, Float> createReport(final Student student) {
        //TODO additional task
        return Collections.emptyMap();
    }

}
