package com.paket.userauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText eUsername,ePassword,eConfPassword,eEmail;
    Button btnSignUp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);

        eUsername = findViewById(R.id.edtUsername);
        ePassword = findViewById(R.id.password);
        eConfPassword = findViewById(R.id.confPassword);
        eEmail = findViewById(R.id.edtEmail);
        btnSignUp2 = findViewById(R.id.btnSignUp2);

        btnSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = eUsername.getText().toString();
                String password = ePassword.getText().toString();
                String confPassword = eConfPassword.getText().toString();
                String email = eEmail.getText().toString();

                if (username.equals("")||password.equals("")||confPassword.equals("")||email.equals("")) {
                    Toast.makeText(RegisterActivity.this,"Informations cannot be left blank!",Toast.LENGTH_LONG).show();
                }
                else {
                    if (password.equals(confPassword)) {
                        Boolean userKontrol = db.userKontrol(username);
                        if (userKontrol == true) {
                            Boolean addUser = db.addUser(username,password,email);
                            if (addUser == true) {
                                Toast.makeText(RegisterActivity.this,"Registration Completed.",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"The username is already taken.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                if (!password.equals(confPassword)) {
                    Toast.makeText(RegisterActivity.this,"Passwords not match!",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
