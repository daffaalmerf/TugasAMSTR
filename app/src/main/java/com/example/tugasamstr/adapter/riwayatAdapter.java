package com.example.tugasamstr.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasamstr.R;
import com.example.tugasamstr.model.riwayatModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class riwayatAdapter extends FirestoreRecyclerAdapter<riwayatModel, riwayatAdapter.riwayatHolder> {

    private Context context;

    public riwayatAdapter(@NonNull FirestoreRecyclerOptions<riwayatModel> option, Context context) {

        super(option);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull riwayatAdapter.riwayatHolder holder, int position,
                                    @NonNull final riwayatModel model) {


        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        String creationDate = dateFormat.format(model.getTimestamp());

        holder.konsumsiTanggal.setText(creationDate);
        holder.konsumsiNama.setText(model.getNamaMenu());
        holder.konsumsiJenis.setText(model.getJenisMenu());

        String strKaloriPerSajian = model.getJumlahKalori();
        String strKaloriPerSatuan = model.getSatuanMenu();
        String strKalori = strKaloriPerSajian + " (Per " + strKaloriPerSatuan + ")";

        holder.konsumsiKalori.setText(strKalori);

        holder.konsumsiJumlah.setText(model.getSajianMenu());

    }

    @NonNull
    @Override
    public riwayatAdapter.riwayatHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_konsumsi, parent, false);
        return new riwayatAdapter.riwayatHolder(view);

    }

    class riwayatHolder extends RecyclerView.ViewHolder {

        TextView konsumsiTanggal, konsumsiNama, konsumsiJenis, konsumsiKalori, konsumsiJumlah;


        public riwayatHolder(@NonNull View itemView) {
                super(itemView);

            konsumsiTanggal = itemView.findViewById(R.id.konsumsiTanggal);
            konsumsiNama = itemView.findViewById(R.id.konsumsiNama);
            konsumsiJenis = itemView.findViewById(R.id.konsumsiJenis);
            konsumsiKalori = itemView.findViewById(R.id.konsumsiKalori);
            konsumsiJumlah = itemView.findViewById(R.id.konsumsiJumlah);


        }

    }

}