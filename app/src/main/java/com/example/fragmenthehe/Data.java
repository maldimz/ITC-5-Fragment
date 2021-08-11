package com.example.fragmenthehe;

public class Data {
    private static String username, password, berat="", tinggi="", klasifikasi;
    private float bmi;

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public static String getKlasifikasi() {
        return klasifikasi;
    }

    public static void setKlasifikasi(String klasifikasi) {
        Data.klasifikasi = klasifikasi;
    }

    public static String getBerat() {
        return berat;
    }

    public static void setBerat(String berat) {
        Data.berat = berat;
    }

    public static String getTinggi() {
        return tinggi;
    }

    public static void setTinggi(String tinggi) {
        Data.tinggi = tinggi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
