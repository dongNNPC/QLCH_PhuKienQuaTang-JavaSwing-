/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.DAO;

import com.duan1.Entity.NhanVien;
import com.duan1.Entity.TaiKhoan;
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
public class TaiKhoanDAO extends QLPK<TaiKhoan, String> {

    String insert_SQL = "INSERT INTO dbo.TaiKhoan(MaTK,TenTK,MatKhau,MaNV)VALUES(?,?,?,?)";
    String update_SQL = "UPDATE dbo.TaiKhoan SET TenTK=?,MatKhau=?  WHERE MaTK =?";
    String delete_SQL = "DELETE dbo.TaiKhoan WHERE MaTK =?";
    String select_All_SQL = "SELECT * FROM dbo.TaiKhoan";
    String select_ByID_SQL = "SELECT * FROM dbo.TaiKhoan WHERE MaTK=?";
    String Select_CV = "select  tenNV,chucvu from TaiKhoan t inner join nhanvien n on t.MaNV = n.maNV where t.manv =?";
    String select_Max_Id = "SELECT MAX(SUBSTRING(MaTK,LEN(MaTK) -2 ,LEN(MaTK))) FROM dbo.TaiKhoan";

    @Override
    public void insert(TaiKhoan entity) {
        XJdbc.executeUpdate(insert_SQL, entity.getMaTK(), entity.getTenTK(), entity.getMatKhau());
    }

    @Override
    public void update(TaiKhoan entity) {
        XJdbc.executeUpdate(update_SQL, entity.getTenTK(), entity.getMatKhau(), entity.getMaTK());
    }

    @Override
    public void delete(String key) {
        XJdbc.executeUpdate(delete_SQL, key);
    }

    @Override
    public List<TaiKhoan> selectAll() {
        return selectBySql(select_All_SQL);
    }

    @Override
    public TaiKhoan selectByid(String key) {
        List<TaiKhoan> list = this.selectBySql(select_ByID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public boolean selectByCV(String key) {
        List<NhanVien> list = this.selectBySqll(Select_CV, key);
        boolean s = false;
        for (NhanVien t : list) {
            s = t.isChucVu();
        }
        return s;
    }

    public String selectByTen(String key) {
        List<NhanVien> list = this.selectBySqlTen(Select_CV, key);
        String s ="";
        for (NhanVien t : list) {
            s = t.getTenNV();
        }
        return s;
    }

    @Override
    protected List<TaiKhoan> selectBySql(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                TaiKhoan entity = new TaiKhoan();
                entity.setMaTK(rs.getString("MaTK"));
                entity.setTenTK(rs.getString("TenTK"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setMaNV(rs.getString("MaNV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected List<NhanVien> selectBySqll(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setChucVu(rs.getBoolean("ChucVu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected List<NhanVien> selectBySqlTen(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setTenNV(rs.getString("tenNV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
      public String getLastID() throws SQLException {
            ResultSet rs = XJdbc.executeQuery(select_Max_Id);
            String id = null;
            if (rs.next()) {
                id = rs.getString(1);
            }
            rs.getStatement().getConnection().close();
            return id;
    }
      public String initID () throws SQLException {
        String id = getLastID();
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        int idNumber = Integer.parseInt(id);
        String newID = String.format("%05d", idNumber += 1);
        return newID;
    }
}
