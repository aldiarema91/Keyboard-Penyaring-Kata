package com.imgncreative.keyboardsehat.model;

public class Data {
    private String id, text, status, akurasi;

    public Data() {
    }

    public Data(String id, String text, String status, String akurasi) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.akurasi = akurasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAkurasi() {
        return akurasi;
    }

    public void setAkurasi(String akurasi) {
        this.akurasi = akurasi;
    }
}
