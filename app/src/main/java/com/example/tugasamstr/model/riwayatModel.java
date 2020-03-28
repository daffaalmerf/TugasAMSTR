package com.example.tugasamstr.model;

import com.google.firebase.firestore.FieldValue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class riwayatModel {

    private String namaMenu;
    private String jenisMenu;
    private String jumlahKalori;
    private String satuanMenu;
    private String sajianMenu;
    private Date timestamp;

    public riwayatModel(){

    }

    public riwayatModel(String namaMenu, String jenisMenu, String jumlahKalori, String satuanMenu, String sajianMenu, Date timestamp){

        this.namaMenu = namaMenu;
        this.jenisMenu = jenisMenu;
        this.jumlahKalori = jumlahKalori;
        this.satuanMenu = satuanMenu;
        this.sajianMenu = sajianMenu;
        this.timestamp = timestamp;

    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public String getJenisMenu() {
        return jenisMenu;
    }

    public String getJumlahKalori() {
        return jumlahKalori;
    }

    public String getSatuanMenu() {
        return satuanMenu;
    }

    public String getSajianMenu() {
        return sajianMenu;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
