/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.DAO;

import com.duan1.Entity.DanhMuc;
import com.duan1.Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class DanhMucDAO extends QLPK<DanhMuc, String> {

    String insert_SQL = "INSERT INTO DanhMuc (TenDM) VALUES(?)";
    String update_SQL = "UPDATE DanhMuc SET TenDM = ? WHERE MaDM =?";
    String delete_SQL = "DELETE FROM DanhMuc WHERE MaDM = ?";
    String select_All_SQL = "SELECT *  FROM DanhMuc";
    String select_ByID_SQL = "SELECT *  FROM DanhMuc WHERE MaDM = ?";

    @Override
    public void insert(DanhMuc entity) {
        XJdbc.executeUpdate(insert_SQL,  entity.getTenDM());
    }

    @Override
    public void update(DanhMuc entity) {
        XJdbc.executeUpdate(update_SQL, entity.getTenDM(), entity.getMaDM());
    }

    @Override
    public void delete(String MaDM) {
        XJdbc.executeUpdate(delete_SQL, MaDM);
    }

    @Override
    public List<DanhMuc> selectAll() {
        return selectBySql(select_All_SQL);
    }

    @Override
    public DanhMuc selectByid(String MaDM) {
        List<DanhMuc> list = this.selectBySql(select_ByID_SQL, MaDM);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DanhMuc> selectBySql(String sql, Object... args) {
        List<DanhMuc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DanhMuc entity = new DanhMuc();
                entity.setMaDM(rs.getString("MaDM"));
                entity.setTenDM(rs.getString("TenDM"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public String getLastID() throws SQLException {
//        ResultSet rs = XJdbc.executeQuery(select_Max_Id);
//        String id = null;
//        if (rs.next()) {
//            id = rs.getString(1);
//        }
//        rs.getStatement().getConnection().close();
//        return id;
//    }
//
//    public String initID() throws SQLException {
//        String id = getLastID();
////        Date date = new Date();
////        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
//        int idNumber = Integer.parseInt(id);
//        String newID = String.format("%05d", idNumber += 1);
//        return newID;
//    }
}
