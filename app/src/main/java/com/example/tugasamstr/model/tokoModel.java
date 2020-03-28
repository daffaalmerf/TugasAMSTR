package com.example.tugasamstr.model;

public class tokoModel {

    private String lokasiToko;
    private String namaToko;
    private String gambarToko;

    public tokoModel(){

    }

    public tokoModel(String lokasiToko, String namaToko, String gambarToko){

        this.lokasiToko = lokasiToko;
        this.namaToko = namaToko;
        this.gambarToko = gambarToko;

    }


    public String getLokasiToko() {
        return lokasiToko;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public String getGambarToko() {
        return gambarToko;
    }
}
