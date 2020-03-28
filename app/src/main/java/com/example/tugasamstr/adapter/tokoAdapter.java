package com.example.tugasamstr.adapter;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasamstr.R;
import com.example.tugasamstr.activity.TokoActivity;
import com.example.tugasamstr.model.tokoModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

import javax.annotation.Nonnull;

public class tokoAdapter extends FirestoreRecyclerAdapter<tokoModel, tokoAdapter.tokoHolder> {

    private Context context;

    public tokoAdapter(@Nonnull FirestoreRecyclerOptions<tokoModel> option, Context context) {

        super(option);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull tokoAdapter.tokoHolder holder, int position, @NonNull final tokoModel model) {

        final String tokoId = getSnapshots().getSnapshot(position).getId();

        holder.mNamaToko.setText(model.getNamaToko());
        holder.mLokasiToko.setText(model.getLokasiToko());

        Picasso.get().load(model.getGambarToko()).into(holder.mGambarToko);

        holder.mNamaToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent namaIntent = new Intent(context, TokoActivity.class);
                namaIntent.putExtra("namaToko", model.getNamaToko());
                namaIntent.putExtra("lokasiToko", model.getLokasiToko());
                namaIntent.putExtra("gambarToko", model.getGambarToko());
                namaIntent.putExtra("idToko", tokoId);
                context.startActivity(namaIntent);


            }
        });

        holder.mLokasiToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lokasiIntent = new Intent(context, TokoActivity.class);
                lokasiIntent.putExtra("namaToko", model.getNamaToko());
                lokasiIntent.putExtra("lokasiToko", model.getLokasiToko());
                lokasiIntent.putExtra("gambarToko", model.getGambarToko());
                lokasiIntent.putExtra("idToko", tokoId);
                context.startActivity(lokasiIntent);

            }
        });

        holder.mGambarToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gambarIntent = new Intent(context, TokoActivity.class);
                gambarIntent.putExtra("namaToko", model.getNamaToko());
                gambarIntent.putExtra("lokasiToko", model.getLokasiToko());
                gambarIntent.putExtra("gambarToko", model.getGambarToko());
                gambarIntent.putExtra("idToko", tokoId);
                context.startActivity(gambarIntent);

            }
        });

    }

    @NonNull
    @Override
    public tokoAdapter.tokoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_toko, parent, false);
        return new tokoAdapter.tokoHolder(view);

    }

    class tokoHolder extends RecyclerView.ViewHolder {

        TextView mNamaToko, mLokasiToko;
        ImageView mGambarToko;

        tokoHolder(@NonNull View itemView){
            super(itemView);

            mNamaToko = itemView.findViewById(R.id.tokoNama);
            mLokasiToko = itemView.findViewById(R.id.tokoLokasi);
            mGambarToko = itemView.findViewById(R.id.tokoImage);


        }

    }

}
