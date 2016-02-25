package id.technomotion.todolist.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.technomotion.todolist.model.Todo;

/**
 * Created by omayib on 25/02/16.
 */
public class TodoLocalRepository {
    private static final String TAG = "TodoLocalRepository";
    private SQLiteDatabase database;
    private TodoDatabaseConfiguration todoDatabase;
    private String[] allColumn=new String[]{TodoDatabaseConfiguration.COLUMN_ID, TodoDatabaseConfiguration.COLUMN_ITEM,
            TodoDatabaseConfiguration.COLUMN_SYNCHRONIZED, TodoDatabaseConfiguration.COLUMN_TIMESTAMP};

    public TodoLocalRepository(Context context){
        todoDatabase =new TodoDatabaseConfiguration(context, TodoDatabaseConfiguration.DATABASE_NAME,
                null, TodoDatabaseConfiguration.DATABSE_VERSION);
    }
    public void open(){
        database= todoDatabase.getWritableDatabase();
    }
    public void close(){
        todoDatabase.close();
    }

    public void insert(Todo itemTodo){
        ContentValues cv=new ContentValues();
        cv.put(TodoDatabaseConfiguration.COLUMN_ITEM,itemTodo.item);
        cv.put(TodoDatabaseConfiguration.COLUMN_ID, itemTodo.id);
        database.insert(TodoDatabaseConfiguration.TABLE_NAME, null, cv);
    }
    public Todo find(Todo todo){
        Cursor cursor=database.query(TodoDatabaseConfiguration.TABLE_NAME,allColumn,
                TodoDatabaseConfiguration.COLUMN_ID+"=\""+todo.id+"\"",null,null,null,null);
        cursor.moveToNext();
        return new Todo(cursor.getString(0),cursor.getString(1));
    }
    public List<Todo> findAll(){
        List<Todo> todos=new ArrayList<>();
        Cursor cursor=database.query(TodoDatabaseConfiguration.TABLE_NAME, allColumn, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Todo todo=new Todo(cursor.getString(0),cursor.getString(1));
            todos.add(todo);
            cursor.moveToNext();
        }
        cursor.close();
        return todos;

    }
    public void delete(String id){
        int deletedStatus=database.delete(TodoDatabaseConfiguration.TABLE_NAME, TodoDatabaseConfiguration.COLUMN_ID+"=\""+id+"\"", null);
        Log.d(TAG, "delete: statuscode " +deletedStatus);
    }
    public void update(String item, String id){
        Log.d(TAG, "update() called with: " + "item = [" + item + "], id = [" + id + "]");
        ContentValues cv=new ContentValues();
        cv.put(TodoDatabaseConfiguration.COLUMN_ITEM, item);
        int updateStatusCode=database.update(TodoDatabaseConfiguration.TABLE_NAME,cv, TodoDatabaseConfiguration.COLUMN_ID+"=\""+id+"\"",null);
        Log.d(TAG, "update: updateStatusCode : "+updateStatusCode);
    }
}
