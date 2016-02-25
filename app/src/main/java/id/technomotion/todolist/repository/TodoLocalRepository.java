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
    private TodoDatabase todoDatabase;
    private String[] allColumn=new String[]{TodoDatabase.COLUMN_ID,TodoDatabase.COLUMN_ITEM,
            TodoDatabase.COLUMN_SYNCHRONIZED,TodoDatabase.COLUMN_TIMESTAMP};

    public TodoLocalRepository(Context context){
        todoDatabase =new TodoDatabase(context, TodoDatabase.DATABASE_NAME,
                null, TodoDatabase.DATABSE_VERSION);
    }
    public void open(){
        database= todoDatabase.getWritableDatabase();
    }
    public void close(){
        todoDatabase.close();
    }

    public void addItem(Todo itemTodo){
        ContentValues cv=new ContentValues();
        cv.put(TodoDatabase.COLUMN_ITEM,itemTodo.item);
        cv.put(TodoDatabase.COLUMN_ID, itemTodo.id);
        database.insert(TodoDatabase.TABLE_NAME,null,cv);
    }
    public List<Todo> getAllItems(){
        List<Todo> todos=new ArrayList<>();
        Cursor cursor=database.query(TodoDatabase.TABLE_NAME, allColumn, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Todo todo=new Todo(cursor.getString(0),cursor.getString(1));
            todos.add(todo);
            cursor.moveToNext();
        }
        cursor.close();
        return todos;

    }
    public void deleteItem(String id){
        int deletedStatus=database.delete(TodoDatabase.TABLE_NAME, TodoDatabase.COLUMN_ID+"=\""+id+"\"", null);
        Log.d(TAG, "deleteItem: statuscode " +deletedStatus);
    }
    public void updateItem(String item, String id){
        Log.d(TAG, "updateItem() called with: " + "item = [" + item + "], id = [" + id + "]");
        ContentValues cv=new ContentValues();
        cv.put(TodoDatabase.COLUMN_ITEM, item);
        int updateStatusCode=database.update(TodoDatabase.TABLE_NAME,cv,TodoDatabase.COLUMN_ID+"=\""+id+"\"",null);
        Log.d(TAG, "updateItem: updateStatusCode : "+updateStatusCode);
    }
}
