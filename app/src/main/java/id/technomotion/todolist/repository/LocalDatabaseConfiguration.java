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

    public static final String TABLE_NAME="todos";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_ITEM="item";
    public static final String COLUMN_TIMESTAMP="timestamp";
    public static final String COLUMN_SYNCHRONIZED="synchronized";

    private final String QUERY_CREATE="CREATE TABLE %s ( %s TEXT, %s TEXT NOT NULL, %s TEXT, %s INTEGER DEFAULT 0)";
    private final String QUERY_DROP_TABLE="DROP TABLE IF EXISTS %s";

    private final String CREATE_TABLE =String.format(QUERY_CREATE,TABLE_NAME,COLUMN_ID,COLUMN_ITEM,COLUMN_TIMESTAMP,COLUMN_SYNCHRONIZED);
    private final String DROP_TABLE=String.format(QUERY_DROP_TABLE,TABLE_NAME);

    public LocalDatabaseConfiguration(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
