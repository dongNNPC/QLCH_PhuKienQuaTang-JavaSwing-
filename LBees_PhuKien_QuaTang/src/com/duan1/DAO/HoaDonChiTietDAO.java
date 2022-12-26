/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.DAO;

import com.duan1.Entity.HoaDonChiTiet;
import com.duan1.Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phung
 */
public class HoaDonChiTietDAO extends QLPK<HoaDonChiTiet, String> {

    String insert_SQL = "INSERT INTO HoaDonChiTiet ( MaHD,MaPNCT,  MaSP, SoLuongBan) VALUES    (?,?,?,?)";
    String update_SQL = "UPDATE HoaDonChiTiet SET MaSP = ?, SoLuongBan = ? WHERE MaHD = ?";
    String delete_SQL = "DELETE dbo.HoaDonChiTiet WHERE MaHD = ?";
    String select_All_SQL = "SELECT * FROM HoaDonChiTiet";
    String select_ByID_SQL = "SELECT   * FROM HoaDonChiTiet WHERE MaPNCT = ?";
    String select_tongTien = "SELECT  SUM(ThanhTien) FROM dbo.HoaDonChiTiet WHERE MaHD like ?";

    @Override
    public void insert(HoaDonChiTiet entity) {
        XJdbc.executeUpdate(insert_SQL, entity.getMaHD(),entity.getMaPNCT(), entity.getMaSP(),entity.getSoLuong());
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        XJdbc.executeUpdate(update_SQL, entity.getMaSP(),entity.getSoLuong(), entity.getMaHD());
    }

    @Override
    public void delete(String key) {
        XJdbc.executeUpdate(delete_SQL, key);
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        return selectBySql(select_All_SQL);
    }

    @Override
    public HoaDonChiTiet selectByid(String key) {
        List<HoaDonChiTiet> list = this.selectBySql(select_ByID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public long getTongTien(String maHD) throws SQLException {
        ResultSet rs = XJdbc.executeQuery(select_tongTien,"%" +maHD +"%");
        if(rs.next()){
            return (long) rs.getDouble(1);
        }
        return 0;
    }

    public List<HoaDonChiTiet> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM dbo.HoaDonChiTiet WHERE MaHD LIKE ?";
        return this.selectBySql(sql, "%" + keyword + '%');
    }

    @Override
    protected List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                HoaDonChiTiet entity = new HoaDonChiTiet();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setMaPNCT(rs.getString("MaPNCT"));
                entity.setMaSP(rs.getString("MaSP"));
                entity.setSoLuong(rs.getInt("SoLuongBan"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
