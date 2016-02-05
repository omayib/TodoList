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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void setupView() {
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        buttonLogin= (Button) findViewById(R.id.buttonLoagin);
        textViewRegister= (TextView) findViewById(R.id.textViewRegister);
    }
}
