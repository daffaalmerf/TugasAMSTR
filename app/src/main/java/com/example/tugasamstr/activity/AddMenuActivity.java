package com.example.tugasamstr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasamstr.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String uid = mAuth.getUid();
        final FirebaseFirestore mMenuAddFirestore = FirebaseFirestore.getInstance();

        TextView menuNama = findViewById(R.id.menuAddNama);
        TextView menuJenis = findViewById(R.id.menuAddJenis);
        TextView menuKalori = findViewById(R.id.menuAddKalori);
        final EditText menuSajian = findViewById(R.id.menuAddSajianEdit);
        Button menuAdd = findViewById(R.id.menuAddVerify);
        Button menuCancel = findViewById(R.id.menuAddCancel);

        final String strMenuNama = getIntent().getStringExtra("namaMenu");
        final String strJenisMenu = getIntent().getStringExtra("jenisMenu");
        final String strJumlahKalori = getIntent().getStringExtra("jumlahKalori");
        final String strSatuanMenu = getIntent().getStringExtra("satuanMenu");
        String hintSajian = "Per " + strSatuanMenu;

        menuNama.setText(strMenuNama);
        menuJenis.setText(strJenisMenu);
        menuKalori.setText(strJumlahKalori);

        menuSajian.setHint(hintSajian);

        menuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String strSajian = menuSajian.getText().toString();

                if(TextUtils.isEmpty(strSajian)){

                    menuSajian.setError("Input jumlah menu");

                } else {

                    Map<String, Object> userMenu = new HashMap<>();
                    userMenu.put("namaMenu", strMenuNama);
                    userMenu.put("jenisMenu", strJenisMenu);
                    userMenu.put("jumlahKalori", strJumlahKalori);
                    userMenu.put("satuanMenu", strSatuanMenu);
                    userMenu.put("sajianMenu", strSajian);
                    userMenu.put("timestamp", FieldValue.serverTimestamp());

                    mMenuAddFirestore.collection("Menu").document(uid).collection("riwayatMenu").add(userMenu).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(AddMenuActivity.this, "Menu Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                                Intent successUpload = new Intent(AddMenuActivity.this, MainActivity.class);
                                startActivity(successUpload);
                                finish();

                            } else {

                                Toast.makeText(AddMenuActivity.this, "Menu Gagal Ditambahkan", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }

            }
        });

        menuCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cancelAdd = new Intent(AddMenuActivity.this, MainActivity.class);
                startActivity(cancelAdd);
                finish();

            }
        });

    }
}
