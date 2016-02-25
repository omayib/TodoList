package id.technomotion.todolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.technomotion.todolist.R;

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

        if(editMode()){
            setData();
        }
    }

    private void setData() {
        editTextItem.setText(getIntent().getStringExtra("item"));
    }

    private boolean editMode() {
        return getIntent().getStringExtra("item")!=null;
    }

    private void setupView() {
        editTextItem= (EditText) findViewById(R.id.editText3);
        buttonCancel= (Button) findViewById(R.id.button2);
        buttonSave= (Button) findViewById(R.id.button3);

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
                Intent intentData=new Intent();
                intentData.putExtra("item", editTextItem.getText().toString());
                if(editMode())intentData.putExtra("id", getIntent().getStringExtra("id"));
                setResult(RESULT_OK, intentData);
                finish();
            }
        });
    }
}
