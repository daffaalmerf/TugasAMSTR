package com.example.tugasamstr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugasamstr.R;
import com.example.tugasamstr.adapter.menuAdapter;
import com.example.tugasamstr.model.menuModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class TokoActivity extends AppCompatActivity {

    private FirebaseFirestore menuFirestore = FirebaseFirestore.getInstance();
    private com.example.tugasamstr.adapter.menuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko);

        String strNamaToko = getIntent().getStringExtra("namaToko");
        String strLokasiToko = getIntent().getStringExtra("lokasiToko");
        String strGambarToko = getIntent().getStringExtra("gambarToko");
        final String strIdToko = getIntent().getStringExtra("idToko");

        TextView namaToko = findViewById(R.id.detTokoNama);
        TextView lokasiToko = findViewById(R.id.detTokoLokasi);
        ImageView gambarToko = findViewById(R.id.tokoView);

        namaToko.setText(strNamaToko);
        lokasiToko.setText(strLokasiToko);
        Picasso.get().load(strGambarToko).into(gambarToko);

        Query query = menuFirestore.collection("Toko").document(strIdToko).collection("menuMakanan");

        FirestoreRecyclerOptions<menuModel> options = new FirestoreRecyclerOptions.Builder<menuModel>().setQuery(query, menuModel.class).build();

        menuAdapter = new menuAdapter(options, TokoActivity.this);

        RecyclerView listMenu = findViewById(R.id.detTokoListMenu);
        listMenu.setLayoutManager(new LinearLayoutManager(TokoActivity.this));
        listMenu.setAdapter(menuAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        menuAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        menuAdapter.stopListening();
    }

}
