package id.technomotion.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ListView listView;
    ArrayList<String> todos=new ArrayList<>();
    ArrayAdapter<String> todosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
        setupViewListener();
        showTheDataIntoListview();
    }

    private void setupViewListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddItemActivity.class));
            }
        });
    }

    private void showTheDataIntoListview() {
        for (int i = 0; i < 20; i++) {
            todos.add("item todo - " + i);
        }
        todosAdapter.notifyDataSetChanged();
    }

    private void setupView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView= (ListView) findViewById(R.id.listview);

        todosAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todos);

        listView.setAdapter(todosAdapter);
    }
}
