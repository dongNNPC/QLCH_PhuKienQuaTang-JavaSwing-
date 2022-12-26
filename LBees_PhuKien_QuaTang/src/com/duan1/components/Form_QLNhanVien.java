/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.duan1.components;

import com.duan1.DAO.NhanVienDAO;
import com.duan1.DAO.SanPhamDAO;
import com.duan1.Entity.NhanVien;
import com.duan1.Entity.SanPham;
import com.duan1.Helper.Auth;
import com.duan1.Helper.Msgbox;
import com.duan1.swing.EventCallBack;
import com.duan1.swing.EventTextField;
import com.duan1.ui.MainJFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import com.duan1.Helper.ImgHelper;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ASUS
 */
public class Form_QLNhanVien extends javax.swing.JPanel {
//
//    DefaultTableModel modelNV = new DefaultTableModel();
//    List<NhanVien> listNV = new ArrayList<>();
//    NhanVienDAO daoNV = new NhanVienDAO();
//    MainJFrame frame = new MainJFrame();
//    int index;
//    String maNV = "";
//
//    /**
//     * Creates new form Form_QLKhachHang
//     */
//    public Form_QLNhanVien() {
//        initComponents();
//        init();
//        TimKiem();
//    }
//
//    public void init() {
//        tt();
//        setHint();
//        initTable();
//        loadDaTa();
//        initComboBox();
//        
//       
//    }
//    public void tt(){
//         try {
//            maNV = "NV" + daoNV.initID();
//            txtMaNV.setText(maNV);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setHint() {
//        txtMaNV.setLabelText("Mã nhân viên");
//        txtSDT.setLabelText("Số điện thoại");
//        txtTenNV.setLabelText("Tên nhân viên");
//        txtCCCD.setLabelText("Căn cước công dân");
//        txtGmail.setLabelText("Gmail");
//        txtTieuDeGhiChu.setLabelText("Ghi chú");
//        txtTimKiem.setHintText("Tìm theo mã, tên nhân viên");
//        txtHinh.setVisible(false);
//    }
//
//    public boolean check() {
//        if (txtMaNV.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Mã nhân viên không được để trống!");
//            return false;
//        }
//        if (!txtMaNV.getText().matches("(?i)[NV]{2}\\d{5}")) {
//            Msgbox.waring(frame, "Mã nhân viên không hợp lệ!(NV00001)");
//            return false;
//        }
//        if (txtTenNV.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Tên nhân viên không được để trống!");
//            return false;
//        }
//        if (!txtTenNV.getText().matches("^([A-Za-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơưăêâưôđ']+)((\s{1}[A-Za-zĐỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ']+){1,})$")) {
//            Msgbox.waring(frame, "Tên nhân viên không hợp lệ!(Trần Tấn Khanh)");
//            return false;
//        }
//        if (txtSDT.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Số điện thoại không được để trống không được để trống!");
//            return false;
//        }
//        if (!txtSDT.getText().matches("^0[9384]{1}\\d{8}$")) {
//            Msgbox.waring(frame, "Số điện thoại không hợp lệ!(0829232859)");
//            return false;
//        }
//        if (txtCCCD.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Căn cước công dân không được để trống!");
//            return false;
//        }
//        if (!txtCCCD.getText().matches("^\\d{12}$")) {
//            Msgbox.waring(frame, "Căn cước công dân không hợp lệ!(109876547817)");
//            return false;
//        }
//        if (rdoNam.isSelected() == false && rdoNu.isSelected() == false) {
//            Msgbox.waring(frame, "Bạn chưa chọn giới tính!");
//            return false;
//        }
//        if (txtGmail.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Gmail không được để trống!");
//            return false;
//        }
//        if (!txtGmail.getText().matches("^\\w+@gmail\\.com$")) {
//            Msgbox.waring(frame, "Gmail không hợp lệ!(khanh@gmail.com)");
//            return false;
//        }
//        return true;
//    }
//
//    public boolean checkCode() {
//        if (txtMaNV.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Mã nhân viên không được để trống!");
//            return false;
//        }
//        listNV = daoNV.selectAll();
//        for (NhanVien n : listNV) {
//            if (n.getMaNV().equalsIgnoreCase(txtMaNV.getText())) {
//                return true;
//            }
//        }
//        Msgbox.waring(frame, "Mã nhân viên không tồn tại!");
//        return false;
//
//    }
//
//    public boolean checkUnique() {
//        listNV = daoNV.selectAll();
//        for (NhanVien n : listNV) {
//            if (n.getMaNV().equalsIgnoreCase(txtMaNV.getText())) {
//                Msgbox.waring(frame, "Mã nhân viên đã tồn tại!");
//                return false;
//            }
//        }
//        for (NhanVien n : listNV) {
//            if (n.getSDT().equalsIgnoreCase(txtSDT.getText())) {
//                Msgbox.waring(frame, "Số điện thoại đã tồn tại!");
//                return false;
//            }
//        }
//        for (NhanVien n : listNV) {
//            if (n.getCCCD().equalsIgnoreCase(txtCCCD.getText())) {
//                Msgbox.waring(frame, "Căc cước công dân đã tồn tại!");
//                return false;
//            }
//        }
//        for (NhanVien n : listNV) {
//            if (n.getGmail().equalsIgnoreCase(txtGmail.getText())) {
//                Msgbox.waring(frame, "Gmail đã tồn tại!");
//                return false;
//            }
//        }
//
//        return true;
//    }
//
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
//    public void initComboBox() {
//        Object[] rows = new Object[]{"Quản lý", "Nhân viên"};
//        cboChucVu.setModel(new DefaultComboBoxModel(rows));
//    }
//
//    public void initTable() {
//        String[] cols = new String[]{"Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "CCCD", "SDT", "Gmail", "Chức Vụ", "Ghi chú", "Hình ảnh"};
//        modelNV.setColumnIdentifiers(cols);
//        tblNhanVien.setModel(modelNV);
//    }
//
//    public void loadDaTa() {
//        while (modelNV.getRowCount() > 0) {
//            modelNV.removeRow(0);
//        }
//        listNV = daoNV.selectAll();
//        for (NhanVien n : listNV) {
//            Object[] Rows = new Object[]{n.getMaNV(), n.getTenNV(), n.isGioiTinh() ? "Nam" : "Nữ", n.getCCCD(), n.getSDT(), n.getGmail(), n.isChucVu() ? "Quản lý" : "Nhân viên", n.getGhiChu(), n.getHinhAnh()};
//            modelNV.addRow(Rows);
//        }
//    }
//
//    public void findIdAndName(String IdAndName) {
//        modelNV = (DefaultTableModel) tblNhanVien.getModel();
//        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(modelNV);
//        tblNhanVien.setRowSorter(trs);
//        trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 0, 1));
//    }
//
//    public NhanVien getForm() {
//        NhanVien n = new NhanVien();
//        n.setMaNV(txtMaNV.getText());
//        n.setTenNV(txtTenNV.getText());
//        if (rdoNam.isSelected()) {
//            n.setGioiTinh(true);
//        } else {
//            n.setGioiTinh(false);
//        }
//        n.setCCCD(txtCCCD.getText());
//        n.setSDT(txtSDT.getText());
//        n.setGmail(txtGmail.getText());
//        if (String.valueOf(cboChucVu.getSelectedItem()).equalsIgnoreCase("Quản lý")) {
//            n.setChucVu(true);
//        } else {
//            n.setChucVu(false);
//        }
//        n.setHinhAnh(txtHinh.getText());
//        n.setGhiChu(txtGhiChu.getText());
//        return n;
//    }
//
//    public void setForm(NhanVien n) {
//        txtMaNV.setText(n.getMaNV());
//        txtTenNV.setText(n.getTenNV());
//        if (n.isGioiTinh()) {
//            rdoNam.setSelected(true);
//        } else {
//            rdoNu.setSelected(true);
//        }
//        txtCCCD.setText(n.getCCCD());
//        txtSDT.setText(txtSDT.getText());
//        txtGmail.setText(n.getGmail());
//        txtHinh.setText(n.getHinhAnh());
//        if (n.isChucVu()) {
//            cboChucVu.setSelectedItem("Quản lý");
//        } else {
//            cboChucVu.setSelectedItem("Nhân viên");
//        }
//        txtGhiChu.setText(n.getGhiChu());
//    }
//
//    public void display(int index) {
//        NhanVien n = listNV.get(index);
//        txtMaNV.setText(n.getMaNV());
//        txtTenNV.setText(n.getTenNV());
//        txtSDT.setText(n.getSDT());
//        if (n.isGioiTinh()) {
//            rdoNam.setSelected(true);
//        } else {
//            rdoNu.setSelected(true);
//        }
//        txtCCCD.setText(n.getCCCD());
//        txtSDT.setText(n.getSDT());
//        txtGmail.setText(n.getGmail());
//        txtHinh.setText(n.getHinhAnh());
//        if (n.isChucVu()) {
//            cboChucVu.setSelectedItem("Quản lý");
//        } else {
//            cboChucVu.setSelectedItem("Nhân viên");
//        }
//        txtGhiChu.setText(n.getGhiChu());
//        ImgHelper m = new ImgHelper();
//        m.loadHinhVaoForm(String.valueOf(n.getHinhAnh()), lblHinh);
//    }
//
//    public void clear() {
//        txtMaNV.setText("");
//        txtTenNV.setText("");
//        rdoNam.setSelected(false);
//        rdoNu.setSelected(false);
//
//        txtCCCD.setText("");
//        txtSDT.setText("");
//        txtGmail.setText("");
//        cboChucVu.setSelectedItem("Quản lý");
//        txtHinh.setText("");
//        txtGhiChu.setText("");
//    }
//
//    public void insert() {
//        try {
//            NhanVien n = new NhanVien();
//            n = getForm();
//            if (n != null) {
//                daoNV.insert(n);
//                loadDaTa();
//                Msgbox.success(frame, "Thêm thành công");
//                clear();
//            }
//        } catch (Exception ex) {
//            Msgbox.waring(frame, "Mã nhân viên đã tồn tại!");
//            throw new RuntimeException(ex);
//
//        }
//        tt();
//    }
//
//    public void update() {
//        try {
//            NhanVien n = new NhanVien();
//            n = getForm();
//            if (n != null) {
//                daoNV.update(n);
//                loadDaTa();
//                clear();
//                Msgbox.success(frame, "Thêm thành công");
//            }
//            frame.setavatar();
//        } catch (Exception ex) {
//            Msgbox.waring(frame, "Mã nhân viên không tồn tại");
//            throw new RuntimeException(ex);
//        }
//        tt();
//    }
//
//    public void delete() {
//        SanPhamDAO daoSP = new SanPhamDAO();
//        List<SanPham> listSP = daoSP.selectAll();
//        for (SanPham s : listSP) {
//            if (s.getMaNV().equals(txtMaNV.getText())) {
//                Msgbox.success(frame, "Nhân viên này đang quản lý sản phẩm");
//                return;
//            }
//        }
//        for (SanPham s : listSP) {
//            if (s.getMaNV().equals(txtMaNV.getText())) {
//                Msgbox.success(frame, "Nhân viên này đang quản lý sản phẩm");
//            }
//        }
//        try {
//            if (Msgbox.yesNo("bạn có muốn xóa", "bạn chắc chắn không???")) {
//                daoNV.delete(txtMaNV.getText());
//                loadDaTa();
//                Msgbox.waring(frame, "Xóa thành công!");
//                clear();
//            }
//        } catch (Exception ex) {
//            Msgbox.waring(frame, "Mã nhân viên không tồn tại");
//            throw new RuntimeException(ex);
//        }
//        tt();
//    }
//
//    public void edit() {
//        tblNhanVien.setRowSelectionInterval(index, index);
//        display(index);
//    }
//    //fist
//
//    public void firs() {
//        index = 0;
//        edit();
//        Msgbox.infoCT(frame, "Đang ở đầu danh sách nhân viên");
//    }
//
//    //end
//    public void last() {
//        index = listNV.size() - 1;
//        edit();
//        Msgbox.infoCT(frame, "Đang ở cuối danh sách nhân viên");
//    }
//
//    //prev
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
//    //next
//    public void next() {
//        if (index == listNV.size() - 1) {
//            firs();
//        } else {
//            index++;
//        }
//        edit();
//
//    }
//    private javax.swing.JFileChooser FileChooser;

//    public void ChonHinh() {
//        FileChooser = new javax.swing.JFileChooser();
//        if (FileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//            File file = FileChooser.getSelectedFile();
//
//            if (ImgHelper.saveLogo(file)) {
//                // Hiển thị hình lên form 
//                ImageIcon hinhanh = new ImageIcon(new ImageIcon(String.valueOf(ImgHelper.readLogo(file.getName()))).getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH));
//                lblHinh.setIcon(hinhanh);
//                txtHinh.setText(file.getName());
//            }
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtTimKiem = new com.duan1.swing.TextFieldAnimation();
        Tabpane = new com.duan1.swing.MaterialTabbed();
        pnls = new javax.swing.JPanel();
        pnlText = new javax.swing.JPanel();
        pnlThemSuaXoa = new javax.swing.JPanel();
        btnThem = new com.duan1.swing.Button();
        btnSua = new com.duan1.swing.Button();
        btnXoa = new com.duan1.swing.Button();
        btnMoi = new com.duan1.swing.Button();
        pnlDieuHuong = new javax.swing.JPanel();
        button6 = new com.duan1.swing.Button();
        button7 = new com.duan1.swing.Button();
        button8 = new com.duan1.swing.Button();
        button2 = new com.duan1.swing.Button();
        rdoNam = new com.duan1.swing.RadioButtonCustom();
        rdoNu = new com.duan1.swing.RadioButtonCustom();
        txtTieuDeGhiChu = new com.duan1.swing.TextAreaScroll();
        txtGhiChu = new com.duan1.swing.TextArea();
        txtMaNV = new com.duan1.swing.MyTextField2();
        txtSDT = new com.duan1.swing.MyTextField2();
        txtTenNV = new com.duan1.swing.MyTextField2();
        txtCCCD = new com.duan1.swing.MyTextField2();
        txtGmail = new com.duan1.swing.MyTextField2();
        cboChucVu = new com.duan1.swing.ComboBoxSuggestion();
        lblHinh = new javax.swing.JLabel();
        txtHinh = new javax.swing.JTextField();
        pnlDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new com.duan1.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(923, 604));
        setMinimumSize(new java.awt.Dimension(923, 604));
        setPreferredSize(new java.awt.Dimension(923, 604));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        pnls.setBackground(new java.awt.Color(255, 255, 255));

        pnlText.setBackground(new java.awt.Color(255, 255, 255));

        pnlThemSuaXoa.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemSuaXoa.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlThemSuaXoa.add(btnThem);

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

        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        pnlThemSuaXoa.add(btnMoi);

        pnlDieuHuong.setBackground(new java.awt.Color(255, 255, 255));
        pnlDieuHuong.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        button6.setText("<");
        button6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(button6);

        button7.setText("|<");
        button7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(button7);

        button8.setText(">|");
        button8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(button8);

        button2.setText(">");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(button2);

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        txtTieuDeGhiChu.setViewportView(txtGhiChu);

        txtMaNV.setEditable(false);

        txtGmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGmailActionPerformed(evt);
            }
        });

        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan1/icon/Avatar.png"))); // NOI18N
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlTextLayout = new javax.swing.GroupLayout(pnlText);
        pnlText.setLayout(pnlTextLayout);
        pnlTextLayout.setHorizontalGroup(
            pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTextLayout.createSequentialGroup()
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlTextLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(pnlThemSuaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(234, 234, 234)
                        .addComponent(pnlDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTextLayout.createSequentialGroup()
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTextLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txtHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTextLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlTextLayout.createSequentialGroup()
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlTextLayout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTextLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTextLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(txtTieuDeGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        pnlTextLayout.setVerticalGroup(
            pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTextLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTextLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTextLayout.createSequentialGroup()
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(txtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTieuDeGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlThemSuaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlsLayout = new javax.swing.GroupLayout(pnls);
        pnls.setLayout(pnlsLayout);
        pnlsLayout.setHorizontalGroup(
            pnlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlsLayout.setVerticalGroup(
            pnlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Tabpane.addTab("Cập Nhật", pnls);

        pnlDanhSach.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "CCCD", "Số Điện Thoại", "Gmail", "Chức Vụ", "Ghi Chú", "Hình Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        Tabpane.addTab("Danh Sách", pnlDanhSach);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tabpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed


    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
    }//GEN-LAST:event_btnMoiActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void txtGmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGmailActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblHinhMouseClicked

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button6ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button7ActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button8ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button2ActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan1.swing.MaterialTabbed Tabpane;
    private com.duan1.swing.Button btnMoi;
    private com.duan1.swing.Button btnSua;
    private com.duan1.swing.Button btnThem;
    private com.duan1.swing.Button btnXoa;
    private com.duan1.swing.Button button2;
    private com.duan1.swing.Button button6;
    private com.duan1.swing.Button button7;
    private com.duan1.swing.Button button8;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.duan1.swing.ComboBoxSuggestion cboChucVu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlDieuHuong;
    private javax.swing.JPanel pnlText;
    private javax.swing.JPanel pnlThemSuaXoa;
    private javax.swing.JPanel pnls;
    private com.duan1.swing.RadioButtonCustom rdoNam;
    private com.duan1.swing.RadioButtonCustom rdoNu;
    private com.duan1.swing.Table tblNhanVien;
    private com.duan1.swing.MyTextField2 txtCCCD;
    private com.duan1.swing.TextArea txtGhiChu;
    private com.duan1.swing.MyTextField2 txtGmail;
    private javax.swing.JTextField txtHinh;
    private com.duan1.swing.MyTextField2 txtMaNV;
    private com.duan1.swing.MyTextField2 txtSDT;
    private com.duan1.swing.MyTextField2 txtTenNV;
    private com.duan1.swing.TextAreaScroll txtTieuDeGhiChu;
    private com.duan1.swing.TextFieldAnimation txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
