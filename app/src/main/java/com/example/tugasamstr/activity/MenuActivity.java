package com.example.tugasamstr.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugasamstr.R;
import com.example.tugasamstr.adapter.kandunganAdapter;
import com.example.tugasamstr.model.kandunganModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class MenuActivity extends AppCompatActivity {

    private FirebaseFirestore kandunganFirestore = FirebaseFirestore.getInstance();
    private com.example.tugasamstr.adapter.kandunganAdapter kandunganAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String strNamaMenu = getIntent().getStringExtra("namaMenu");
        String strJenisMenu = getIntent().getStringExtra("jenisMenu");
        String strKaloriMenu = getIntent().getStringExtra("jumlahKalori");
        String strSatuanMenu = getIntent().getStringExtra("satuanMenu");
        String strSajianMenu = "(Per " + strSatuanMenu + ")";
        String idMenu = getIntent().getStringExtra("menuId");
        String idToko = getIntent().getStringExtra("tokoId");

        TextView namaMenu = findViewById(R.id.detMenuNama);
        TextView jenisMenu = findViewById(R.id.detMenuJenis);
        TextView kaloriMenu = findViewById(R.id.detMenuKalori);
        TextView satuanMenu = findViewById(R.id.detMenuSajian);
        final ImageView gambarMenu = findViewById(R.id.menuImage);

        DocumentReference refGambar = kandunganFirestore.collection("Toko").document(idToko);

        refGambar.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                    DocumentSnapshot documentSnapshot = task.getResult();

                    String strGambarToko = String.valueOf(documentSnapshot.get("gambarToko"));

                    Picasso.get().load(strGambarToko).into(gambarMenu);

                }

            }
        });


        namaMenu.setText(strNamaMenu);
        jenisMenu.setText(strJenisMenu);
        kaloriMenu.setText(strKaloriMenu);
        satuanMenu.setText(strSajianMenu);

        Query query = kandunganFirestore.collection("Toko").document(idToko).collection("menuMakanan").document(idMenu).collection("kandunganMenu");

        FirestoreRecyclerOptions<kandunganModel> options = new FirestoreRecyclerOptions.Builder<kandunganModel>().setQuery(query, kandunganModel.class).build();

        kandunganAdapter = new kandunganAdapter(options, MenuActivity.this);

        RecyclerView listMenu = findViewById(R.id.detMenuKomposisiList);
        listMenu.setLayoutManager(new LinearLayoutManager(MenuActivity.this));
        listMenu.setAdapter(kandunganAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        kandunganAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        kandunganAdapter.stopListening();
    }
}
