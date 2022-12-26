/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.DAO;

import com.duan1.Helper.XJdbc;
import java.util.List;
import com.duan1.Entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class NhanVienDAO extends QLPK<NhanVien, String> {

    String insert_SQL = "INSERT INTO NhanVien (MaNV, MaTK, TenNV, GioiTinh, CCCD, SDT, Email, DiaChi, HinhAnh, GhiChu, TrangThai) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    String update_SQL = "UPDATE NhanVien SET TenNV = ?, GioiTinh = ? , CCCD = ? , SDT = ? , Email = ? , DiaChi = ? , HinhAnh = ? , GhiChu = ? , TrangThai = ? WHERE MaNV = ?";
    String delete_SQL = "DELETE dbo.NhanVien WHERE MaNV =?";
    String select_All_SQL = "SELECT * FROM dbo.NhanVien";
    String select_ByID_SQL = "SELECT * FROM dbo.NhanVien WHERE MaNV=?";
    String update_SQL_HinhAnh = "UPDATE dbo.NhanVien SET HinhAnh=? WHERE MaNV =?";
    String select_Max_ID = "SELECT MAX(MaNV) FROM dbo.NhanVien";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.executeUpdate(insert_SQL, entity.getMaNV(), entity.getMaTK(), entity.getTenNV(),entity.isGioiTinh(),  entity.getCCCD(), 
                entity.getSDT(), entity.getEmail(), entity.getDiaChi(), entity.getHinhAnh(),entity.getGhiChu(),entity.getTrangThai() );
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.executeUpdate(update_SQL, entity.getTenNV(), entity.isGioiTinh(), entity.getCCCD(), 
                entity.getSDT(), entity.getEmail(), entity.getDiaChi(), entity.getHinhAnh(), entity.getGhiChu(),entity.getTrangThai(), entity.getMaNV());
    }
    public void updateHA(String DuongDan , String maNV){
        XJdbc.executeUpdate(update_SQL_HinhAnh,DuongDan,maNV);
    }

    @Override
    public void delete(String key) {
        XJdbc.executeUpdate(delete_SQL, key);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(select_All_SQL);
    }

    @Override
    public NhanVien selectByid(String key) {
        List<NhanVien> list = this.selectBySql(select_ByID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
     public String select_Last_ID() throws SQLException {
        ResultSet rs = XJdbc.executeQuery(select_Max_ID);
        return rs.getString("MaNV");
    }

    public String getLastID() throws SQLException {
        ResultSet rs = XJdbc.executeQuery(select_Max_ID);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
    
    public String initID() throws SQLException {
        String id = getLastID();
        int idNumber = Integer.parseInt(id.replaceAll("\\D+",""));
        String newID = String.format("%05d", idNumber += 1);
        return newID;
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMaTK(rs.getString("MaTK"));
                entity.setTenNV(rs.getString("TenNV"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setCCCD(rs.getString("CCCD"));
                entity.setSDT(rs.getString("SDT"));
                entity.setEmail(rs.getString("Gmail"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setHinhAnh(rs.getString("HinhAnh"));
                entity.setTrangThai(rs.getString("TrangThai"));
                entity.setDiaChi(rs.getString("DiaChi"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
