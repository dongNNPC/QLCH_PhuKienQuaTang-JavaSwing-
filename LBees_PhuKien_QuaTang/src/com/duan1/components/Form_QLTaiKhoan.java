package com.duan1.components;

import com.duan1.DAO.TaiKhoanDAO;
import com.duan1.Entity.KhachHang;
import com.duan1.Entity.TaiKhoan;
import com.duan1.Helper.Msgbox;
import com.duan1.Helper.XDate;
import com.duan1.swing.EventCallBack;
import com.duan1.swing.EventTextField;
import com.duan1.swing.MessageDialog;
import com.duan1.swing.Notification;
import com.duan1.swing.ScrollBarCustom;
import com.duan1.ui.MainJFrame;
import java.awt.Cursor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ASUS
 */
public class Form_QLTaiKhoan extends javax.swing.JPanel {

//    TaiKhoanDAO daoTK = new TaiKhoanDAO();
//    MainJFrame frame = new MainJFrame();
//    List<TaiKhoan> listTK = daoTK.selectAll();
//    MessageDialog mdl = new MessageDialog(frame);
//    DefaultTableModel model ;
//    public int index;
//    String maTK = "";
//    public Form_QLTaiKhoan() {
//        initComponents();
//        setHin();
//        TimKiem();
//        ThanhTruotTK();
//        loadDataQLTK();
//        lblIconAn.setVisible(false);
//        init();
//    }
//    //gán tên lên textfile
//    public void setHin() {
//        txtMaTaiKhoan.setHint("Nhập mã tài khoản");
//        txtMatKhau.setHint("Nhập mật khẩu");
//        txtTaiKhoan.setHint("Nhập tên tài khoản");
//        //đổi con chuột
//        
//        txtTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        Tabpane.setCursor(new Cursor(Cursor.HAND_CURSOR));
//       
//    }
//    public void init (){
//          try {
//            maTK = "TK" + daoTK.initID();
//            txtMaTaiKhoan.setText(maTK);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    //tạo animition cho thanh tìm kiếm
//    public void TimKiem() {
//        txtTimKiem.setHintText("Nhập mã hoặc tên tài khoản");
//        txtTimKiem.addEvent(new EventTextField() {
//            @Override
//            public void onPressed(EventCallBack call) {
//                //  Test
//                try {
//                    for (int i = 1; i <= 100; i++) {
//
//                        Thread.sleep(5);
//                    }
//                    call.done();
//                } catch (Exception e) {
//                    System.err.println(e);
//                }
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//        });
//    }
//
//    //tạo thanh trượt trong table
//    public void ThanhTruotTK() {
//        ScrollTK.setVerticalScrollBar(new ScrollBarCustom());
//    }
//    
//    //load dữ liệu lên bảng
//    public void loadDataQLTK() {
//        DefaultTableModel model = (DefaultTableModel) tblQLTK.getModel();
//        model.setRowCount(0);
//        listTK = daoTK.selectAll();
//        try {
//            for (TaiKhoan tk : listTK) {
//
//                Object[] row = {
//                    tk.getMaTK(),
//                    tk.getTenTK(),
//                    tk.getMatKhau()
//                };
//                model.addRow(row);
//            }
//
//        } catch (Exception e) {
//            Notification noti = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.CENTER, "Lỗi thêm dữ liệu vào tài khoản");
//            noti.showNotification();
//        }
//    }
//    
//    //lấy nhập  trên textfiled
//    public void setForm(TaiKhoan tk) {
//        TaiKhoan hd = daoTK.selectByid(tk.getMaTK());
//        txtMaTaiKhoan.setText(tk.getMaTK());
//        txtTaiKhoan.setText(tk.getTenTK());
//        txtMatKhau.setText(tk.getMatKhau());
//
//    }
//    
//    //lấy giá trị của bảng
//    public void display(int index) {
//        TaiKhoan taiKhoan = listTK.get(index);
//        setForm(taiKhoan);
//    }
//    
//    //hỗ trợ thêm sửa xoá
//    public TaiKhoan getForm() {
//        TaiKhoan tk = new TaiKhoan();
//        tk.setMaTK(txtMaTaiKhoan.getText());
//        tk.setTenTK(txtTaiKhoan.getText());
//        tk.setMatKhau(txtMatKhau.getText());
//        return tk;
//    }
//    
//    //xoá sạch bảng
//    public void clear() {
//        txtMaTaiKhoan.setText("");
//        txtTaiKhoan.setText("");
//        txtMatKhau.setText("");
//    }
//    
//    //kiểm trả lỗi
//    public boolean check() {
//
//        try {
//            if (txtMaTaiKhoan.getText().trim().isEmpty()) {
//                Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Không được để trống mã tài khoản");
//                noti.showNotification();
//                return false;
//            }
//            if (txtMaTaiKhoan.getText().length() > 11) {
//            Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Mã tài khoản phải dưới 10 kí tự");
//            noti.showNotification();
//            return false;
//        }
//        } catch (Exception e) {
//            Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Lỗi trùng mã");
//            noti.showNotification();
//        }
//        
//
//        if (txtTaiKhoan.getText().trim().isEmpty()) {
//            Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Không được để trống tên tài khoản");
//            noti.showNotification();
//            return false;
//        }
//        if (txtMatKhau.getText().trim().isEmpty()) {
//            Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Không được để trống mật khẩu");
//            noti.showNotification();
//            return false;
//        }
//        return true;
//    }
//
//    //tạo nút thêm
//    public void Them() {
//        TaiKhoan tk = getForm();
//
//        if (tk != null) {
//            try {
//                daoTK.insert(tk);
//                loadDataQLTK();
//                clear();
//                Notification noti = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Thêm tài khoản thành công");
//                noti.showNotification();
//            } catch (Exception e) {
//                Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Thêm tài khoản không thành công");
//                noti.showNotification();
//            }
//        }
//
//    }
//
//    //tạo nút cập nhật
//    public void capNhat() {
//     
//        TaiKhoan tk = getForm();
//         if (Msgbox.yesNo("bạn có muốn cập nhật tài khoản", "bạn chắc chắn không???")) {
//            daoTK.update(tk);
//            loadDataQLTK();
//            Msgbox.success(frame, "cập nhật tài khoản thành công");
//            Tabpane.setSelectedIndex(1);
//        } else {
//            Msgbox.waring(frame, "cập nhật tài khoản không thành công");
//        }
//   
//    }
//    
//    //tạo nút xoá
//    public void xoa() {
//        mdl.showMessage("XÓA TÀI KHOẢN", "Bạn có chắc chắn xóa tài khoản không");
//
//        if (mdl.getMessageType() == MessageDialog.MessageType.OK) {
//            String maKH = txtMaTaiKhoan.getText();
//            try {
//                daoTK.delete(maKH);
//                loadDataQLTK();
//                Notification noti = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Xóa tài khoản thành công");
//                noti.showNotification();
//            } catch (RuntimeException e) {
//                JOptionPane.showMessageDialog(this, "Loi xoa ");
//            }
//        }
//
//    }
//
//    //cập nhật tình trạng của 4 nút
//    public void updateStatus() {
//        if (index == 0) {
//            btnDauTien.setEnabled(false);
//            btnLui.setEnabled(false);
//        } else {
//            btnDauTien.setEnabled(true);
//            btnLui.setEnabled(true);
//        }
//        if (index == listTK.size() - 1) {
//            btnToi.setEnabled(false);
//            btnCuoi.setEnabled(false);
//        } else {
//            btnToi.setEnabled(true);
//            btnCuoi.setEnabled(true);
//        }
//    }
//
//    //lấy giá trị
//    public void edit() {
//        tblQLTK.setRowSelectionInterval(index, index);
//        display(index);
//        updateStatus();
//    }
//
//    //tạo nút quay lại hàng đầu tiên
//    public void firs() {
//        index = 0;
//         Msgbox.info(new MainJFrame(), "Bạn đang ở đầu danh sách!");
//        edit();
//    }
//
//    //tạo nút tới hàng cuối cùng
//    public void last() {
//        index = listTK.size() - 1;
//        Msgbox.info(new MainJFrame(), "Bạn đang ở cuối danh sách!");
//        edit();
//    }
//
//    //tạo nút lùi lại 1 hàng
//    public void prev() {
//        if (index <= 0) {
//            last();
//        } else {
//            index--;
//        }
//        edit();
//
//    }
//
//    //tạo nút tới 1 hàng
//    public void next() {
//        if (index == listTK.size() - 1) {
//            firs();
//        } else {
//            index++;
//        }
//        edit();
//
//    }
//    
//    public void findIdAndName(String IdAndName) {
//        model = (DefaultTableModel) tblQLTK.getModel();
//        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
//        tblQLTK.setRowSorter(trs);
//         trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 0,1));
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTimKiem = new com.duan1.swing.TextFieldAnimation();
        Tabpane = new com.duan1.swing.MaterialTabbed();
        pnlCapNhat = new javax.swing.JPanel();
        txtMaTaiKhoan = new com.duan1.swing.MyTextField();
        txtTaiKhoan = new com.duan1.swing.MyTextField();
        pnlThemSuaXoa = new javax.swing.JPanel();
        btnMoi = new com.duan1.swing.Button();
        btnXoa = new com.duan1.swing.Button();
        btnSua = new com.duan1.swing.Button();
        btnThem = new com.duan1.swing.Button();
        btnDauTien = new com.duan1.swing.Button();
        btnLui = new com.duan1.swing.Button();
        btnToi = new com.duan1.swing.Button();
        btnCuoi = new com.duan1.swing.Button();
        lblIconHien = new javax.swing.JLabel();
        lblIconAn = new javax.swing.JLabel();
        txtMatKhau = new com.duan1.swing.MyPassField();
        pnlDanhSach = new javax.swing.JPanel();
        ScrollTK = new javax.swing.JScrollPane();
        tblQLTK = new com.duan1.swing.Table();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimKiemMousePressed(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        pnlCapNhat.setBackground(new java.awt.Color(255, 255, 255));

        txtMaTaiKhoan.setEditable(false);

        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });

        pnlThemSuaXoa.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemSuaXoa.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnDauTien.setText("<");
        btnDauTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDauTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauTienActionPerformed(evt);
            }
        });

        btnLui.setText("|<");
        btnLui.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        btnToi.setText(">|");
        btnToi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnToi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToiActionPerformed(evt);
            }
        });

        btnCuoi.setText(">");
        btnCuoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiActionPerformed(evt);
            }
        });

        lblIconHien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan1/icon/icons8_eye_26px.png"))); // NOI18N
        lblIconHien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconHienMouseClicked(evt);
            }
        });

        lblIconAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan1/icon/icons8_invisible_26px.png"))); // NOI18N
        lblIconAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconAnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconAn)
                            .addComponent(lblIconHien))
                        .addGap(40, 40, 40))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnDauTien, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnToi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
            .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCapNhatLayout.createSequentialGroup()
                    .addGap(359, 359, 359)
                    .addComponent(pnlThemSuaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(274, Short.MAX_VALUE)))
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addComponent(txtMaTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblIconAn)
                    .addComponent(lblIconHien))
                .addGap(98, 98, 98)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDauTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnToi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCapNhatLayout.createSequentialGroup()
                    .addGap(222, 222, 222)
                    .addComponent(pnlThemSuaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(160, Short.MAX_VALUE)))
        );

        Tabpane.addTab("Cập nhật", pnlCapNhat);

        ScrollTK.setBorder(null);

        tblQLTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã tài khoản", "Tài khoản", "Mật khẩu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLTKMouseClicked(evt);
            }
        });
        ScrollTK.setViewportView(tblQLTK);

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollTK, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollTK, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );

        Tabpane.addTab("Danh sách", pnlDanhSach);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ TÀI KHOẢN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Tabpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Tabpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
       // MainJFrame m = new MainJFrame();
//       new Form_ThemThanhCong(m, true).setVisible(true);

    }//GEN-LAST:event_btnThemActionPerformed

    private void tblQLTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLTKMouseClicked
    }//GEN-LAST:event_tblQLTKMouseClicked

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

    private void lblIconHienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconHienMouseClicked
        lblIconAn.setVisible(true);
        lblIconHien.setVisible(false);
        txtMatKhau.setEchoChar((char) 0);

    }//GEN-LAST:event_lblIconHienMouseClicked

    private void lblIconAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconAnMouseClicked
        lblIconHien.setVisible(true);
        lblIconAn.setVisible(false);
        txtMatKhau.setEchoChar('*');
    }//GEN-LAST:event_lblIconAnMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
            
       
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnDauTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauTienActionPerformed
    }//GEN-LAST:event_btnDauTienActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
       // 
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnToiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToiActionPerformed
    }//GEN-LAST:event_btnToiActionPerformed

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
       
    }//GEN-LAST:event_btnCuoiActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
    }//GEN-LAST:event_btnMoiActionPerformed

    private void txtTimKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMousePressed
    }//GEN-LAST:event_txtTimKiemMousePressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
    }//GEN-LAST:event_txtTimKiemKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollTK;
    private com.duan1.swing.MaterialTabbed Tabpane;
    private com.duan1.swing.Button btnCuoi;
    private com.duan1.swing.Button btnDauTien;
    private com.duan1.swing.Button btnLui;
    private com.duan1.swing.Button btnMoi;
    private com.duan1.swing.Button btnSua;
    private com.duan1.swing.Button btnThem;
    private com.duan1.swing.Button btnToi;
    private com.duan1.swing.Button btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblIconAn;
    private javax.swing.JLabel lblIconHien;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlThemSuaXoa;
    private com.duan1.swing.Table tblQLTK;
    private com.duan1.swing.MyTextField txtMaTaiKhoan;
    private com.duan1.swing.MyPassField txtMatKhau;
    private com.duan1.swing.MyTextField txtTaiKhoan;
    private com.duan1.swing.TextFieldAnimation txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
