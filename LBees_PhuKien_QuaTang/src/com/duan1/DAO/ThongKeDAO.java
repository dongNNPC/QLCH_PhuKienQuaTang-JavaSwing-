package com.duan1.DAO;

import com.duan1.Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeDAO {

    //////////////////////////////Thống kê Tổng số lượng hoá đơn
    protected Integer tongSLHoaDon(String sql) throws SQLException {

        int sl = 0;
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                sl = rs.getInt("tong");
                System.out.println(rs.getInt(0));
            }
            rs.getStatement().getConnection().close();

        } catch (Exception e) {
        }
        return sl;
    }

    public int tongSLHoaDD() throws SQLException {
        String tong = "SELECT  COUNT(MaHD) as'tong'  FROM dbo.HoaDon";
        return tongSLHoaDon(tong);
    }

    ///////////////////////Thống kê tổng nhập khho
    protected Integer tongSLNP(String sql) throws SQLException {

        int sl = 0;
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                sl = rs.getInt("tong");
                System.out.println(rs.getInt(0));
            }
            rs.getStatement().getConnection().close();

        } catch (Exception e) {
        }
        return sl;
    }

    public int tongSLNhapKho() throws SQLException {
        String tong = "SELECT COUNT(MaPNK) AS'Tong' FROM dbo.PhieuNhapKho";
        return tongSLNP(tong);
    }

    ///thống kê tổng xuất kho
    public int tongSLXuatK() throws SQLException {
        String tong = "SELECT COUNT(MaHD) AS'Tong' FROM dbo.HoaDon";
        return tongSLNP(tong);
    }

    public int tongDoanhThu() throws SQLException {
        String tong = " SELECT  SUM(ThanhTien) -SUM(TienShip) AS'tong'  FROM dbo.HoaDon INNER JOIN dbo.HoaDonChiTiet ON HoaDonChiTiet.MaHD = HoaDon.MaHD";
        return tongSLNP(tong);
    }

    public int tongToanKho() throws SQLException {
        String tong = "SELECT SUM(SoLuong) AS'tong' FROM dbo.SanPhamBan";
        return tongSLNP(tong);
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

    public List<Object[]> getDoanhThu(Date tuNgay, Date denNgay) {
        String sql = "{CALL sp_ThongKeDoanhThu (?,?)}";
        String[] cols = {"MaHD", "NgayTao", "TongTien"};
        return this.getListOfArray(sql, cols, tuNgay, denNgay);
    }
    public List<Object[]> getThongKeSanPham() {
        String sql = "{CALL sp_ThongKeSanPham}";
        String[] cols = {"MaSP", "TenSP", "SoLuong","TongTien"};
        return this.getListOfArray(sql, cols);
    }
    
      public List<Object[]> findHDbyDateTke(Date tuNgay, Date denNgay) {
        String sql = "{CALL sp_TimHD_TheoNgayTke(?,?)}";
        String[] cols = {"MaHD","NgayTao","TongTien"};
        return this.getListOfArray(sql, cols,tuNgay,denNgay);
    }
}
