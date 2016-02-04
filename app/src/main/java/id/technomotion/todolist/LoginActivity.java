package id.technomotion.todolist;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by omayib on 05/02/16.
 */
public class LoginActivity extends AppCompatActivity {

    //deklarasi komponen dan variabel yang akan di control
    EditText editTextEmail,editTextPassword;
    Button buttonLogin;
    TextView textViewRegister;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);

        setupView();
    }

    private void setupView() {
        editTextEmail= (EditText) findViewById(R.id.editText);
        editTextPassword= (EditText) findViewById(R.id.editText2);
        buttonLogin= (Button) findViewById(R.id.button);
        textViewRegister= (TextView) findViewById(R.id.textView2);
    }
}
