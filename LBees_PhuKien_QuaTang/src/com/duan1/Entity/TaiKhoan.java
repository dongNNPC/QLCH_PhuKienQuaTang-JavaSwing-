/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.Entity;

/**
 *
 * @author ASUS
 */
public class TaiKhoan {

    String MaTK;
    String tenTK;
    String matKhau;
    boolean chucVu;
    String trangThai;

    public TaiKhoan() {
    }

    public TaiKhoan(String MaTK, String tenTK, String matKhau, boolean chucVu, String trangThai) {
        this.MaTK = MaTK;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
