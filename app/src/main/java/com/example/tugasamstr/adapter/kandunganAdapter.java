package com.example.tugasamstr.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasamstr.R;
import com.example.tugasamstr.model.kandunganModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class kandunganAdapter extends FirestoreRecyclerAdapter<kandunganModel, kandunganAdapter.kandunganHolder> {

    private Context context;

    public kandunganAdapter(@NonNull FirestoreRecyclerOptions<kandunganModel> option, Context context){

        super(option);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull kandunganAdapter.kandunganHolder holder, int position, @NonNull final kandunganModel model) {

        holder.mNamaKandungan.setText(model.getNamaKandungan());
        holder.mBeratKandungan.setText(model.getBeratKandungan());
    }

    @NonNull
    @Override
    public kandunganAdapter.kandunganHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_kandungan, parent, false);
        return new kandunganAdapter.kandunganHolder(view);

    }

    class kandunganHolder extends RecyclerView.ViewHolder {

        TextView mNamaKandungan, mBeratKandungan;

        kandunganHolder(@NonNull View itemView){
            super(itemView);

            mNamaKandungan = itemView.findViewById(R.id.kandunganNama);
            mBeratKandungan = itemView.findViewById(R.id.kandunganBerat);

        }

    }

}
