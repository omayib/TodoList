package id.technomotion.todolist.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by omayib on 25/02/16.
 */
public class LocalDatabaseConfiguration extends SQLiteOpenHelper {
    private static final String TAG = "LocalDatabaseConfiguration";
    public static final String DATABASE_NAME="todo.db";
    public static final int DATABSE_VERSION=1;

    public LocalDatabaseConfiguration(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TableTodos.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TableTodos.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
