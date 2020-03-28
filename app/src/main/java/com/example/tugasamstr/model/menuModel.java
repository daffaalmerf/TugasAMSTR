package com.example.tugasamstr.model;

public class menuModel {

    private String jenisMenu;
    private String jumlahKalori;
    private String namaMenu;
    private String satuanMenu;
    private String tokoId;

    public menuModel(){

    }

    public menuModel(String jenisMenu, String jumlahKalori, String namaMenu, String satuanMenu, String tokoId){

        this.jenisMenu = jenisMenu;
        this.jumlahKalori = jumlahKalori;
        this.namaMenu = namaMenu;
        this.satuanMenu = satuanMenu;
        this.tokoId = tokoId;

    }

    public String getJenisMenu() {
        return jenisMenu;
    }

    public String getJumlahKalori() {
        return jumlahKalori;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public String getSatuanMenu() {
        return satuanMenu;
    }

    public String getTokoId() {
        return tokoId;
    }
}
