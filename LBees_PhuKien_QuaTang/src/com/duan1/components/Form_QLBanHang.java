/////*
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//// * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
//// */
////package com.duan1.components;
////
////import com.duan1.DAO.HoaDonChiTietDAO;
////import com.duan1.DAO.SanPhamDAO;
////import com.duan1.Entity.HoaDonChiTiet;
////import com.duan1.Entity.SanPham;
////import com.duan1.Helper.Msgbox;
////import com.duan1.swing.Barcodes;
////import com.duan1.swing.ScrollBarCustom;
////import com.duan1.ui.MainJFrame;
////import com.github.sarxos.webcam.Webcam;
////import com.github.sarxos.webcam.WebcamPanel;
////import com.github.sarxos.webcam.WebcamResolution;
////import com.google.zxing.BinaryBitmap;
////import com.google.zxing.LuminanceSource;
////import com.google.zxing.MultiFormatReader;
////import com.google.zxing.NotFoundException;
////import com.google.zxing.Result;
////import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
////import com.google.zxing.common.HybridBinarizer;
////import java.awt.Desktop;
////import java.awt.Dimension;
////import java.awt.event.KeyEvent;
////import java.awt.image.BufferedImage;
////import java.io.IOException;
////import java.net.URI;
////import java.net.URISyntaxException;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.concurrent.Executor;
////import java.util.concurrent.Executors;
////import java.util.concurrent.ThreadFactory;
////import java.util.logging.Level;
////import java.util.logging.Logger;
////import javax.swing.JOptionPane;
////import javax.swing.RowFilter;
////import javax.swing.table.DefaultTableModel;
////import javax.swing.table.TableRowSorter;
////
/////**
//// *
//// * @author ASUS
//// */
////public class Form_QLBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {
////
////    SanPhamDAO daoSP = new SanPhamDAO();
////    List<SanPham> listSP = new ArrayList<>();
////    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
////    DefaultTableModel tblVoHang;
////    HoaDonChiTietDAO daoHDCT = new HoaDonChiTietDAO();
////    String keyword = "";
////    String sl;
////    int index = -1;
////    MainJFrame f = new MainJFrame();
////    private WebcamPanel panel = null;
////    private Webcam webcam = null;
////    private Executor executor = Executors.newSingleThreadExecutor(this);
////    public static int checks = 1;
////
////    /**
////     * Creates new form Form_QLBanHang
////     */
////    public Form_QLBanHang() {
////        initComponents();
////        init();
////    }
////    MainJFrame frame = new MainJFrame();
////
////    public void init() {
////        Scroll_GioHang.setVerticalScrollBar(new ScrollBarCustom());
////        Scroll_SPBan.setVerticalScrollBar(new ScrollBarCustom());
////        txtTimKiem.setHintText("Nhập mã, tên sản phẩm...");
////        txtMaVach.setLabelText("Mã vạch");
////        listSP = daoSP.selectAll();
////        initWebcam();
////        loadData();
////    }
////    DefaultTableModel model;
////
////    public void loadData() {
////        int tt = 0;
////        model = (DefaultTableModel) tblSanPham.getModel();
////        model.setRowCount(0);
////        try {
////            for (SanPham sp : listSP) {
////                Object[] row = {
////                    tt++,
////                    sp.getMaSP(),
////                    sp.getTenSP(),
////                    sp.getSoLuong(),
////                    sp.getDonGia(),
////                    sp.getMaVach(),
////                    sp.getTrangThai()};
////                model.addRow(row);
////            }
////        } catch (Exception e) {
////        }
////    }
////
////    public void clearGH() {
////        listHDCT.removeAll(listHDCT);
////        loadDataToGH();
////    }
////// Nếu trùng mã sẽ câp nhập số lượng
////    // ngược lại sẽ thêm
////
//////    public void addDataGH(int index) {
//////        SanPham sp = listSP.get(index);
//////        HoaDonChiTiet hdct = new HoaDonChiTiet();
//////        hdct.setMaHD(sp.getMaSP());
//////        hdct.setMaSP(sp.getMaSP());
//////        hdct.setSoLuong(Integer.parseInt(sl));
//////        hdct.setThanhTien(hdct.getSoLuong() * sp.getDonGia());
//////        listHDCT.add(hdct);
//////        loadDataToGH();
//////    }
//////    public void updateGH(int index) {
//////        SanPham sp = listSP.get(index);
//////        boolean check = true;
//////        String mess = "mã sản phẩm: " + sp.getMaSP() + "\nTên sản phẩm: " + sp.getTenSP() + "\nNhập số lượng: ";
//////        sl = JOptionPane.showInputDialog(frame, mess, "Nhập số lượng sản phẩm", JOptionPane.INFORMATION_MESSAGE);
//////        if (sl == null) {
//////            return;
//////        }
//////        try {
//////            if (sl.trim().isEmpty()) {
//////                Msgbox.waring(frame, "Bạn chưa nhập số lượng");
//////                return;
//////            }
//////            int s = Integer.parseInt(sl);
//////        } catch (NumberFormatException e) {
//////            Msgbox.waring(frame, "Vui lòng nhập số!");
//////            return;
//////        }
//////        
//////        for (HoaDonChiTiet h : listHDCT) {
//////            if (h.getMaSP().equals(sp.getMaSP())) {
//////                boolean choice = Msgbox.yesNo("Xác nhận thêm", "Sản phẩm đã được thêm\n Bạn có muốn thêm nữa không?");
//////                if (choice) {
//////                    h.setSoLuong(h.getSoLuong() + Integer.parseInt(sl));
//////                    h.setThanhTien(h.getSoLuong() * sp.getDonGia());
//////                    check = false;
//////                    loadDataToGH();
//////                    break;
//////                }
//////            }
//////        }
//////        if (check) {
//////            addDataGH(index);
//////        }
//////    }
////    public void updateGHMV(String maVach) {
////        SanPham sp = daoSP.selectByMV(maVach);
////        boolean check = true;
////        for (SanPham s : listSP) {
////            if (s.getMaVach().equalsIgnoreCase(sp.getMaVach())) {
////                if (s.getSoLuong() <= 0) {
////                    Msgbox.waring(frame, "Đã hết hàng !");
////                    return;
////                }
////            }
////        }
////        for (HoaDonChiTiet h : listHDCT) {
////            if (h.getMaSP().equals(sp.getMaSP())) {
////                boolean choice = Msgbox.yesNo("Xác nhận thêm", "Sản phẩm đã được thêm\n Bạn có muốn thêm nữa không?");
////                if (choice) {
////                    break;
////                } else {
////                    return;
////                }
////            }
////        }
////
////        MainJFrame frame = new MainJFrame();
////        String mess = "mã sản phẩm: " + sp.getMaSP() + "\nTên sản phẩm: " + sp.getTenSP() + "\nNhập số lượng: ";
////        sl = JOptionPane.showInputDialog(frame, mess, "Nhập số lượng sản phẩm", JOptionPane.INFORMATION_MESSAGE);
////        if (sl == null) {
////            return;
////        }
////        try {
////            if (sl.trim().isEmpty()) {
////                Msgbox.waring(frame, "Bạn chưa nhập số lượng");
////                return;
////            }
////            int s = Integer.parseInt(sl);
////        } catch (NumberFormatException e) {
////            Msgbox.waring(frame, "Vui lòng nhập số!");
////            return;
////        }
////        if (checkSL()) {
////
////        } else {
////            Msgbox.waring(frame, "Số lượng không đủ!");
////            return;
////        }
////
////        for (HoaDonChiTiet h : listHDCT) {
////            if (h.getMaSP().equals(sp.getMaSP())) {
////                h.setSoLuong(h.getSoLuong() + Integer.parseInt(sl));
////                h.setThanhTien(h.getSoLuong() * sp.getDonGia());
//////                daoSP.updateSL(sp.getMaSP(), sp.getSoLuong() - Integer.parseInt(sl));
////                check = false;
////
////                break;
////            }
////        }
////        for (SanPham s : listSP) {
////            if (s.getMaVach().equalsIgnoreCase(sp.getMaVach())) {
////                s.setSoLuong(s.getSoLuong() - Integer.parseInt(sl));
////                break;
////            }
////        }
////        loadDataToGH();
////        loadData();
////        setTrangThai();
////        if (check) {
////            addDataGH(maVach);
////        }
////    }
////
////    public void addDataGH(String maVach) {
////        SanPham sp = daoSP.selectByMV(maVach);
////        HoaDonChiTiet hdct = new HoaDonChiTiet();
////        hdct.setMaHD(sp.getMaSP());
////        hdct.setMaSP(sp.getMaSP());
////        hdct.setSoLuong(Integer.parseInt(sl));
////        hdct.setThanhTien(hdct.getSoLuong() * sp.getDonGia());
////        listHDCT.add(hdct);
////        loadDataToGH();
////        setTrangThai();
////    }
////
////    public void loadDataToGH() {
////        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
////        model.setRowCount(0);
////        try {
////            int tt = 0;
////            for (HoaDonChiTiet hdct : listHDCT) {
////                SanPham sp = daoSP.selectByid(hdct.getMaSP());
////                Object[] row = {
////                    tt++,
////                    hdct.getMaSP(),
////                    sp.getTenSP(),
////                    hdct.getSoLuong(),
////                    sp.getDonGia(),
////                    hdct.getThanhTien()
////                };
////                model.addRow(row);
////                model.fireTableDataChanged();
////            }
////        } catch (Exception e) {
////        }
////    }
////
////    public void deleteAll() {
////        if (listHDCT.isEmpty()) {
////            Msgbox.waring(f, "Không có sản phẩm nào để xóa!");
////            return;
////        }
////        boolean chon = Msgbox.yesNo("Xóa tất cả giỏ hàng", "Bạn có chắc chắn muốn xóa ?");
////        if (chon) {
////            for (int i = 0; i < tblGioHang.getRowCount(); i++) {
////                int sl = (int) tblGioHang.getValueAt(i, 3);
////                String maSP = (String) tblGioHang.getValueAt(i, 1);
////                for (SanPham s : listSP) {
////                    if (s.getMaSP().equalsIgnoreCase(maSP)) {
////                        s.setSoLuong(s.getSoLuong() + sl);
////                    }
////                }
////            }
////
////            listHDCT.removeAll(listHDCT);
////            loadData();
////            loadDataToGH();
////            setTrangThai();
////        }
////    }
////
////    public void findIdAndName(String IdAndName) {
////        model = (DefaultTableModel) tblSanPham.getModel();
////        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
////        tblSanPham.setRowSorter(trs);
////        trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 0, 1));
////    }
////
////    public void deleteBH(int index) {
////        if (listHDCT.isEmpty()) {
////            Msgbox.waring(f, "Không có sản phẩm nào để xóa!");
////            return;
////        }
////        if (index < 0) {
////            Msgbox.waring(f, "Bạn chưa chọn sản phẩm để xóa!");
////            return;
////        }
////        boolean chon = Msgbox.yesNo("Xóa khỏi giỏ hàng", "Bạn có chắc chắn muốn xóa ?");
////        if (chon) {
////            int sl = (int) tblGioHang.getValueAt(index, 3);
////            String maSP = (String) tblGioHang.getValueAt(index, 1);
////            for (SanPham s : listSP) {
////                if (s.getMaSP().equalsIgnoreCase(maSP)) {
////                    s.setSoLuong(s.getSoLuong() + sl);
////                }
////            }
////            listHDCT.remove(index);
////            loadDataToGH();
////            loadData();
////            setTrangThai();
////        }
////
////    }
////
////    private void initWebcam() {
////        Dimension size = WebcamResolution.VGA.getSize();
////        webcam = Webcam.getWebcams().get(0);
////        webcam.setViewSize(size);
//////        webcam.setViewSize(new Dimension(2592, 1944));
////        panel = new WebcamPanel(webcam);
////        panel.setPreferredSize(size);
//////        panel.setPreferredSize(new Dimension(2592, 1944));
////        panel.setFPSDisplayed(true);
//////2592, 1944
////        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 200));
////
////        executor.execute(this);
////    }
////
////    /**
////     *
////     */
////    @Override
////    public void run() {
////        boolean i = true;
////        do {
////            try {
////                Thread.sleep(100);
////
////            } catch (InterruptedException ex) {
//////                Logger.getLogger(com.duan1.components.Form_QLBanHang.class
//////                        .getName()).log(Level.SEVERE, null, ex);
////            }
////            Result result = null;
////            BufferedImage image = null;
////            if (webcam.isOpen()) {
////                if ((image = webcam.getImage()) == null) {
////                    continue;
////                }
////            }
////            LuminanceSource source = new BufferedImageLuminanceSource(image);
////            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
////            try {
////                result = new MultiFormatReader().decode(bitmap);
////            } catch (NotFoundException ex) {
//////                Logger.getLogger(com.duan1.components.MyWebCam.class.getName()).log(Level.SEVERE, null, ex);
////                if (checks == 1) {
//////                    List<SanPham> s = new ArrayList<>();
//////                    s = daoSP.selectAll();
////                    for (SanPham sanPham : listSP) {
////                        if (sanPham.getMaVach().equals(txtMaVach.getText())) {
////                            updateGHMV(txtMaVach.getText());
////                            txtMaVach.setText("");
////                        }
////                    }
////                }
////                if (checks == 0 || checks == 2) {
////                    webcam.close();
////                    return;
////                }
////            }
////            if (result != null) {
////                txtMaVach.setText(result.getText());
////            }
////            if (txtMaVach.getText().length() > 0) {
////                try {
////                    Desktop.getDesktop().browse(new URI(txtMaVach.getText()));
////                    i = true;
////                } catch (IOException | URISyntaxException ex) {
//////                    i = true;
////                }
////            }
////        } while (i);
////    }
////
////    @Override
////    public Thread newThread(Runnable r) {
////        Thread t = new Thread(r, "My Thread");
////        t.setDaemon(true);
////        return t;
////    }
////
////    public void setTrangThai() {
////        for (SanPham s : listSP) {
////            if (s.getSoLuong() <= 0) {
////                s.setTrangThai("Hết hàng");
////            }
////            if (s.getSoLuong() > 0 && !s.getTrangThai().equalsIgnoreCase("Không còn bán")) {
////                s.setTrangThai("Đang kinh doanh");
////            }
////        }
////        loadData();
////    }
////
////    public boolean checkSL() {
////        int soLuong;
////        if (txtMaVach.getText().trim().isEmpty()) {
////            soLuong = (int) tblSanPham.getValueAt(index, 3);
////        } else {
////            SanPham s = new SanPham();
////            s = daoSP.selectByMV(txtMaVach.getText());
////            soLuong = s.getSoLuong();
////        }
////
////        if (soLuong < Integer.parseInt(sl)) {
////            Msgbox.waring(frame, "Số lượng sản phẩm hiện tại không đủ!");
////            return false;
////        }
////        return true;
////    }
////
////    /**
////     * This method is called from within the constructor to initialize the form.
////     * WARNING: Do NOT modify this code. The content of this method is always
////     * regenerated by the Form Editor.
////     */
////    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Tabpane = new com.duan1.swing.MaterialTabbed();
        pnlDanhSach = new javax.swing.JPanel();
        Scroll_GioHang = new javax.swing.JScrollPane();
        tblGioHang = new com.duan1.swing.Table();
        Tabpane1 = new com.duan1.swing.MaterialTabbed();
        pnlDanhSach1 = new javax.swing.JPanel();
        Scroll_SPBan = new javax.swing.JScrollPane();
        tblSanPham = new com.duan1.swing.Table();
        pnlThemSuaXoa1 = new javax.swing.JPanel();
        btnXoaKhoiGio = new com.duan1.swing.Button();
        btnDatHang = new com.duan1.swing.Button();
        btnDatHang1 = new com.duan1.swing.Button();
        txtTimKiem = new com.duan1.swing.TextFieldAnimation();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtMaVach = new com.duan1.swing.MyTextField2();
        btnRead = new com.duan1.swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(923, 604));
        setMinimumSize(new java.awt.Dimension(923, 604));
        setPreferredSize(new java.awt.Dimension(923, 604));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("QUẢN LÝ BÁN HÀNG");

        pnlDanhSach.setBackground(new java.awt.Color(255, 255, 255));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "TT", "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Scroll_GioHang.setViewportView(tblGioHang);

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
            .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDanhSachLayout.createSequentialGroup()
                    .addComponent(Scroll_GioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Scroll_GioHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );

        Tabpane.addTab("Giỏ Hàng", pnlDanhSach);

        pnlDanhSach1.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "TT", "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Mã Vạch", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseEntered(evt);
            }
        });
        Scroll_SPBan.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(30);
            tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(5);
            tblSanPham.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        javax.swing.GroupLayout pnlDanhSach1Layout = new javax.swing.GroupLayout(pnlDanhSach1);
        pnlDanhSach1.setLayout(pnlDanhSach1Layout);
        pnlDanhSach1Layout.setHorizontalGroup(
            pnlDanhSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 906, Short.MAX_VALUE)
            .addGroup(pnlDanhSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDanhSach1Layout.createSequentialGroup()
                    .addComponent(Scroll_SPBan, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 71, Short.MAX_VALUE)))
        );
        pnlDanhSach1Layout.setVerticalGroup(
            pnlDanhSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(pnlDanhSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDanhSach1Layout.createSequentialGroup()
                    .addComponent(Scroll_SPBan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Tabpane1.addTab("Sản Phẩm Bán", pnlDanhSach1);

        pnlThemSuaXoa1.setBackground(new java.awt.Color(255, 255, 255));
        pnlThemSuaXoa1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnXoaKhoiGio.setText("Xóa khỏi giỏ");
        btnXoaKhoiGio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaKhoiGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoiGioActionPerformed(evt);
            }
        });
        pnlThemSuaXoa1.add(btnXoaKhoiGio);

        btnDatHang.setText("Xóa tất cả");
        btnDatHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });
        pnlThemSuaXoa1.add(btnDatHang);

        btnDatHang1.setText("Đặt hàng");
        btnDatHang1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDatHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHang1ActionPerformed(evt);
            }
        });
        pnlThemSuaXoa1.add(btnDatHang1);

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "WebCam", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(27, 178, 222))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(300, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 300));

        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel2.setMaximumSize(new java.awt.Dimension(200, 200));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtMaVach.setEditable(false);
        txtMaVach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaVachActionPerformed(evt);
            }
        });
        txtMaVach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaVachKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaVachKeyReleased(evt);
            }
        });

        btnRead.setText("Đọc");
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRead, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Tabpane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(jLabel1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pnlThemSuaXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Tabpane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(Tabpane, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlThemSuaXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tabpane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaKhoiGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoiGioActionPerformed
        index = tblGioHang.getSelectedRow();
        deleteBH(index);


    }//GEN-LAST:event_btnXoaKhoiGioActionPerformed

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        // TODO add your handling code here:

        deleteAll();
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void btnDatHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHang1ActionPerformed
        if (listHDCT.isEmpty()) {
            Msgbox.waring(f, "Chưa có sản phẩm trong giỏ hàng!");
            return;
        }
        int tongTien = 0;
        for (HoaDonChiTiet hdct : listHDCT) {
            tongTien += hdct.getThanhTien();
        }
        JDL_NhapKhachHang bh = new JDL_NhapKhachHang(f, true);

        bh.getList(listHDCT);

        bh.geList(listSP);
        bh.setTongTienSP(tongTien);
        bh.setVisible(true);
        if (bh.isSuscess()) {
            clearGH();
        }
    }//GEN-LAST:event_btnDatHang1ActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate

    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped

    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        keyword = txtTimKiem.getText();
        loadData();
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        findIdAndName(txtTimKiem.getText());
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        Barcodes s = new Barcodes();
        s.Read_IMG(f, txtMaVach);
        loadDataToGH();
    }//GEN-LAST:event_btnReadActionPerformed

    private void txtMaVachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaVachKeyReleased


    }//GEN-LAST:event_txtMaVachKeyReleased

    private void txtMaVachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaVachKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            List<SanPham> s = new ArrayList<>();
            for (SanPham sanPham : s) {
                if (sanPham.getMaVach().equals(txtMaVach.getText())) {

                    updateGHMV(txtMaVach.getText());
                    return;
                }
            }
            Msgbox.waring(frame, "Mã vạch không tồn tại!");
        }
    }//GEN-LAST:event_txtMaVachKeyPressed

    private void txtMaVachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaVachActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(f, "ss");
    }//GEN-LAST:event_txtMaVachActionPerformed

    private void tblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMouseEntered

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

        index = tblSanPham.getSelectedRow();
        String mv = (String) tblSanPham.getValueAt(index, 5);
//        txtMaVach.setText(mv);
        updateGHMV(mv);
    }//GEN-LAST:event_tblSanPhamMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Scroll_GioHang;
    private javax.swing.JScrollPane Scroll_SPBan;
    private com.duan1.swing.MaterialTabbed Tabpane;
    private com.duan1.swing.MaterialTabbed Tabpane1;
    private com.duan1.swing.Button btnDatHang;
    private com.duan1.swing.Button btnDatHang1;
    private com.duan1.swing.Button btnRead;
    private com.duan1.swing.Button btnXoaKhoiGio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlDanhSach1;
    private javax.swing.JPanel pnlThemSuaXoa1;
    private com.duan1.swing.Table tblGioHang;
    private com.duan1.swing.Table tblSanPham;
    private com.duan1.swing.MyTextField2 txtMaVach;
    private com.duan1.swing.TextFieldAnimation txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
