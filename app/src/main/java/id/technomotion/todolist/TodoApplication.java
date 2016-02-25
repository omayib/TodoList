package id.technomotion.todolist;

import android.app.Application;

import id.technomotion.todolist.repository.TodoLocalRepository;

/**
 * Created by omayib on 25/02/16.
 */
public class TodoApplication extends Application {

    private TodoLocalRepository todoDataSource;

    @Override
    public void onCreate() {
        super.onCreate();
        todoDataSource=new TodoLocalRepository(this);
    }

    public TodoLocalRepository getTodoLocalRepository() {
        return todoDataSource;
    }
}
