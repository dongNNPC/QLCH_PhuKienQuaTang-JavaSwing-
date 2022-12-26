package com.duan1.components;

import com.duan1.DAO.HoaDonDAO;
import com.duan1.DAO.KhachHangDAO;
import com.duan1.DAO.QuaTangDAO;
import com.duan1.DAO.SanPhamDAO;
import com.duan1.Entity.HoaDon;
import com.duan1.Entity.HoaDonChiTiet;
import com.duan1.Entity.KhachHang;
import com.duan1.Entity.QuaTang;
import com.duan1.Entity.SanPham;
import com.duan1.Helper.Auth;
import com.duan1.Helper.Msgbox;
import com.duan1.Helper.XDate;
import com.duan1.ui.MainJFrame;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.sound.sampled.*;

public class JDL_XacNhanThongTin_QuaTang extends javax.swing.JDialog {

    QuaTang qt = new QuaTang();
    KhachHang kh = new KhachHang();

    KhachHangDAO daoKH = new KhachHangDAO();
    HoaDonDAO daoHD = new HoaDonDAO();
    QuaTangDAO daoQT = new QuaTangDAO();
    List<HoaDonChiTiet> listHDCT = null;
    List<KhachHang> listKH = new ArrayList<>();
    SanPhamDAO daosp = new SanPhamDAO();
    String tenKH = "";
    String SDTKH = "";
    String MaKH = "";
    String MaHD = "";
    String mauNgay = "dd-MM-yyyy";
    String trangThaiString;
    String maNGH;
    String maHD;

    MainJFrame f = new MainJFrame();
    List<SanPham> listSP = null;

    public JDL_XacNhanThongTin_QuaTang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setBackground(new Color(0, 0, 0, 0));
        setHint();
        getForm();

    }

    public List<SanPham> geList(List<SanPham> listSP) {
        return this.listSP = listSP;
    }

    public List<HoaDonChiTiet> getList(List<HoaDonChiTiet> listHDCT) {
        return this.listHDCT = listHDCT;
    }

    public void setHint() {
        txttTienKhachDua.setLabelText("Tiền khách đưa");
        txtTienPhiShip.setLabelText("Tiền phí ship");
        txtpdf.setVisible(false);
    }

    public QuaTang getData(QuaTang qt) {
        return this.qt = qt;
    }

    public void getForm() {
        try {
            MaKH = "KH" + daoKH.initID();
            MaHD = "HD" + daoHD.initID();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        kh = new KhachHang(MaKH, tenKH, SDTKH, 0);

        //set dữ liệu khh
//        lblMaKH.setText(MaKH);
//        lblTenKH.setText(tenKH);
//        lblSDTKH.setText(SDTKH);
//        //set dữ liệu nguuoiNhan
//        lblTenNN.setText(qt.getTenNN());
//        lblDiaChi.setText(qt.getDiaChiNN());
//        lblSDTNN.setText(qt.getSDTNN());
    }

    public void seta(String makh, String tenkh, String sdtkh, String tenn, String diachi, String sdtnn, String ngayNhan, String trangThai, String NGH, String HD) {
        lblMaKH.setText(makh);
        lblTenKH.setText(tenkh);
        lblSDTKH.setText(sdtkh);
        lblTenNN.setText(tenn);
        lblDiaChi.setText(diachi);
        lblSDTNN.setText(sdtnn);
        lblNgayNhan.setText(ngayNhan);
        trangThaiString = trangThai;
        maNGH = NGH;
        MaHD = HD;
        System.out.println(trangThaiString);
        System.out.println(maNGH);
        System.out.println(MaHD);
    }

    public long lamTron(int tongTien) {
        int tienTT = tongTien;
        int donViTram = tongTien % 1000;
        if (donViTram != 0) {
            if (donViTram >= 500) {
                tienTT = tongTien + (1000 - donViTram);
            } else {
                tienTT = tongTien - donViTram;
            }
        }
        return tienTT;
    }
    long tongTien = 0;
    long tienThanhToan = 0;

    public void setTongTienSP(int tongTien) {
        this.tongTien = tongTien;
        tienThanhToan = tongTien;
        String tToan = String.format("%,d", tienThanhToan);
        String tTien = String.format("%,d", tongTien);

        lblTongTien.setText(tTien + "");
        lblThanhToan.setText(tToan + "");
        //  txtTienPhiShip.setText(tienPhiShip);
    }

    public void music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("./src/com/duan1/icon/sound_ThanhToanThanhCong (1).wav");
        AudioInputStream ad = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(ad);
        clip.start();
    }

    public void insertHD() throws SQLException {
        HoaDon hd = new HoaDon();
        try {

            KhachHang k = new KhachHang(lblMaKH.getText(), lblTenKH.getText(), lblSDTKH.getText(), 0);
            boolean s = false;
            listKH = daoKH.selectAll();
            for (KhachHang kh : listKH) {
                if (kh.getSDT().equalsIgnoreCase(k.getSDT())) {
                    boolean x = Msgbox.yesNo("Hệ Thống", "Số điện thoại đã được sử dụng với tên người dùng là: " + kh.getTenKH() + " bạn có muốn đặt lại tên người dùng hay không?");
                    if (x) {
                        k.setTenKH(lblTenKH.getText());
                    } else {
                        k.setTenKH(kh.getTenKH());
                    }
                    k.setMaKH(kh.getMaKH());
                    k.setSDT(kh.getSDT());
                    daoKH.update(k);
                    s = true;
                    break;
                }
            }
            if (s == false) {
                daoKH.insert(k);
            }
            hd = new HoaDon(MaHD, 0, new Date(), "", Auth.tk.getMaNV(), k.getMaKH());
            daoHD.insert(hd);
            XDate.toDate(lblNgayNhan.getText(), mauNgay);
            QuaTang qt = new QuaTang("DH"+daoQT.initID(), lblTenNN.getText(), lblDiaChi.getText(), lblSDTNN.getText(), XDate.toDate(lblNgayNhan.getText(), mauNgay), trangThaiString, "", maNGH, MaHD);
            daoQT.insert(qt);
            for (SanPham sanPham : listSP) {
                daosp.update(sanPham);
            }
            inHoaDon();
            System.out.println("Thêm thành công");
            music();
            Msgbox.success(f, "Thanh toán thanh công!");
            dispose();
        } catch (Exception ex) {
            Msgbox.success(f, "Thêm thất bại!");
            throw new RuntimeException(ex);
        }

    }
    int tt = 0;

    public void inHoaDon() throws PrinterException {
        String pdf = "\t\tSHOP PHỤ KIỆN LBEES\n";
        pdf += "\t\tPHIẾU THANH TOÁN \t \t\t\n";
        pdf += "Ngày: " + java.time.LocalDate.now() + "\t\t\t";
        pdf += "Mã hóa đơn: HD0001 \n";
        pdf += "Nhân viên : Minh Khôi \t\t\t";

        Date hienTai = new Date();
        SimpleDateFormat dinhDang = new SimpleDateFormat("hh:mm:ss");
        pdf += "In lúc: " + dinhDang.format(hienTai) + "\n\n";

        pdf += "  TT   |Sản phẩm\t|Số lượng\t|Đơn vị tính\t|Giá\t|Tiền\n";
        pdf += ".............................................................................................................\n";
        for (HoaDonChiTiet h : listHDCT) {
            SanPham sp = daosp.selectByid(h.getMaSP());
//            String tenSp = String.format("%s", sp.getTenSP());
//            String sl = String.format("%s", h.getSoLuong() + "");
//            String DonViTinh = String.format("%s", sp.getDonViTinh());
//            String donGia = String.format("%s", sp.getDonGia() + "");
//            String thanhTien = String.format("%s", h.getThanhTien() + "");
//            pdf += "." + tt++ + "         " + sp.getTenSP() + "              " + h.getSoLuong() + "                   " + sp.getDonViTinh() + "                    " + sp.getDonGia() + "             " + h.getThanhTien() + "\n";
//            pdf += "." + tt++ + "         " + String.format("%s", sp.getTenSP()) + "              " + String.format("%s", h.getSoLuong() + "") + "                   " + String.format("%s", sp.getDonViTinh()) + "                    " + String.format("%s", sp.getDonGia() + "") + "             " + String.format("%s", h.getThanhTien() + "") + "\n";
            pdf += "." + tt++ + "\t";
            pdf += String.format("%s", sp.getTenSP()) + "\t";
            pdf += String.format("%s", h.getSoLuong() + "") + "\t";
            pdf += String.format("%s", sp.getDonViTinh()) + "\t";
            pdf += String.format("%s", sp.getDonGia() + "") + "\t";
            pdf += String.format("%s", h.getThanhTien() + "") + "\n";
        }
        pdf += "\n\n ..................................................................................................................\n\n\n";
        pdf += "Tổng tiền: " + lblTongTien.getText() + "VNĐ";
        pdf += "\t\tTiền khách đưa: " + txttTienKhachDua.getText() + "VNĐ" + "\n\n";
        pdf += "Thanh toán: " + lblThanhToan.getText() + "VNĐ";
        pdf += "\t\tTrả lại: " + lblTienThua.getText() + "VNĐ";
        txtpdf.setText(pdf);
        txtpdf.print();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTrang1 = new com.duan1.components.panelTrang();
        lblXacNhan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtTienPhiShip = new com.duan1.swing.MyTextField2();
        txttTienKhachDua = new com.duan1.swing.MyTextField2();
        jLabel5 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDTKH = new javax.swing.JLabel();
        btnHuy = new com.duan1.swing.Button();
        btnThanhToan = new com.duan1.swing.Button();
        lblSDTNN = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblTenNN = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblNgayNhan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtpdf = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lblXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblXacNhan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXacNhan.setText("XÁC NHẬN THÔNG TIN");
        lblXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXacNhanMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Mã khách hàng : ");

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Tên khách hàng :");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Số điện thoại khách hàng :");

        txtTienPhiShip.setBackground(new java.awt.Color(233, 228, 240));
        txtTienPhiShip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTienPhiShipMousePressed(evt);
            }
        });
        txtTienPhiShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienPhiShipKeyReleased(evt);
            }
        });

        txttTienKhachDua.setBackground(new java.awt.Color(233, 228, 240));
        txttTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel5.setText("Tổng tiền SP :");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("0");

        jLabel7.setText("Cần Thanh toán :");

        lblThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblThanhToan.setText("0");

        lblMaKH.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N

        lblTenKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblSDTKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnHuy.setBackground(new java.awt.Color(204, 204, 204));
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Huỷ");
        btnHuy.setToolTipText("");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(0, 242, 96));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lblSDTNN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Số điện thoại :");

        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Địa chỉ người nhận :");

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblTenNN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Tên người nhận : ");

        jLabel18.setText("Tiền thừa :");

        lblTienThua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienThua.setText("0");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("VNĐ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("VNĐ");

        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Ngày nhận");

        lblNgayNhan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtpdf.setColumns(20);
        txtpdf.setRows(5);
        jScrollPane1.setViewportView(txtpdf);

        javax.swing.GroupLayout panelTrang1Layout = new javax.swing.GroupLayout(panelTrang1);
        panelTrang1.setLayout(panelTrang1Layout);
        panelTrang1Layout.setHorizontalGroup(
            panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrang1Layout.createSequentialGroup()
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTrang1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addComponent(lblMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTrang1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel3)
                                .addGap(6, 6, 6)
                                .addComponent(lblTenKH))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTrang1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel4)
                                .addGap(6, 6, 6)
                                .addComponent(lblSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTrang1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(lblXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTrang1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel13)
                                .addGap(6, 6, 6)
                                .addComponent(lblSDTNN, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTrang1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel14)
                                .addGap(6, 6, 6)
                                .addComponent(lblDiaChi))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTrang1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel17)
                                .addGap(12, 12, 12)
                                .addComponent(lblTenNN, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addComponent(txtTienPhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrang1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelTrang1Layout.setVerticalGroup(
            panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrang1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblXacNhan)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3))
                    .addComponent(lblTenKH))
                .addGap(10, 10, 10)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel4))
                    .addComponent(lblSDTKH))
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblTenNN))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)))
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel14))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel13))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblSDTNN)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienPhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txttTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblTongTien)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(panelTrang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(panelTrang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXacNhanMouseClicked

    }//GEN-LAST:event_lblXacNhanMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        try {
            insertHD();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }


    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txttTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttTienKhachDuaKeyReleased

        if (txttTienKhachDua.getText().trim().isEmpty()) {
            lblTienThua.setText("");
            return;
        }

        long tienTT = tienThanhToan;
        try {
            long tienKhachDua = Long.parseLong(txttTienKhachDua.getText());
            long tienTraLai = tienKhachDua - tienTT;
            String tThua = String.format("%,d", tienTraLai);
            if (tienTraLai >= 0) {
                lblTienThua.setText(tThua + "  VNĐ");
            }
            if (txtTienPhiShip.getText().trim().isEmpty()) {
                Msgbox.waring(f, "Không được để trống tiền ship!");
                txtTienPhiShip.requestFocus();

            }
        } catch (NumberFormatException e) {
            Msgbox.waring(new MainJFrame(), "Nhập quá ký tự cho phép!");
            return;
        }
    }//GEN-LAST:event_txttTienKhachDuaKeyReleased

    private void txtTienPhiShipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienPhiShipKeyReleased
        if (txtTienPhiShip.getText().trim().isEmpty()) {
            lblThanhToan.setText(tongTien + "");
            return;
        }
        try {
            long tienTT = tienThanhToan;
            long tienship = Long.parseLong(txtTienPhiShip.getText());

            System.out.println(tongTien);
            if (tienship > 10000 || tienship < 100000) {
                tienThanhToan = tongTien + tienship;
                String tThanhToan = String.format("%,d", tienThanhToan);
                lblThanhToan.setText(tThanhToan + "");

            } else {
                Msgbox.waring(f, "Tiền ship phải (10000 - 100000)");
            }
        } catch (Exception e) {
            Msgbox.waring(f, "Tiền ship phải là số");
            return;
        }
    }//GEN-LAST:event_txtTienPhiShipKeyReleased

    private void txtTienPhiShipMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienPhiShipMousePressed
//        long tienTT = tienThanhToan;
//        long tienship = Long.parseLong(txtTienPhiShip.getText());
//        tienThanhToan = tongTien + tienship;
//        lblThanhToan.setText(tienThanhToan + "");
//        if (txtTienPhiShip.getText().trim().length() == 0) {
//            lblThanhToan.setText(tongTien + "");
//        }
    }//GEN-LAST:event_txtTienPhiShipMousePressed

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
            java.util.logging.Logger.getLogger(JDL_XacNhanThongTin_QuaTang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDL_XacNhanThongTin_QuaTang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDL_XacNhanThongTin_QuaTang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDL_XacNhanThongTin_QuaTang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDL_XacNhanThongTin_QuaTang dialog = new JDL_XacNhanThongTin_QuaTang(new javax.swing.JFrame(), true);
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
    private com.duan1.swing.Button btnHuy;
    private com.duan1.swing.Button btnThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblNgayNhan;
    private javax.swing.JLabel lblSDTKH;
    private javax.swing.JLabel lblSDTNN;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenNN;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblXacNhan;
    private com.duan1.components.panelTrang panelTrang1;
    private com.duan1.swing.MyTextField2 txtTienPhiShip;
    private javax.swing.JTextArea txtpdf;
    private com.duan1.swing.MyTextField2 txttTienKhachDua;
    // End of variables declaration//GEN-END:variables
}
