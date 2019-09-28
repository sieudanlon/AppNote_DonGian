package com.example.appnote_dongian;

public class CongViec {
    private int idCV;

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public CongViec(int idCV, String tenCV) {
        this.idCV = idCV;
        this.tenCV = tenCV;
    }

    private String tenCV;
}
