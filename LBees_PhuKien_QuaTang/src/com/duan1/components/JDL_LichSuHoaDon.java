
package com.duan1.components;

import com.duan1.DAO.HoaDonChiTietDAO;
import com.duan1.DAO.HoaDonDAO;
import com.duan1.DAO.KhachHangDAO;
import com.duan1.DAO.NhanVienDAO;
import com.duan1.DAO.SanPhamDAO;
import com.duan1.Entity.HoaDon;
import com.duan1.Entity.HoaDonChiTiet;
import com.duan1.Entity.KhachHang;
import com.duan1.Entity.NhanVien;
import com.duan1.Entity.SanPham;
import com.duan1.Helper.XDate;
import static com.duan1.Helper.XDate.now;
import com.duan1.Helper.XJdbc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javaswingdev.FontAwesome;
import microsoft.sql.Types;

/**
 *
 * @author asus
 */
public class JDL_LichSuHoaDon extends javax.swing.JDialog {

    HoaDonDAO daoHD = new HoaDonDAO();
    SanPhamDAO daoSP = new SanPhamDAO();
    NhanVienDAO daoNV = new NhanVienDAO();
    HoaDonChiTietDAO daoCT = new HoaDonChiTietDAO();
    KhachHangDAO daoKH = new KhachHangDAO();

    List<HoaDon> listHD = daoHD.selectAll();
    List<SanPham> listSP = daoSP.selectAll();
    List<NhanVien> listNV = daoNV.selectAll();
    List<HoaDonChiTiet> listCT = daoCT.selectAll();
    Form_LSHoaDon lshd = new Form_LSHoaDon();
    int index;

    public JDL_LichSuHoaDon(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

    }

    public void setForm(HoaDon hd) throws ParseException {

        NhanVien nv = daoNV.selectByid(hd.getMaNV());
        HoaDonChiTiet ct = daoCT.selectByid(hd.getMaHD());
        SanPham sp = daoSP.selectByid(ct.getMaSP());
        KhachHang kh = daoKH.selectByid(hd.getMaKH());

        lblHoaDon.setText(hd.getMaHD());
        lblMaSP.setText(sp.getMaSP());
        lblTenSP.setText(sp.getTenSP());
        lblSoLuong.setText(String.valueOf(ct.getSoLuong()));
        lblDonGia.setText("" + sp.getDonGia());
        lblThanhTien.setText(String.valueOf(ct.getThanhTien()));
        lblNhanVien.setText(nv.getTenNV());
        lblkhachHang.setText(kh.getTenKH());
//        lblNgay.setText(String.format(todayS, hd.getNgayTao()));
        Date ngay = hd.getNgayTao();
        lblNgay.setText(hd.getNgayTao() + "");

    }

    public void display(int index) throws ParseException {
        HoaDon hd = listHD.get(index);
        setForm(hd);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblDonGia = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblkhachHang = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel10 = new javax.swing.JLabel();
        lblThanhTien1 = new javax.swing.JLabel();
        lblThanhTien2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thông Tin Chi Tiết");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Mã hóa đơn:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel3.setText("Mã Sản Phẩm: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel4.setText("Tên Sản Phẩm: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel5.setText("Số lượng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel6.setText("Đơn giá:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel7.setText("Thành tiền:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel8.setText("Nhân viên:");

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(102, 102, 102));

        lblSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoLuong.setText("SoLuong");

        lblDonGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDonGia.setText("DONGia");

        lblTenSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTenSP.setText("121");

        lblMaSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMaSP.setText("Masp");

        lblThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThanhTien.setText("thanhtien");

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNhanVien.setText("tên");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel9.setText("Khách hàng:");

        lblkhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblkhachHang.setText("kh");

        lblNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNgay.setText("Ngay");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel11.setText("Ngày tạo:");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan1/icon/receipt_40px.png"))); // NOI18N

        lblThanhTien1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThanhTien1.setText("VND");

        lblThanhTien2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThanhTien2.setText("VND");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblkhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblThanhTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(186, 186, 186))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(28, 28, 28)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(220, 220, 220)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHoaDon))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblHoaDon)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblTenSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblSoLuong))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThanhTien2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblThanhTien)
                    .addComponent(lblThanhTien1))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblkhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDL_LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDL_LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDL_LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDL_LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDL_LichSuHoaDon dialog = new JDL_LichSuHoaDon(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThanhTien1;
    private javax.swing.JLabel lblThanhTien2;
    private javax.swing.JLabel lblkhachHang;
    // End of variables declaration//GEN-END:variables
}
