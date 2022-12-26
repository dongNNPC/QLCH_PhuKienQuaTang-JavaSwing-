/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.Entity;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class HoaDon {

    String maHD;
    float tienShip;
    Date ngayTao;
    String ghiChu;
    String maNV;
    String maKH;

    public HoaDon() {
    }

    public HoaDon(String maHD, float tienShip, Date ngayTao, String ghiChu, String maNV, String maKH) {
        this.maHD = maHD;
        this.tienShip = tienShip;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
        this.maKH = maKH;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public float getTienShip() {
        return tienShip;
    }

    public void setTienShip(float tienShip) {
        this.tienShip = tienShip;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNgayTao(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
