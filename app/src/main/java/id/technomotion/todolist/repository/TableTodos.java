package id.technomotion.todolist.repository;

/**
 * Created by omayib on 02/03/16.
 */
public class TableTodos {
    public static final String TABLE_NAME="todos";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_ITEM="item";
    public static final String COLUMN_TIMESTAMP="timestamp";
    public static final String COLUMN_SYNCHRONIZED="synchronized";

    public static final String QUERY_CREATE="CREATE TABLE %s ( %s TEXT, %s TEXT NOT NULL, %s TEXT, %s INTEGER DEFAULT 0)";
    public static final String QUERY_DROP_TABLE="DROP TABLE IF EXISTS %s";

    public static final String CREATE_TABLE =String.format(QUERY_CREATE,TABLE_NAME,COLUMN_ID,COLUMN_ITEM,COLUMN_TIMESTAMP,COLUMN_SYNCHRONIZED);
    public static final String DROP_TABLE=String.format(QUERY_DROP_TABLE,TABLE_NAME);
}