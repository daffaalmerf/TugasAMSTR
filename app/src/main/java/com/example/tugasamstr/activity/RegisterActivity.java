package com.example.tugasamstr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tugasamstr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        final ProgressBar mBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);

        final EditText registerName = findViewById(R.id.registerName);
        final EditText registerEmail = findViewById(R.id.registerEmail);
        final EditText registerPassword = findViewById(R.id.registerPassword);
        final EditText registerVerifyPassword = findViewById(R.id.registerVerifyPassword);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String regName = registerName.getText().toString();
                final String regEmail = registerEmail.getText().toString();
                final String regPassword = registerPassword.getText().toString();
                final String regVerifyPassword = registerVerifyPassword.getText().toString();

                if(TextUtils.isEmpty(regName)){

                    registerName.setError("Nama Tidak Valid");

                }

                if(TextUtils.isEmpty(regEmail)){

                    registerEmail.setError("Alamat Surel Tidak Valid");

                }

                if(TextUtils.isEmpty(regPassword)){

                    registerPassword.setError("Kata Sandi Tidak Valid");

                }

                if(TextUtils.isEmpty(regVerifyPassword)){

                    registerVerifyPassword.setError("Verifikasi Kata Sandi Tidak Valid");

                }

                if(!regVerifyPassword.equals(regPassword)){

                    registerVerifyPassword.setError("Verifikasi Kata Sandi Tidak Valid");

                }

                if(!TextUtils.isEmpty(regName) && !TextUtils.isEmpty(regEmail) && !TextUtils.isEmpty(regPassword) && !TextUtils.isEmpty(regVerifyPassword) && regVerifyPassword.equals(regPassword)) {

                    mAuth.createUserWithEmailAndPassword(regEmail, regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String userId = user.getUid();
                            DatabaseReference mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                            HashMap<String, String> userMap = new HashMap<>();
                            userMap.put("username", regName);
                            userMap.put("thumbnail", "default");
                            userMap.put("email", regEmail);
                            mUserDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(RegisterActivity.this, "Account Is Successfully Registered", Toast.LENGTH_SHORT).show();

                                        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(mainIntent);
                                        finish();

                                    } else {

                                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                        }
                    });



                }



            }
        });

    }
}
