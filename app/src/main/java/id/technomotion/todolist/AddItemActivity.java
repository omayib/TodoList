package id.technomotion.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        setupViewListener();
    }

    private void setupViewListener() {
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemEntered=editTextItem.getText().toString();
                Intent intentData=new Intent();
                intentData.putExtra("item",itemEntered);
                setResult(RESULT_OK,intentData);
                finish();
            }
        });
    }

    private void setupView() {
        editTextItem= (EditText) findViewById(R.id.editText3);
        buttonCancel= (Button) findViewById(R.id.button2);
        buttonSave= (Button) findViewById(R.id.button3);
    }
}
