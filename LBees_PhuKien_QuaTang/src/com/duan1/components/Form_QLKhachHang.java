/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.duan1.components;

import com.duan1.DAO.KhachHangDAO;

import com.duan1.Entity.HoaDon;
import com.duan1.Entity.KhachHang;
import com.duan1.Entity.NguoiGiaoHang;
import com.duan1.Helper.Auth;
import com.duan1.Helper.Msgbox;

import com.duan1.swing.EventCallBack;
import com.duan1.swing.EventTextField;
import com.duan1.swing.MessageDialog;
import com.duan1.swing.Notification;
import com.duan1.swing.ScrollBarCustom;
import com.duan1.ui.MainJFrame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import org.jdesktop.swingx.sort.RowFilters;

/**
 *
 * @author ASUS
 */
public class Form_QLKhachHang extends javax.swing.JPanel {

    MainJFrame frame = new MainJFrame();

    KhachHangDAO daoKH = new KhachHangDAO();
    List<KhachHang> listKH = daoKH.selectAll();
    DefaultTableModel model;

    int index = -1;
    
    /**
     * Creates new form Form_QLKhachHang
     */
    public Form_QLKhachHang() {
        initComponents();
        setHint();
        TimKiem();
        ThanhTruotTb();
        loadData();
        hideDelete();
    }

    public void hideDelete() {
        if (Auth.role() == false) {
            btnXoa.setVisible(false);
        }

    }
   

    public void ThanhTruotTb() {
        ScrollKH.setVerticalScrollBar(new ScrollBarCustom());
    }

    public void TimKiem() {
        txtTimKiem.setHintText("Nhập mã, tên khách hàng");
        txtTimKiem.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                //  Test
                try {
                    for (int i = 1; i <= 100; i++) {

                        Thread.sleep(5);
                    }
                    call.done();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void setHint() {
        txtMaKhachHang.setHint("Nhập mã khách hàng");
        txtTenKhachHang.setHint("Nhập tên khách hàng");
        txtSDT.setHint("Nhập số điện thoại");
        txtDiemTichLuy.setHint("Nhập điểm tích lũy");
    }

    public void loadData() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        listKH = daoKH.selectAll();
        for (KhachHang kh : listKH) {
            Object[] row = {
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getSDT(),
                kh.getDiemTichLuy()
            };
            model.addRow(row);

        }

    }

    public void setForm(KhachHang kh) {
        txtMaKhachHang.setText(kh.getMaKH());
        txtTenKhachHang.setText(kh.getTenKH());
        txtSDT.setText(kh.getSDT());
        txtDiemTichLuy.setText(String.valueOf(kh.getDiemTichLuy()));
    }

    public KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMaKhachHang.getText());
        kh.setTenKH(txtTenKhachHang.getText());
        kh.setSDT(txtSDT.getText());
        kh.setDiemTichLuy(Integer.parseInt(txtDiemTichLuy.getText()));
        return kh;
    }

    public void display(int index) {
        KhachHang kh = listKH.get(index);
        setForm(kh);
    }

    public void clear() {
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSDT.setText("");
        txtDiemTichLuy.setText("");
    }

    public void insert() {
        try {
            KhachHang kh = getForm();
            if(Msgbox.yesNo("Bạn có muốn thêm khách hàng", "bạn chắc chắn không?")){
                daoKH.insert(kh);
                loadData();
                clear();
                Msgbox.success(frame, "Thêm khách hàng thành công");
                Tabpane.setSelectedIndex(1);

            }
        } catch (Exception e) {
                Msgbox.waring(frame, "Mã khách hàng không tồn tại");
        }
        
    }

    public void update() {
        try {
            KhachHang kh = getForm();
         if (Msgbox.yesNo("bạn có muốn cập nhật khách hàng", "bạn chắc chắn không???")) {
            daoKH.update(kh);
            loadData();
            clear();
            Msgbox.success(frame, "cập nhật khách hàng thành công");
            Tabpane.setSelectedIndex(1);
        }
            } catch (Exception e) {
                 Msgbox.waring(frame, "Mã khách hàng không tồn tại");  
            }
        }
         
    public void delete() {
        try {
            if (Msgbox.yesNo("bạn có muốn xóa khách hàng", "bạn chắc chắn không???")) {         
            String maKH = txtMaKhachHang.getText();
            daoKH.delete(maKH);
            loadData();
            clear();
            Msgbox.success(frame, "Xóa khách hàng thành công");
            } 
        } catch (Exception e) {          
              
        }
        
    }

    public void updateStatus() {
        if (index == 0) {
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnPrev.setEnabled(true);
        }
        if (index == listKH.size() - 1) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        } else {
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        }
    }

    public void edit() {
        tblKhachHang.setRowSelectionInterval(index, index);
        display(index);
        updateStatus();
    }

    public void firs() {
        index = 0;
        edit();
        Msgbox.infoCT(frame, "Đang ở đầu danh sách khách hàng");
    }

    public void last() {
        index = listKH.size() - 1;
        edit();
        Msgbox.infoCT(frame, "Đang ở cuối danh sách khách hàng");
    }

    public void prev() {
        if (index <= 0) {
            last();
        } else {
            index--;
        }
        edit();
        

    }

    public void next() {
        if (index == listKH.size() - 1) {
            firs();
        } else {
            index++;
        }
        edit();

    }

    public void findIdAndName(String IdAndName) {
        model = (DefaultTableModel) tblKhachHang.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblKhachHang.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 0, 1));

    }

    public boolean check() {
        String MAKH_REGEX = "(?i)[kh]{2}\\d{5}";
        boolean MAKH = txtMaKhachHang.getText().matches(MAKH_REGEX);
        if (txtMaKhachHang.getText().trim().isEmpty()) {
            Msgbox.waring(frame, "Mã khách hàng không được để trống");
            txtMaKhachHang.requestFocus();
            return false;
        }
        if (MAKH != true) {
            Msgbox.waring(frame, "Mã khách hàng không đúng định dạng (KH00001)");
            txtMaKhachHang.requestFocus();
            return false;
        }

        //^([a-z]+)((\s{1}[a-z]+){1,})$
        String TENKH_REGEX = "^([A-Za-zỲọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ']+)((\\s{1}[A-Za-zỲọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ']+){1,})$";

        boolean TENKH = txtTenKhachHang.getText().matches(TENKH_REGEX);
        if (txtTenKhachHang.getText().trim().isEmpty()) {
            Msgbox.waring(frame, "Tên khách hàng không được để trống");
            txtTenKhachHang.requestFocus();
            return false;
        }
        if (TENKH != true) {
            Msgbox.waring(frame, "Tên khách hàng không đúng định dạng (Nguyễn Văn A)");
            txtTenKhachHang.requestFocus();
            return false;
        }

        //
        
        String SDTKH_REGEX = "0[35789]{1}\\d{8}$";
        boolean SDTKH = txtSDT.getText().matches(SDTKH_REGEX);
        if (txtSDT.getText().trim().isEmpty()) {
            Msgbox.waring(frame, "Số điện thoại không được để trống");
            txtSDT.requestFocus();
            return false;
        }
        if (SDTKH != true) {
            Msgbox.waring(frame, "Số điện thoại không đúng định dạng(chỉ nhập số và gồm 10 chỉ số)");
            txtSDT.requestFocus();
            return false;
        }

        //
        if (txtDiemTichLuy.getText().trim().isEmpty()) {
            Msgbox.waring(frame, "Điểm tích lũy không được để trống");
            txtDiemTichLuy.requestFocus();
            return false;
        } else {
            try {
                double diem = Integer.parseInt(txtDiemTichLuy.getText());
                if (diem < 0) {
                    Msgbox.waring(frame, "Điểm tích lũy phải lớn hơn 0");
                    txtDiemTichLuy.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                Msgbox.waring(frame, "Vui lòng nhập điểm tích lũy bằng số");
                txtDiemTichLuy.requestFocus();
                return false;
            }
        }

        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        txtTimKiem = new com.duan1.swing.TextFieldAnimation();
        Tabpane = new com.duan1.swing.MaterialTabbed();
        pnlCapNhat = new javax.swing.JPanel();
        pnlText = new javax.swing.JPanel();
        txtMaKhachHang = new com.duan1.swing.MyTextField();
        txtTenKhachHang = new com.duan1.swing.MyTextField();
        txtSDT = new com.duan1.swing.MyTextField();
        txtDiemTichLuy = new com.duan1.swing.MyTextField();
        pnlThemSuaXoa = new javax.swing.JPanel();
        btnSua = new com.duan1.swing.Button();
        btnXoa = new com.duan1.swing.Button();
        pnlDieuHuong = new javax.swing.JPanel();
        btnFirst = new com.duan1.swing.Button();
        btnPrev = new com.duan1.swing.Button();
        btnNext = new com.duan1.swing.Button();
        btnLast = new com.duan1.swing.Button();
        pnlDanhSach = new javax.swing.JPanel();
        ScrollKH = new javax.swing.JScrollPane();
        tblKhachHang = new com.duan1.swing.Table();
        jLabel1 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(923, 604));
        setMinimumSize(new java.awt.Dimension(923, 604));
        setPreferredSize(new java.awt.Dimension(923, 604));

        txtTimKiem.setBackground(new java.awt.Color(235, 227, 227));
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

        pnlCapNhat.setBackground(new java.awt.Color(255, 255, 255));

        pnlText.setBackground(new java.awt.Color(255, 255, 255));

        txtMaKhachHang.setEditable(false);

        pnlThemSuaXoa.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemSuaXoa.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlThemSuaXoa.add(btnSua);

        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlThemSuaXoa.add(btnXoa);

        pnlDieuHuong.setBackground(new java.awt.Color(255, 255, 255));
        pnlDieuHuong.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnFirst.setText("|<");
        btnFirst.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnFirst);

        btnPrev.setText("<");
        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnPrev);

        btnNext.setText(">");
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnNext);

        btnLast.setText(">|");
        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnLast);

        javax.swing.GroupLayout pnlTextLayout = new javax.swing.GroupLayout(pnlText);
        pnlText.setLayout(pnlTextLayout);
        pnlTextLayout.setHorizontalGroup(
            pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTextLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTextLayout.createSequentialGroup()
                .addComponent(pnlThemSuaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTextLayout.setVerticalGroup(
            pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTextLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDieuHuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlThemSuaXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Tabpane.addTab("Cập Nhật", pnlCapNhat);

        pnlDanhSach.setBackground(new java.awt.Color(255, 255, 255));

        ScrollKH.setBackground(new java.awt.Color(255, 255, 255));
        ScrollKH.setBorder(null);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Điểm Tích Lũy"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        ScrollKH.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(240);
            tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(190);
            tblKhachHang.getColumnModel().getColumn(3).setMinWidth(50);
            tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollKH, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollKH, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );

        Tabpane.addTab("Danh Sách", pnlDanhSach);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Khách Hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tabpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tabpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int index = tblKhachHang.getSelectedRow();
        if (evt.getClickCount() == 2) {
            display(index);
            Tabpane.setSelectedIndex(0);
        }


    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (check()) {
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        String ID = txtTimKiem.getText().trim();
        findIdAndName(ID);
        loadData();
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMousePressed
        String ID = txtTimKiem.getText();
        findIdAndName(ID);
    }//GEN-LAST:event_txtTimKiemMousePressed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        firs();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();
    }//GEN-LAST:event_btnLastActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollKH;
    private com.duan1.swing.MaterialTabbed Tabpane;
    private com.duan1.swing.Button btnFirst;
    private com.duan1.swing.Button btnLast;
    private com.duan1.swing.Button btnNext;
    private com.duan1.swing.Button btnPrev;
    private com.duan1.swing.Button btnSua;
    private com.duan1.swing.Button btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlDieuHuong;
    private javax.swing.JPanel pnlText;
    private javax.swing.JPanel pnlThemSuaXoa;
    private com.duan1.swing.Table tblKhachHang;
    private com.duan1.swing.MyTextField txtDiemTichLuy;
    private com.duan1.swing.MyTextField txtMaKhachHang;
    private com.duan1.swing.MyTextField txtSDT;
    private com.duan1.swing.MyTextField txtTenKhachHang;
    private com.duan1.swing.TextFieldAnimation txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
