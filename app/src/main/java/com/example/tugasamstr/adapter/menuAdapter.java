package com.example.tugasamstr.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasamstr.R;
import com.example.tugasamstr.activity.AddMenuActivity;
import com.example.tugasamstr.activity.MenuActivity;
import com.example.tugasamstr.model.menuModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class menuAdapter extends FirestoreRecyclerAdapter<menuModel, menuAdapter.menuHolder> {

    private Context context;

    public menuAdapter(@NonNull FirestoreRecyclerOptions<menuModel> option, Context context){

        super(option);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull menuAdapter.menuHolder holder, int position, @NonNull final menuModel model) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        final String menuId = getSnapshots().getSnapshot(position).getId();

        holder.mJenisMenu.setText(model.getJenisMenu());
        holder.mJumlahKalori.setText(model.getJumlahKalori());
        holder.mNamaMenu.setText(model.getNamaMenu());
        holder.mMenuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addIntent = new Intent(context, AddMenuActivity.class);
                addIntent.putExtra("menuId", menuId);
                addIntent.putExtra("namaMenu", model.getNamaMenu());
                addIntent.putExtra("jenisMenu", model.getJenisMenu());
                addIntent.putExtra("jumlahKalori", model.getJumlahKalori());
                addIntent.putExtra("satuanMenu", model.getSatuanMenu());
                addIntent.putExtra("tokoId", model.getTokoId());
                context.startActivity(addIntent);

            }
        });

        holder.mJenisMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent jenisIntent = new Intent(context, MenuActivity.class);
                jenisIntent.putExtra("menuId", menuId);
                jenisIntent.putExtra("namaMenu", model.getNamaMenu());
                jenisIntent.putExtra("jenisMenu", model.getJenisMenu());
                jenisIntent.putExtra("jumlahKalori", model.getJumlahKalori());
                jenisIntent.putExtra("satuanMenu", model.getSatuanMenu());
                jenisIntent.putExtra("tokoId", model.getTokoId());
                context.startActivity(jenisIntent);

            }
        });

        holder.mJumlahKalori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kaloriIntent = new Intent(context, MenuActivity.class);
                kaloriIntent.putExtra("menuId", menuId);
                kaloriIntent.putExtra("namaMenu", model.getNamaMenu());
                kaloriIntent.putExtra("jenisMenu", model.getJenisMenu());
                kaloriIntent.putExtra("jumlahKalori", model.getJumlahKalori());
                kaloriIntent.putExtra("satuanMenu", model.getSatuanMenu());
                kaloriIntent.putExtra("tokoId", model.getTokoId());
                context.startActivity(kaloriIntent);


            }
        });

        holder.mNamaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent namaIntent = new Intent(context, MenuActivity.class);
                namaIntent.putExtra("menuId", menuId);
                namaIntent.putExtra("namaMenu", model.getNamaMenu());
                namaIntent.putExtra("jenisMenu", model.getJenisMenu());
                namaIntent.putExtra("jumlahKalori", model.getJumlahKalori());
                namaIntent.putExtra("satuanMenu", model.getSatuanMenu());
                namaIntent.putExtra("tokoId", model.getTokoId());
                context.startActivity(namaIntent);

            }
        });

    }

    @NonNull
    @Override
    public menuAdapter.menuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_makanan, parent, false);
        return new menuAdapter.menuHolder(view);

    }

    class menuHolder extends RecyclerView.ViewHolder {

        TextView mNamaMenu, mJenisMenu, mJumlahKalori;
        Button mMenuAdd;

        menuHolder(@NonNull View itemView){
            super(itemView);

            mNamaMenu = itemView.findViewById(R.id.menuNama);
            mJenisMenu = itemView.findViewById(R.id.menuJenis);
            mJumlahKalori = itemView.findViewById(R.id.menuKalori);
            mMenuAdd = itemView.findViewById(R.id.menuAdd);

        }

    }

}
