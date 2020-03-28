package com.example.tugasamstr.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugasamstr.R;
import com.example.tugasamstr.adapter.tokoAdapter;
import com.example.tugasamstr.model.tokoModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class TokoFragment extends Fragment {

    private FirebaseFirestore tokoFirestore = FirebaseFirestore.getInstance();
    private com.example.tugasamstr.adapter.tokoAdapter tokoAdapter;

    public TokoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View tokoView = inflater.inflate(R.layout.fragment_toko, container, false);

        Query query = tokoFirestore.collection("Toko");

        FirestoreRecyclerOptions<tokoModel> options = new FirestoreRecyclerOptions.Builder<tokoModel>().setQuery(query, tokoModel.class).build();

        tokoAdapter = new tokoAdapter(options, getContext());

        RecyclerView tokoList = tokoView.findViewById(R.id.daftarToko);
        tokoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        tokoList.setAdapter(tokoAdapter);
        tokoList.setHasFixedSize(true);

        return tokoView;

    }

    @Override
    public void onStart() {
        super.onStart();
        tokoAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        tokoAdapter.stopListening();
    }

}
