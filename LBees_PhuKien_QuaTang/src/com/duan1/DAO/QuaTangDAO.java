
package com.duan1.DAO;


import com.duan1.Entity.QuaTang;
import com.duan1.Helper.XJdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class QuaTangDAO extends QLPK<QuaTang, String> {

    String insert_SQL = "INSERT INTO dbo.QuaTang(MaDH,TenNN,DiaChiNN,SDTNN,NgayGiao,TrangThai,GhiChu,MaNGH,MaHD)VALUES(?,?,?,?,?,?,?,?,?)";
    String update_SQL = "UPDATE dbo.QuaTang SET TenNN=?,DiaChiNN=?,SDTNN=?,NgayGiao=?,TrangThai=?,GhiChu=?,MaNGH=?,MaHD=? WHERE MaDH =?";
    String delete_SQL = "DELETE dbo.QuaTang WHERE MaDH =?";
    String select_All_SQL = "SELECT * FROM dbo.QuaTang";
    String select_ByID_SQL = "SELECT * FROM dbo.QuaTang WHERE MaDH=?";
    String select_Max_Id = "SELECT Max(SUBSTRING(MaDH,LEN(MaDH) - 3,LEN(MaDH)))FROM QuaTang";
    @Override
    public void insert(QuaTang entity) {
        XJdbc.executeUpdate(insert_SQL, entity.getMaDH(), entity.getTenNN(), entity.getDiaChiNN(), entity.getSDTNN(), entity.getNgayGiao(), entity.getTrangThai(), entity.getGhiChu(), entity.getMaNGH(), entity.getMaHD());
    }

    @Override
    public void update(QuaTang entity) {
        XJdbc.executeUpdate(update_SQL, entity.getTenNN(), entity.getDiaChiNN(), entity.getSDTNN(), entity.getNgayGiao(), entity.getTrangThai(), entity.getGhiChu(), entity.getMaNGH(), entity.getMaHD(), entity.getMaDH());
    }

    @Override

    public void delete(String key) {
        XJdbc.executeUpdate(delete_SQL, key);
    }

    @Override
    public List<QuaTang> selectAll() {
        return selectBySql(select_All_SQL);
    }

    @Override
    public QuaTang selectByid(String key) {
        List<QuaTang> list = this.selectBySql(select_ByID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<QuaTang> selectBySql(String sql, Object... args) {
        List<QuaTang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                QuaTang entity = new QuaTang();
                entity.setMaDH(rs.getString("MaDH"));
                entity.setTenNN(rs.getString("TenNN"));
                entity.setDiaChiNN(rs.getString("DiaChiNN"));
                entity.setSDTNN(rs.getString("SDTNN"));
                entity.setNgayGiao(rs.getDate("NgayGiao"));
                entity.setTrangThai(rs.getString("TrangThai"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNGH(rs.getString("MaNGH"));
                entity.setMaHD(rs.getString("MaHD"));
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
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        int idNumber = Integer.parseInt(id);
        String newID = String.format(sdf.format(date) + "%04d", idNumber += 1);
        return newID;
    }
}