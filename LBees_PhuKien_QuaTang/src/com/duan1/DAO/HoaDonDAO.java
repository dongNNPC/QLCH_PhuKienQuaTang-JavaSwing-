/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.DAO;

import com.duan1.Entity.HoaDon;
import com.duan1.Entity.HoaDonChiTiet;
import com.duan1.Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public class HoaDonDAO extends QLPK<HoaDon, String> {

    String insert_SQL = "INSERT INTO HoaDon (MaHD, MaKH, MaNV, NgayTao, TienShip, GhiChu) VALUES    (?,?,?,?,?,?)";
    String update_SQL = "UPDATE HoaDon SET MaKH = ? , MaNV = ? , NgayTao = ? , TienShip = ? , GhiChu = ? WHERE MaHD = ?";
    String delete_SQL = "DELETE HoaDon WHERE MaHD = ?";
    String select_All_SQL = "SELECT * FROM HoaDon";
    String select_ByID_SQL = "SELECT * FROM HoaDon WHERE MaHD=?";
    String select_Max_ID = "SELECT MAX(SUBSTRING(MaHD,LEN(MaHD) - 3,LEN(MaHD)))FROM HOADON";
    String select_ByID_SQL_TK = "SELECT * FROM dbo.HoaDon WHERE NgayTao=?";

    @Override
    public void insert(HoaDon entity) {
        XJdbc.executeUpdate(insert_SQL, entity.getMaHD(), entity.getMaKH() ,entity.getMaNV(),entity.getNgayTao(), entity.getTienShip(), entity.getGhiChu());
    }

    @Override
    public void update(HoaDon entity) {
        XJdbc.executeUpdate(update_SQL, entity.getMaKH() ,entity.getMaNV(),entity.getNgayTao(), entity.getTienShip(),entity.getGhiChu(),entity.getMaHD());
    }

    @Override
    public void delete(String MaHD) {
        XJdbc.executeUpdate(delete_SQL, MaHD);
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySql(select_All_SQL);
    }

    @Override
    public HoaDon selectByid(String MaHD) {
        List<HoaDon> list = this.selectBySql(select_ByID_SQL, MaHD);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

//    public String select_Last_ID() throws SQLException {
//        ResultSet rs = XJdbc.executeQuery(select_Max_ID);
//        return rs.getString("MaHD");
//    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                entity.setTienShip(rs.getFloat("TienShip"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMaKH(rs.getString("MaKH"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<HoaDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HoaDon WHERE MaHD LIKE ? ";
        return this.selectBySql(sql, "%" + keyword + '%');
    }

//    public List<HoaDon> selectByDate(Date tuNgay, Date denNgay) {
//        String sql = "SELECT * FROM dbo.HoaDon WHERE NgayTao BETWEEN ? AND ?";
//        return this.selectBySql(sql, tuNgay, denNgay);
//    }

   
    public String getLastID() throws SQLException {
            ResultSet rs = XJdbc.executeQuery(select_Max_ID);
            String id = null;
            if (rs.next()) {
                id = rs.getString(1);
            }
            rs.getStatement().getConnection().close();
            return id;
    }
    public String initID () throws SQLException {
        String id = getLastID();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        int idNumber = Integer.parseInt(id);
        String newID = String.format(sdf.format(date)+"%04d", idNumber+=1);
        return newID;
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
    public List<Object[]> findHDbyDate(Date tuNgay, Date denNgay) {
        String sql = "{CALL sp_TimHD_TheoNgay(?,?)}";
        String[] cols = {"MaHD","MaNV","MaKH", "TenKH","NgayTao","TongTien"};
        return this.getListOfArray(sql, cols,tuNgay,denNgay);
    }
//    public List<Object[]> getSoLuong_TongTien() {
//        String sql = "{CALL sp_dsPhieuNhapKho}";
//        String[] cols = {"MaPNK","NgayNhap","SoLuong", "ThanhTien"};
//        return this.getListOfArray(sql, cols);
//    }
}
