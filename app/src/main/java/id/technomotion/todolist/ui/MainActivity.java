package id.technomotion.todolist.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import id.technomotion.todolist.R;
import id.technomotion.todolist.TodoApplication;
import id.technomotion.todolist.model.Todo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    FloatingActionButton fab;
    ListView listView;
    Toolbar toolbar;
    ArrayAdapter<Todo> todoAdapter;
    List<Todo> todos;
    TodoApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app= (TodoApplication) getApplication();

        app.getTodoLocalRepository().open();
        todos=app.getTodoLocalRepository().getAllItems();

        setupView();

    }

    private void setupView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView= (ListView) findViewById(R.id.listview);
        todoAdapter=new ArrayAdapter<Todo>(this,android.R.layout.simple_list_item_1,todos){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view=super.getView(position, convertView, parent);
                TextView textView= (TextView) view.findViewById(android.R.id.text1);
                textView.setText(getItem(position).item);
                return view;
            }
        };

        setSupportActionBar(toolbar);
        listView.setAdapter(todoAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddItemActivity.class), 212);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentData = new Intent(MainActivity.this, AddItemActivity.class);
                intentData.putExtra("item", ((Todo) adapterView.getItemAtPosition(i)).item);
                intentData.putExtra("id", ((Todo) adapterView.getItemAtPosition(i)).id);
                startActivityForResult(intentData, 213);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("warning");
                alert.setMessage("are you sure to delete it?");
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        processToDelete(((Todo) adapterView.getItemAtPosition(position)));
                        dialogInterface.dismiss();
                    }
                });
                alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.show();
                return false;
            }
        });
    }

    private void processToDelete(Todo todoItem) {
        app.getTodoLocalRepository().deleteItem(todoItem.id);
        todos.remove(todoItem);
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult:requestCode "+requestCode);
        if(resultCode==RESULT_OK&& requestCode==212){
            Todo newTodo=new Todo(UUID.randomUUID().toString(),data.getStringExtra("item"));
            app.getTodoLocalRepository().addItem(newTodo);
            todos.add(newTodo);
            todoAdapter.notifyDataSetChanged();
        }
        if(resultCode==RESULT_OK && requestCode==213){
            Todo todoUpdated=new Todo(data.getStringExtra("id"),data.getStringExtra("item"));
            app.getTodoLocalRepository().updateItem(todoUpdated.item,todoUpdated.id);
            if(todos.contains(todoUpdated)){
                todos.set(todos.indexOf(todoUpdated),todoUpdated);
                todoAdapter.notifyDataSetChanged();
            }
        }
    }
}
