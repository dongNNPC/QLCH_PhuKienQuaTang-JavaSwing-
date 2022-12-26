/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.duan1.components;

import com.duan1.DAO.HoaDonChiTietDAO;
import com.duan1.DAO.HoaDonDAO;
import com.duan1.DAO.KhachHangDAO;
import com.duan1.DAO.SanPhamDAO;
import com.duan1.Entity.HoaDon;
import com.duan1.Entity.HoaDonChiTiet;
import com.duan1.Entity.KhachHang;
import com.duan1.Entity.SanPham;
import com.duan1.Helper.Auth;
import com.duan1.Helper.Msgbox;
import com.duan1.ui.MainJFrame;
import com.itextpdf.text.DocWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author MSI PC
 */
public class JDL_NhapKhachHang extends javax.swing.JDialog {

    List<HoaDonChiTiet> listHDCT = null;

    List<SanPham> listSP = null;
    List<KhachHang> listKH = new ArrayList<>();

    HoaDonChiTietDAO daoHDCT = new HoaDonChiTietDAO();
    HoaDonDAO daoHD = new HoaDonDAO();
    SanPhamDAO daoSP = new SanPhamDAO();
    KhachHangDAO daoKH = new KhachHangDAO();
    long tongTien = 0;
    long tienThanhToan = 0;
    boolean checkThanhToan = false;
    String MAKH = "";
    String SDT_REG = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
    String HOTEN_REG = "^([A-Za-zỲọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ']+)((\\s{1}[A-Za-zỲọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ']+){1,})$";

    MainJFrame f = new MainJFrame();

    public JDL_NhapKhachHang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setBackground(new Color(0, 0, 0, 0));
        setHint();
        try {
            lblMaKH.setText("KH" + daoKH.initID());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean check() {

        //^([a-z]+)((\s{1}[a-z]+){1,})$
        String TENKH_REGEX = "^([A-Za-zỲọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ']+)((\\s{1}[A-Za-zỲọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ']+){1,})$";

        boolean TENKH = txtTenKH.getText().matches(TENKH_REGEX);
        if (txtTenKH.getText().trim().isEmpty()) {
            Msgbox.waring(f, "Tên khách hàng không được để trống");
            txtTenKH.requestFocus();
            return false;
        }
        if (TENKH != true) {
            Msgbox.waring(f, "Tên khách hàng không đúng định dạng (Nguyễn Văn A)");
            txtTenKH.requestFocus();
            return false;
        }

        //
        String SDTKH_REGEX = "0[35789]{1}\\d{8}$";
        boolean SDTKH = txtSDTKH.getText().matches(SDTKH_REGEX);
//        if (txtSDTKH.getText().trim().isEmpty()) {
//            Msgbox.waring(f, "Số điện thoại không được để trống");
//            txtSDTKH.requestFocus();
//            return false;
//        }
//        if (SDTKH != true) {
//            Msgbox.waring(f, "Số điện thoại không đúng định dạng(chỉ nhập số và gồm 10 chỉ số)");
//            txtSDTKH.requestFocus();
//            return false;
//        }

        //
//        if (txtDiemTichLuy.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Điểm tích lũy không được để trống");
//            txtDiemTichLuy.requestFocus();
//            return false;
//        } else {
//            try {
//                double diem = Integer.parseInt(txtDiemTichLuy.getText());
//                if (diem < 0) {
//                    Msgbox.waring(frame, "Điểm tích lũy phải lớn hơn 0");
//                    txtDiemTichLuy.requestFocus();
//                    return false;
//                }
//            } catch (Exception e) {
//                Msgbox.waring(frame, "Vui lòng nhập điểm tích lũy bằng số");
//                txtDiemTichLuy.requestFocus();
//                return false;
//            }
//        }
        return true;
    }

    public void setHint() {
        txtTenKH.setLabelText("Họ và Tên khách hàng");
        txtSDTKH.setLabelText("Số điện thoại");
        txtTienKhachDua.setLabelText("Tiền khách đưa");
        txtGhiChu.setLabelText("Ghi chú");
        txtpdf.setVisible(false);

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

    public void setTongTienSP(int tongTien) {
        this.tongTien = tongTien;
        tienThanhToan = tongTien;
        lblTongTien.setText(tongTien + "" + " VNĐ");
        lblThanhToan.setText(tienThanhToan + "");
    }

    public List<SanPham> geList(List<SanPham> listSP) {
        return this.listSP = listSP;
    }

    public List<HoaDonChiTiet> getList(List<HoaDonChiTiet> listHDCT) {
        return this.listHDCT = listHDCT;
    }

    public boolean validateForm() {
        boolean check = true;
        if (txtTenKH.getText().trim().isEmpty()) {
            Msgbox.waring(f, "Bạn chưa nhập tên khách hàng!");
            check = false;
        } else {
            if (!txtTenKH.getText().matches(HOTEN_REG)) {
                Msgbox.waring(f, "Tên phải bao gồm Họ và Tên! VD: Nguyễn Văn A");
                check = false;
            }
        }
        if (txtSDTKH.getText().trim().isEmpty()) {
        } else {
            if (!txtSDTKH.getText().matches(SDT_REG)) {
                Msgbox.waring(f, "SĐT không đúng định dạng!");
                check = false;
            }

        }

        if (txtTienKhachDua.getText().trim().isEmpty()) {
            Msgbox.waring(f, "Bạn chưa nhập tiền khách đưa!");
            check = false;
        } else {

            try {
                int so = Integer.parseInt(txtTienKhachDua.getText());
            } catch (Exception e) {
                Msgbox.waring(f, "Tiền khách đưa phải là số!");
                check = false;
            }
            if (txtTienKhachDua.getText().length() > 10) {
                Msgbox.waring(f, "Tiền khách đưa vượt quá kí tự cho phép!");
                check = false;
            }
        }
        return check;
    }

    public void thanhToan() {
        String maKH = "";
        String maHD = "";
        boolean check = true;
        if (!validateForm()) {
            return;
        }
        try {
            maKH = "KH" + daoKH.initID();
            maHD = "HD" + daoHD.initID();

            KhachHang kh = new KhachHang();
            listKH = daoKH.selectAll();
            boolean checkKH = true;
            HoaDon hd = new HoaDon();

            for (KhachHang kH : listKH) {
                if (txtSDTKH.getText().equals(kH.getSDT())) {
                    boolean x = Msgbox.yesNo("Hệ Thống", "Số điện thoại đã được sử dụng với tên người dùng là: " + kH.getTenKH() + " bạn có muốn đặt lại tên người dùng hay không?");
                    if (x) {
                        kh.setTenKH(txtTenKH.getText());
                    } else {
                        kh.setTenKH(kH.getTenKH());
                    }
                    kh.setMaKH(kH.getMaKH());
                    kh.setSDT(kH.getSDT());
                    kh.setDiemTichLuy(0);
                    daoKH.update(kh);
                    hd = new HoaDon(maHD, 0, new Date(), txtGhiChu1.getText(), Auth.tk.getMaNV(), kh.getMaKH());
                    daoHD.insert(hd);
                    checkKH = false;
                }
            }
            if (txtSDTKH.getText().trim().isEmpty()) {
                kh.setTenKH(txtTenKH.getText());
                kh.setMaKH(lblMaKH.getText());
                kh.setSDT("0000000000");
                kh.setDiemTichLuy(0);
                daoKH.insert(kh);
                hd = new HoaDon(maHD, 0, new Date(), txtGhiChu1.getText(), Auth.tk.getMaNV(), kh.getMaKH());
                daoHD.insert(hd);
                checkKH = false;

            }
            if (checkKH) {
                kh = new KhachHang(lblMaKH.getText(), txtTenKH.getText(), txtSDTKH.getText(), 1);
                daoKH.insert(kh);
                System.out.println("Insert");
                hd = new HoaDon(maHD, 0, new Date(), txtGhiChu1.getText(), Auth.tk.getMaNV(), maKH);
                daoHD.insert(hd);
            }

            for (HoaDonChiTiet hdct : listHDCT) {
                try {

                    hdct.setMaHD(hd.getMaHD());
                    daoHDCT.insert(hdct);
                } catch (Exception e) {
                    e.printStackTrace();
                    check = false;
                }
            }

            if (check == false) {
                Msgbox.waring(f, "Đã thêm thất bại!");
                return;
            } else {
                for (SanPham s : listSP) {
                    daoSP.update(s);
                }
                inHoaDon();
                Msgbox.success(f, "Đã thêm thành công!");
                checkThanhToan = true;
            }

            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("./src/com/duan1/icon/sound_ThanhToanThanhCong (1).wav");
        AudioInputStream ad = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(ad);
        clip.start();
    }

    public boolean isSuscess() {
        return checkThanhToan;
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
            SanPham sp = daoSP.selectByid(h.getMaSP());
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
        pdf += "Tổng tiền: " + lblTongTien.getText();
        pdf += "\t\tTiền khách đưa: " + txtTienKhachDua.getText() + " VNĐ" + "\n\n";
        pdf += "Thanh toán: " + lblThanhToan.getText() + " VNĐ";
        pdf += "\t\tTrả lại: " + lblTienTraLai.getText() + " VNĐ";

        txtpdf.setText(pdf);
        txtpdf.print();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtpdf = new javax.swing.JTextArea();
        panelTrang1 = new com.duan1.components.panelTrang();
        txtTenKH = new com.duan1.swing.MyTextField2();
        txtSDTKH = new com.duan1.swing.MyTextField2();
        btnThanhToan = new com.duan1.swing.Button();
        btnHuy = new com.duan1.swing.Button();
        txtGhiChu = new com.duan1.swing.TextAreaScroll();
        txtGhiChu1 = new com.duan1.swing.TextArea();
        jLabel5 = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        txtTienKhachDua = new com.duan1.swing.MyTextField2();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTienTraLai = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();

        txtpdf.setColumns(20);
        txtpdf.setRows(5);
        jScrollPane1.setViewportView(txtpdf);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelTrang1.setBackground(new java.awt.Color(153, 51, 0));

        txtTenKH.setBackground(new java.awt.Color(233, 228, 240));

        txtSDTKH.setBackground(new java.awt.Color(233, 228, 240));

        btnThanhToan.setBackground(new java.awt.Color(0, 242, 96));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(204, 204, 204));
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtGhiChu1.setBackground(new java.awt.Color(233, 228, 240));
        txtGhiChu1.setColumns(20);
        txtGhiChu1.setRows(5);
        txtGhiChu.setViewportView(txtGhiChu1);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Thanh toán:");

        lblThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Tổng tiền:");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtTienKhachDua.setBackground(new java.awt.Color(233, 228, 240));
        txtTienKhachDua.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("VNĐ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("NHẬP THÔNG TIN KHÁCH HÀNG");

        lblTienTraLai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTienTraLai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Trả lại:");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("VND");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(202, 202, 202));
        jLabel2.setText("Mã KH:");

        lblMaKH.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblMaKH.setForeground(new java.awt.Color(202, 202, 202));

        javax.swing.GroupLayout panelTrang1Layout = new javax.swing.GroupLayout(panelTrang1);
        panelTrang1.setLayout(panelTrang1Layout);
        panelTrang1Layout.setHorizontalGroup(
            panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrang1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addComponent(lblThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrang1Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel10))
                                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTrang1Layout.createSequentialGroup()
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrang1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addComponent(jLabel1))
                .addGap(95, 95, 95))
        );
        panelTrang1Layout.setVerticalGroup(
            panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(lblTienTraLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(panelTrang1Layout.createSequentialGroup()
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addGap(19, 19, 19)
                .addGroup(panelTrang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTrang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTrang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        try {
            if (check()) {
                thanhToan();
                music();
                Msgbox.success(f, "Thanh toán thành công!");
                dispose();
            }

        } catch (UnsupportedAudioFileException ex) {

        } catch (IOException ex) {

        } catch (LineUnavailableException ex) {

        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate


    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtTienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyPressed

    }//GEN-LAST:event_txtTienKhachDuaKeyPressed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        if (txtTienKhachDua.getText().trim().isEmpty()) {
            lblTienTraLai.setText("");
            return;
        }
        long tienTT = tienThanhToan;
        try {
            long tienKhachDua = Long.parseLong(txtTienKhachDua.getText());
            long tienTraLai = tienKhachDua - tienTT;
            if (tienTraLai >= 0) {
                lblTienTraLai.setText(tienTraLai + "");
            }
        } catch (NumberFormatException e) {
            Msgbox.waring(new MainJFrame(), "Nhập quá ký tự cho phép!");
            return;
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void txtTienKhachDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaKeyTyped

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
            java.util.logging.Logger.getLogger(JDL_NhapKhachHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDL_NhapKhachHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDL_NhapKhachHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDL_NhapKhachHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDL_NhapKhachHang dialog = new JDL_NhapKhachHang(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienTraLai;
    private javax.swing.JLabel lblTongTien;
    private com.duan1.components.panelTrang panelTrang1;
    private com.duan1.swing.TextAreaScroll txtGhiChu;
    private com.duan1.swing.TextArea txtGhiChu1;
    private com.duan1.swing.MyTextField2 txtSDTKH;
    private com.duan1.swing.MyTextField2 txtTenKH;
    private com.duan1.swing.MyTextField2 txtTienKhachDua;
    private javax.swing.JTextArea txtpdf;
    // End of variables declaration//GEN-END:variables

}
