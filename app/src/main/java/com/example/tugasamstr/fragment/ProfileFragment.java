package com.example.tugasamstr.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tugasamstr.R;
import com.example.tugasamstr.activity.CalorieActivity;
import com.example.tugasamstr.activity.LoginActivity;
import com.example.tugasamstr.adapter.riwayatAdapter;
import com.example.tugasamstr.adapter.tokoAdapter;
import com.example.tugasamstr.model.riwayatModel;
import com.example.tugasamstr.model.tokoModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseFirestore riwayatFirestore = FirebaseFirestore.getInstance();
    private com.example.tugasamstr.adapter.riwayatAdapter riwayatAdapter;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View riwayatView = inflater.inflate(R.layout.fragment_profile, container, false);

        String uid = mAuth.getUid();
        final DatabaseReference profileData = FirebaseDatabase.getInstance().getReference("Users").child(uid);

        TextView logOut = riwayatView.findViewById(R.id.profileLogout);
        TextView kaloriKalkulator = riwayatView.findViewById(R.id.profileCalorie);
        final TextView username = riwayatView.findViewById(R.id.profileName);

        profileData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("username").getValue().toString();

                username.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent logOut = new Intent(getContext(), LoginActivity.class);
                startActivity(logOut);
                getActivity().finish();

            }
        });

        kaloriKalkulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kaloriKalkulator = new Intent(getContext(), CalorieActivity.class);
                startActivity(kaloriKalkulator);

            }
        });

        Query query = riwayatFirestore.collection("Menu").document(uid).collection("riwayatMenu");

        FirestoreRecyclerOptions<riwayatModel> options = new FirestoreRecyclerOptions.Builder<riwayatModel>().setQuery(query, riwayatModel.class).build();

        riwayatAdapter = new riwayatAdapter(options, getContext());

        RecyclerView riwayatList = riwayatView.findViewById(R.id.profileRiwayat);
        riwayatList.setLayoutManager(new LinearLayoutManager(getActivity()));
        riwayatList.setAdapter(riwayatAdapter);

        return riwayatView;

    }


    @Override
    public void onStart() {
        super.onStart();
        riwayatAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        riwayatAdapter.stopListening();
    }


}
