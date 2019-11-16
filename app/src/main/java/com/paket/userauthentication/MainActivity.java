package com.paket.userauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText edUsername,edPassword;
    Button btnSignUp,btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        final Cursor cursor = db.allData();

        edUsername = findViewById(R.id.edtUsername);
        edPassword = findViewById(R.id.edtPassword);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                db.allData();
                if (username.equals("")||password.equals("")) {
                    Toast.makeText(MainActivity.this,"Informations cannot be left blank",Toast.LENGTH_LONG).show();
                }
                else if (null != db.loginControl(username,password)) {
                    String userDb = db.loginControl(username,password);
                    Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Username or Password is wrong!",Toast.LENGTH_LONG).show();
                    edUsername.setText("");
                    edPassword.setText("");
                    edUsername.requestFocus();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });


    }


}
