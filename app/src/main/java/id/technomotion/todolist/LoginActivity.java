package id.technomotion.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
        setupView();
        setupViewListener();
    }

    private void setupViewListener() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //any code here...
                processLogin();
            }
        });
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //any code here...
            }
        });
    }

    private void processLogin() {
        String email=editTextEmail.getText().toString();
        String password=editTextPassword.getText().toString();

        if(email.equalsIgnoreCase("coba@email.com") && password.equalsIgnoreCase("1234")){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            Snackbar.make(buttonLogin,"login failed",Snackbar.LENGTH_SHORT).show();
        }
    }

    private void setupView() {
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        buttonLogin= (Button) findViewById(R.id.buttonLoagin);
        textViewRegister= (TextView) findViewById(R.id.textViewRegister);
    }
}
