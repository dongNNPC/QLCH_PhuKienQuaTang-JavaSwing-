/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.Entity;

/**
 *
 * @author ASUS
 */
public class SanPham {

    String maSP;
    String maVach;
    int maDM;
    int maDVT;
    String tenSP;
    int soLuongTonKho;
    String hinhAnh;
    String trangThai;

    public SanPham() {
    }

    public SanPham(String maSP, String maVach, int maDM, int maDVT, String tenSP, int soLuongTonKho, String hinhAnh, String trangThai) {
        this.maSP = maSP;
        this.maVach = maVach;
        this.maDM = maDM;
        this.maDVT = maDVT;
        this.tenSP = tenSP;
        this.soLuongTonKho = soLuongTonKho;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaVach() {
        return maVach;
    }

    public void setMaVach(String maVach) {
        this.maVach = maVach;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public int getMaDVT() {
        return maDVT;
    }

    public void setMaDVT(int maDVT) {
        this.maDVT = maDVT;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
