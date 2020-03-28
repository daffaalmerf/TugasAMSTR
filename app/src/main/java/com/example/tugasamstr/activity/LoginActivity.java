package com.example.tugasamstr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasamstr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView loginRegister = findViewById(R.id.loginRegister);
        final EditText loginEmail = findViewById(R.id.loginEmail);
        final EditText loginPassword = findViewById(R.id.loginPassword);

        Button loginButton = findViewById(R.id.loginEnter);

        final ProgressBar loginBar = findViewById(R.id.progressLogin);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String logEmail = loginEmail.getText().toString();
                final String logPassword = loginPassword.getText().toString();

                if(TextUtils.isEmpty(logEmail)){

                    loginEmail.setError("Alamat Surel Tidak Valid");

                }

                if(TextUtils.isEmpty(logPassword)){

                    loginPassword.setError("Kata Sandi Tidak Valid");

                }

                if(!TextUtils.isEmpty(logEmail) && !TextUtils.isEmpty(logPassword)) {

                    mAuth.signInWithEmailAndPassword(logEmail, logPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            loginBar.setMax(100);
                            loginBar.setVisibility(View.VISIBLE);

                            if (task.isSuccessful()) {

                                Intent accountRegister = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(accountRegister);
                                finish();
                                loginBar.setVisibility(View.GONE);


                            } else {

                                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                                loginBar.setVisibility(View.GONE);


                            }

                        }
                    });

                }

            }
        });

    }
}
