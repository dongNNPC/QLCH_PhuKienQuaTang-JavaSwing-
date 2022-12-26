/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.Entity;

/**
 *
 * @author MSI PC
 */
public class HoaDonChiTiet {

    String maPNCT;
    int soLuong;
    String maSP;
    String maHD;

    public HoaDonChiTiet() {
    }
    
    
    public HoaDonChiTiet(String maPNCT, int soLuong, String maSP, String maHD) {
        this.maPNCT = maPNCT;
        this.soLuong = soLuong;
        this.maSP = maSP;
        this.maHD = maHD;
    }

    public String getMaPNCT() {
        return maPNCT;
    }

    public void setMaPNCT(String maPNCT) {
        this.maPNCT = maPNCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

}
