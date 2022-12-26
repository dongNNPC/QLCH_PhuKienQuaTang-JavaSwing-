/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.Entity;

/**
 *
 * @author ASUS
 */
public class KhachHang {

    String MaKH;
    String TenKH;
    String SDT;
    int DiemTichLuy;

    public KhachHang() {
    }

    public KhachHang(String MaKH, String TenKH, String SDT, int DiemTichLuy) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.DiemTichLuy = DiemTichLuy;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getDiemTichLuy() {
        return DiemTichLuy;
    }

    public void setDiemTichLuy(int DiemTichLuy) {
        this.DiemTichLuy = DiemTichLuy;
    }
    
    
}
