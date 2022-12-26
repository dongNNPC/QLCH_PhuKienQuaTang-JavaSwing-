/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.DAO;

import com.duan1.Entity.PhieuNhapChiTiet;
import com.duan1.Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public class PhieuNhapChiTietDAO extends QLPK<PhieuNhapChiTiet, String> {

    String insert_SQL = "INSERT INTO PhieuNhapChiTiet (MaPNCT, MaPN, MaSP, SoLuongNhap, GiaNhap, GiaBan) VALUES (?,?,?,?,?,?)";
    String update_SQL = "UPDATE PhieuNhapChiTiet SET MaPN = ?, MaSP = ?, SoLuongNhap = ?, GiaNhap = ?, GiaBan = ? WHERE MaPNCT = ?";
    String delete_SQL = "DELETE dbo.PhieuNhapChiTiet WHERE MaPNK =?";
    String select_All_SQL = "SELECT * FROM dbo.PhieuNhapChiTiet";
    String select_ByID_SQL = "SELECT * FROM dbo.PhieuNhapChiTiet WHERE MaPNK=?";

    @Override
    public void insert(PhieuNhapChiTiet entity) {
        XJdbc.executeUpdate(insert_SQL, entity.getMaPNCT(), entity.getMaPN(),
                entity.getMaSP(), entity.getSoLuongNhap(),
                entity.getGiaNhap(),entity.getGiaBan());
    }

    @Override
    public void update(PhieuNhapChiTiet entity) {
        XJdbc.executeUpdate(update_SQL, entity.getMaPN(),
                entity.getMaSP(), entity.getSoLuongNhap(),
                entity.getGiaNhap(),entity.getGiaBan(),entity.getMaPNCT());
    }

    @Override
    public void delete(String key) {
        XJdbc.executeUpdate(delete_SQL, key);
    }

    @Override
    public List<PhieuNhapChiTiet> selectAll() {
        return selectBySql(select_All_SQL);
    }

    @Override
    public PhieuNhapChiTiet selectByid(String key) {
        List<PhieuNhapChiTiet> list = this.selectBySql(select_ByID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<PhieuNhapChiTiet> selectBySql(String sql, Object... args) {
        List<PhieuNhapChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                PhieuNhapChiTiet entity = new PhieuNhapChiTiet();
                entity.setMaPNK(rs.getString("MaPNK"));
                entity.setMaSP(rs.getString("MaSP"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setThanhTien(rs.getFloat("ThanhTien"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getSoLuong_TongTien() {
        String sql = "{CALL sp_dsPhieuNhapKho}";
        String[] cols = {"MaPNK","NgayNhap","SoLuong", "ThanhTien"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<PhieuNhapChiTiet> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM dbo.PhieuNhapChiTiet WHERE MaPNK LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

}
    