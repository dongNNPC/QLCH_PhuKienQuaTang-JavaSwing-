/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.Entity;

/**
 *
 * @author asus
 */
public class PhieuNhapChiTiet {

    String maPNCT;
    String maPN;
    String maSP;
    String soLuongNhap;
    double giaNhap;
    double giaBan;

    public PhieuNhapChiTiet() {
    }

    public PhieuNhapChiTiet(String maPNCT, String maPN, String maSP, String soLuongNhap, double giaNhap, double giaBan) {
        this.maPNCT = maPNCT;
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuongNhap = soLuongNhap;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public String getMaPNCT() {
        return maPNCT;
    }

    public void setMaPNCT(String maPNCT) {
        this.maPNCT = maPNCT;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(String soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

}
