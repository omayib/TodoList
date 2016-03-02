package id.technomotion.todolist.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.technomotion.todolist.model.Todo;

/**
 * Created by omayib on 25/02/16.
 */
public class TodoRepository {
    private static final String TAG = "TodoRepository";
    private SQLiteDatabase database;
    private LocalDatabaseConfiguration todoDatabase;
    private String[] allColumn=new String[]{TableTodos.COLUMN_ID, TableTodos.COLUMN_ITEM,
            TableTodos.COLUMN_SYNCHRONIZED, TableTodos.COLUMN_TIMESTAMP};

    public TodoRepository(LocalDatabaseConfiguration databaseConfiguration){
        this.todoDatabase =databaseConfiguration;
    }
    public void open(){
        database= todoDatabase.getWritableDatabase();
    }
    public void close(){
        todoDatabase.close();
    }

    public void insert(Todo itemTodo){
        ContentValues cv=new ContentValues();
        cv.put(TableTodos.COLUMN_ITEM,itemTodo.item);
        cv.put(TableTodos.COLUMN_ID, itemTodo.id);
        database.insert(TableTodos.TABLE_NAME, null, cv);
    }
    public Todo find(Todo todo){
        Cursor cursor=database.query(TableTodos.TABLE_NAME,allColumn,
                TableTodos.COLUMN_ID+"=\""+todo.id+"\"",null,null,null,null);
        cursor.moveToNext();
        return new Todo(cursor.getString(0),cursor.getString(1));
    }
    public List<Todo> findAll(){
        List<Todo> todos=new ArrayList<>();
        Cursor cursor=database.query(TableTodos.TABLE_NAME, allColumn, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Todo todo=new Todo(cursor.getString(0),cursor.getString(1));
            todos.add(todo);
            cursor.moveToNext();
        }
        cursor.close();
        return todos;

    }
    public void delete(Todo itemTobeDeleted){
        int deletedStatus=database.delete(TableTodos.TABLE_NAME, TableTodos.COLUMN_ID+"=\""+itemTobeDeleted.id+"\"", null);
        Log.d(TAG, "delete: statuscode " +deletedStatus);
    }
    public void update(Todo itemTobeUpdated){
        Log.d(TAG, "update() called with: " + "item = [" + itemTobeUpdated.item + "], id = [" + itemTobeUpdated.id + "]");
        ContentValues cv=new ContentValues();
        cv.put(TableTodos.COLUMN_ITEM, itemTobeUpdated.item);
        int updateStatusCode=database.update(TableTodos.TABLE_NAME,cv, TableTodos.COLUMN_ID+"=\""+itemTobeUpdated.id+"\"",null);
        Log.d(TAG, "update: updateStatusCode : "+updateStatusCode);
    }
}
