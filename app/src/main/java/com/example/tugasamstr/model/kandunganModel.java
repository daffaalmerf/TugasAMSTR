package com.example.tugasamstr.model;

public class kandunganModel {

    private String namaKandungan;
    private String beratKandungan;

    public kandunganModel(){

    }

    public kandunganModel(String namaKandungan, String beratKandungan){

        this.namaKandungan = namaKandungan;
        this.beratKandungan = beratKandungan;

    }

    public String getNamaKandungan() {
        return namaKandungan;
    }

    public String getBeratKandungan() {
        return beratKandungan;
    }

}
