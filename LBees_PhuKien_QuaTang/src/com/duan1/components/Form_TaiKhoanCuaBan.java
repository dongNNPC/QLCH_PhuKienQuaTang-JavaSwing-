/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.duan1.components;

import com.duan1.DAO.NhanVienDAO;
import com.duan1.DAO.TaiKhoanDAO;
import com.duan1.Entity.HoaDon;
import com.duan1.Entity.HoaDonChiTiet;
import com.duan1.Entity.KhachHang;
import com.duan1.Entity.NhanVien;
import com.duan1.Entity.SanPham;
import com.duan1.Entity.TaiKhoan;
import com.duan1.Helper.Auth;
import com.duan1.Helper.ImgHelper;
import com.duan1.Helper.Msgbox;
import com.duan1.ui.MainJFrame;
import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Form_TaiKhoanCuaBan extends javax.swing.JDialog {

    MainJFrame frame = new MainJFrame();
    NhanVienDAO daoNV = new NhanVienDAO();
    TaiKhoanDAO daoTK = new TaiKhoanDAO();

    List<NhanVien> listNV = daoNV.selectAll();
    List<TaiKhoan> listTK = daoTK.selectAll();

    public Form_TaiKhoanCuaBan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setForm();
        this.setBackground(new Color(0, 0, 0, 0));

    }

    public void setForm() {
        txtHinh.setVisible(false);
        NhanVien nv = daoNV.selectByid(Auth.tk.getMaNV());
        lblMaNV.setText(nv.getMaNV());
        lblTenNV.setText(nv.getTenNV());
        if (nv.isGioiTinh()) {
            lblGioiTinh.setText("Nam");
        } else {
            lblGioiTinh.setText("Nữ");
        }
        lblcancuoc.setText(nv.getCCCD());
        lblSDT.setText(nv.getSDT());
        lblgmail.setText(nv.getGmail());
        lbltkkk.setText(Auth.tk.getTenTK());
//        lblmatkhau.setText(Auth.tk.getMatKhau());
        lblmatkhau.setText("*****");
        new ImgHelper().loadHinhVaoForm(nv.getHinhAnh(), lblHinh);
    }

    public void updateHinh() {
        ImgHelper s = new ImgHelper();
        s.ChonHinh(frame, lblHinh, txtHinh);
        boolean check = Msgbox.yesNo("Hệ thống", "Bạn có muốn cập nhật lại hình ảnh của bản thân không ?");
        if (check) {
            daoNV.updateHA(txtHinh.getText(), lblMaNV.getText());
        }else{
            NhanVien nv = new NhanVien();
            nv = daoNV.selectByid(lblMaNV.getText());
            s.loadHinhVaoForm(nv.getHinhAnh(), lblHinh);
        }
    }
//    public void display(int index) throws ParseException {
//        TaiKhoan tk = listTK.get(index);
//        setForm(tk);
//
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtHinh = new javax.swing.JTextField();
        panelTrang1 = new com.duan1.components.panelTrang();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblgmail = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblcancuoc = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbltkkk = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblmatkhau = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        button3 = new com.duan1.swing.Button();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("THÔNG TIN TÀI KHOẢN");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan1/icon/info.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel9.setText("Mã nhân viên:");

        lblMaNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMaNV.setText("th");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel11.setText("Gmail:");

        lblgmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblgmail.setText("th");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel13.setText("Tên nhân viên:");

        lblTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenNV.setText("th");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel17.setText("Giới tính:");

        lblcancuoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblcancuoc.setText("th");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel18.setText("CCCD:");

        lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGioiTinh.setText("th");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel19.setText("Số điện thoại:");

        lblSDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSDT.setText("th");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel20.setText("Tên tài khoản:");

        lbltkkk.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbltkkk.setText("th");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel21.setText("Mật khẩu:");

        lblmatkhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblmatkhau.setText("th");

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan1/icon/Avatar.png"))); // NOI18N
        lblHinh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        button3.setBackground(new java.awt.Color(51, 153, 255));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Đổi mật khẩu");
        button3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan1/icon/close1.png"))); // NOI18N
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTrang1Layout = new javax.swing.GroupLayout(panelTrang1);
        panelTrang1.setLayout(panelTrang1Layout);
        panelTrang1Layout.setHorizontalGroup(
            panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrang1Layout.createSequentialGroup()
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTrang1Layout.createSequentialGroup()
                                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel19))
                                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(lbltkkk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(panelTrang1Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblcancuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                            .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelTrang1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTrang1Layout.createSequentialGroup()
                                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel17))
                                        .addGap(37, 37, 37)
                                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblmatkhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblgmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelTrang1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addGap(12, 12, 12))))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrang1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        panelTrang1Layout.setVerticalGroup(
            panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrang1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel22))
                .addGap(11, 11, 11)
                .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblTenNV)
                    .addComponent(jLabel9)
                    .addComponent(lblMaNV))
                .addGap(0, 0, 0)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblcancuoc)
                    .addComponent(jLabel17)
                    .addComponent(lblGioiTinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblgmail)
                    .addComponent(lblSDT)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblmatkhau)
                    .addComponent(jLabel20)
                    .addComponent(lbltkkk))
                .addGap(18, 18, 18)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(panelTrang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(panelTrang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        updateHinh();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed

        DoiMatKhauJFrame d = new DoiMatKhauJFrame();
        dispose();
        d.setVisible(true);
    }//GEN-LAST:event_button3ActionPerformed

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel22MouseClicked

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
            java.util.logging.Logger.getLogger(Form_TaiKhoanCuaBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_TaiKhoanCuaBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_TaiKhoanCuaBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_TaiKhoanCuaBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Form_TaiKhoanCuaBan dialog = new Form_TaiKhoanCuaBan(new javax.swing.JFrame(), true);
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
    private com.duan1.swing.Button button3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblcancuoc;
    private javax.swing.JLabel lblgmail;
    private javax.swing.JLabel lblmatkhau;
    private javax.swing.JLabel lbltkkk;
    private com.duan1.components.panelTrang panelTrang1;
    private javax.swing.JTextField txtHinh;
    // End of variables declaration//GEN-END:variables
}
