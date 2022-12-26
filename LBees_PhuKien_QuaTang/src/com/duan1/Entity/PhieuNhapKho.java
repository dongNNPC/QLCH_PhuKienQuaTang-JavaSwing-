/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.Entity;

import java.util.Date;

/**
 *
 * @author asus
 */
public class PhieuNhapKho {

    String maPN;
    String maNV;
    Date ngayNhap;
    String ghiChu;

    public PhieuNhapKho() {
    }

    public PhieuNhapKho(String maPN, String maNV, Date ngayNhap, String ghiChu) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
        this.ghiChu = ghiChu;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
