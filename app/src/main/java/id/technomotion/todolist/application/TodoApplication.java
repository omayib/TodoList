package id.technomotion.todolist.application;

import android.app.Application;

import id.technomotion.todolist.repository.LocalDatabaseConfiguration;
import id.technomotion.todolist.repository.TodoRepository;

/**
 * Created by omayib on 25/02/16.
 */
public class TodoApplication extends Application {

    private TodoRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        LocalDatabaseConfiguration localDatabaseConfiguration =new LocalDatabaseConfiguration(this, LocalDatabaseConfiguration.DATABASE_NAME,
                null, LocalDatabaseConfiguration.DATABSE_VERSION);
        repository =new TodoRepository(localDatabaseConfiguration);
    }

    public TodoRepository getRepository() {
        return repository;
    }
}
