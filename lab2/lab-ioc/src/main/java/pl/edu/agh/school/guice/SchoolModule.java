package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import pl.edu.agh.school.persistence.IPersistenceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

import javax.inject.Inject;
import javax.inject.Named;

public class SchoolModule extends AbstractModule {
     @Provides
    protected IPersistenceManager providePersistenceManager(SerializablePersistenceManager serializablePersistenceManager) {
         return serializablePersistenceManager;
    }
    @Provides
    @Named("teachersStorageFileName")
    protected String provideTeachersStorageFileName() {
        return "teachers.dat";
    }

    @Provides
    @Named("classStorageFileName")
    protected String provideClassStorageFileName() {
        return "classes.dat";
    }

    @Provides
    @com.google.inject.name.Named("logFilename")
    public String provideLogFilename() {
        return "persistence.log";
    }




}
