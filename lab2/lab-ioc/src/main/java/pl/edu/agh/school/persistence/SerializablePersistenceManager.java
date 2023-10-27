package pl.edu.agh.school.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Teacher;


import javax.inject.Inject;
import javax.inject.Named;

public final class SerializablePersistenceManager implements IPersistenceManager {

//    private static final Logger log = Logger.getInstance();
    @Inject
    private Logger logger;
    private String teachersStorageFileName;

    private String classStorageFileName;

    @Inject
    public SerializablePersistenceManager() {
    }

    @Override
    public void saveTeachers(List<Teacher> teachers) {
        if (teachers == null) {
            throw new IllegalArgumentException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teachersStorageFileName))) {
            oos.writeObject(teachers);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            logger.log("There was an error while saving the teachers data", e);
        }
    }

    @Inject
    public void setClassStorageFileName(@Named("classStorageFileName") String classStorageFileName) {
        this.classStorageFileName = classStorageFileName;
    }
    @Inject
    public void setTeachersStorageFileName(@Named("teachersStorageFileName") String teachersStorageFileName) {
        this.teachersStorageFileName = teachersStorageFileName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Teacher> loadTeachers() {
        ArrayList<Teacher> res = null;
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(teachersStorageFileName))) {

            res = (ArrayList<Teacher>) ios.readObject();
        } catch (FileNotFoundException e) {
            res = new ArrayList<>();
        } catch (IOException e) {
            logger.log("There was an error while loading the teachers data", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return res;
    }

    @Override
    public void saveClasses(List<SchoolClass> classes) {
        if (classes == null) {
            throw new IllegalArgumentException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(classStorageFileName))) {

            oos.writeObject(classes);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            logger.log("There was an error while saving the classes data", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SchoolClass> loadClasses() {
        ArrayList<SchoolClass> res = null;
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(classStorageFileName))) {
            res = (ArrayList<SchoolClass>) ios.readObject();
        } catch (FileNotFoundException e) {
            res = new ArrayList<>();
        } catch (IOException e) {
            logger.log("There was an error while loading the classes data", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return res;
    }
}
