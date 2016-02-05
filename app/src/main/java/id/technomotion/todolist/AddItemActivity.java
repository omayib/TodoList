package id.technomotion.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by omayib on 05/02/16.
 */
public class AddItemActivity extends AppCompatActivity {

    EditText editTextItem;
    Button buttonSave,buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        setupView();
    }

    private void setupView() {
        editTextItem= (EditText) findViewById(R.id.editText3);
        buttonCancel= (Button) findViewById(R.id.button2);
        buttonSave= (Button) findViewById(R.id.button3);
    }
}
