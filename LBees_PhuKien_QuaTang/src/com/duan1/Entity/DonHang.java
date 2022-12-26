package com.duan1.Entity;

import java.util.Date;

public class DonHang {

    String maDH;
    String maKH;
    String maHD;
    String tenNN;
    String sdtNN;
    Date ngayGiao;
    String ghiChu;

    public DonHang(String maDH, String maKH, String maHD, String tenNN, String sdtNN, Date ngayGiao, String ghiChu) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.maHD = maHD;
        this.tenNN = tenNN;
        this.sdtNN = sdtNN;
        this.ngayGiao = ngayGiao;
        this.ghiChu = ghiChu;
    }

    public DonHang() {
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenNN() {
        return tenNN;
    }

    public void setTenNN(String tenNN) {
        this.tenNN = tenNN;
    }

    public String getSdtNN() {
        return sdtNN;
    }

    public void setSdtNN(String sdtNN) {
        this.sdtNN = sdtNN;
    }

    public Date getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Date ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    

}
