package com.duan1.components;

import com.duan1.DAO.HoaDonChiTietDAO;
import com.duan1.DAO.HoaDonDAO;
import com.duan1.DAO.KhachHangDAO;
import com.duan1.DAO.NguoiGiaoHangDAO;
import com.duan1.DAO.QuaTangDAO;
import com.duan1.DAO.SanPhamDAO;
import com.duan1.Entity.HoaDon;
import com.duan1.Entity.HoaDonChiTiet;
import com.duan1.Entity.KhachHang;

import com.duan1.Entity.SanPham;
import com.duan1.Entity.TaiKhoan;
import com.duan1.Helper.Auth;
import com.duan1.Helper.Msgbox;
import com.duan1.Helper.XDate;
import static com.duan1.components.Form_QLBanHang.checks;
import com.duan1.swing.Barcodes;
import com.duan1.swing.Dates;
import com.duan1.swing.EventCallBack;
import com.duan1.swing.EventTextField;
import com.duan1.swing.MessageDialog;
import com.duan1.swing.Notification;
import com.duan1.swing.ScrollBarCustom;
import com.duan1.ui.MainJFrame;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.log.Logger;
import com.sun.net.httpserver.Authenticator.Result;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ASUS
 */
public class Form_QuaTang extends javax.swing.JPanel implements Runnable, ThreadFactory {

//    QuaTangDAO daoQT = new QuaTangDAO();
//    KhachHangDAO daoKH = new KhachHangDAO();
//    NguoiGiaoHangDAO daoNGH = new NguoiGiaoHangDAO();
//    HoaDonDAO daoHD = new HoaDonDAO();
//
//    List<QuaTang> listQT = daoQT.selectAll();
//    List<KhachHang> listKH = daoKH.selectAll();
//    List<NguoiGiaoHang> listNGH = daoNGH.selectAll();
//
//    MainJFrame frame = new MainJFrame();
//    MessageDialog mdl = new MessageDialog(frame);
//
//    public int index;
//    DefaultTableModel model;
//
//    String mauNgay = "dd-MM-yyyy";
//    private int MONTH = 1;
//    private int YEAR = 2021;
//    private int DAY = 1;
//    Date date = new Date();
//
//    SanPhamDAO daoSP = new SanPhamDAO();
//    List<SanPham> listSP = null;
//    String sl;
//    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
//    HoaDonChiTietDAO daoHDCT = new HoaDonChiTietDAO();
//    String keyword = "";
//
//    private WebcamPanel panel = null;
//    private Webcam webcam = null;
//    private Executor executor = Executors.newSingleThreadExecutor(this);
//    MainJFrame f = new MainJFrame();
//
//    String maDH = "";
//    String maHD = "";
//
//    String maKH = "";
//
//    public Form_QuaTang() {
//        initComponents();
//        setHint();
//        TimKiem();
//        init();
//        ThanhTruotQT();
//        String keyword = "";
//        initWebcam();
//        hideDelete();
//    }
//
//    public void hideDelete() {
//        if (Auth.role() == false) {
//            btnXoa.setVisible(false);
//        }
//
//    }
//
//    public void init() {
//        listSP = daoSP.selectAll();
//        Scroll_GioHang.setVerticalScrollBar(new ScrollBarCustom());
//        loadData();
//        fillComboboxTrangThai();
//        fillComboboxNGH();
//        fillComboboxTrangThaiGiaoHang();
//        loadDataBH();
//
//        try {
//            maDH = "DH" + daoQT.initID();
//            maHD = "HD" + daoHD.initID();
//            maKH = "KH" + daoKH.initID();
//            txtMaDH.setText(maDH);
//            txtMaHD.setText(maHD);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void initIDForm() {
//        try {
//            maDH = "DH" + daoQT.initID();
//            maHD = "HD" + daoHD.initID();
//            maKH = "KH" + daoKH.initID();
//            txtMaDH.setText(maDH);
//            txtMaHD.setText(maHD);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setHint() {
//        txtTenKH.setLabelText("Tên khách hàng");
//        txtTenNN.setLabelText("Tên người nhận");
//        txtDiaChiNN.setLabelText("Địa chỉ người nhận");
//        txtMaDH.setLabelText("Mã đơn hàng");
//        txtSDTKH.setLabelText("Số điện thoại khách hàng");
//        txtSDTNN.setLabelText("Số điện thoại người nhận");
//        txtNgayGiao.setLabelText("Ngày giao");
//        txtMaHD.setLabelText("Mã hóa đơn");
//        ahihi.setLabelText("Ghi chú");
//        txtMaVach1.setLabelText("Nhập mã vạch");
//
//    }
//
//    public void TimKiem() {
//        txtTimKiem.setHintText("Nhập ");
//        txtTimKiemGH.setHintText("Nhập tên sản phẩm");
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
//        txtTimKiemGH.addEvent(new EventTextField() {
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
//    public void ThanhTruotQT() {
//        ScrollQT.setVerticalScrollBar(new ScrollBarCustom());
//        ScrollQT1.setVerticalScrollBar(new ScrollBarCustom());
//    }
//
//    public void loadData() {
//        DefaultTableModel model = (DefaultTableModel) tblQuaTang.getModel();
//        model.setRowCount(0);
//        listQT = daoQT.selectAll();
//        try {
//
//            for (QuaTang qt : listQT) {
//                NguoiGiaoHang ngh = daoNGH.selectByid(qt.getMaNGH());
//                Object[] row = {
//                    qt.getMaDH(),
//                    qt.getMaHD(),
//                    ngh.getTenNGH(),
//                    qt.getTenNN(),
//                    qt.getDiaChiNN(),
//                    qt.getSDTNN(),
//                    XDate.toString(qt.getNgayGiao(), mauNgay),
//                    qt.getTrangThai(),
//                    qt.getGhiChu()
//                };
//                model.addRow(row);
//            }
//
//        } catch (Exception e) {
//            Notification noti = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.CENTER, "Lỗi thêm dữ liệu vào quà tặng");
//            noti.showNotification();
//        }
//    }
//
//    public void clear() {
//        txtDiaChiNN.setText("");
//        txtGhiChu.setText("");
////        txtMaDH.setText("");
////        txtMaHD.setText("");
//        txtNgayGiao.setText("");
//        txtSDTKH.setText("");
//        txtSDTNN.setText("");
//        txtTenKH.setText("");
//        txtTenNN.setText("");
//        cboNGH.setSelectedIndex(0);
//        cboTrangThai.setSelectedIndex(1);
//
//    }
//
//    public void setForm(QuaTang qt) {
//        HoaDon hd = daoHD.selectByid(qt.getMaHD());
//        KhachHang kh = daoKH.selectByid(hd.getMaKH());
//        NguoiGiaoHang ngh = daoNGH.selectByid(qt.getMaNGH());
//        txtMaDH.setText(qt.getMaDH());
//        txtTenNN.setText(qt.getTenNN());
//        txtDiaChiNN.setText(qt.getDiaChiNN());
//        txtSDTNN.setText(qt.getSDTNN());
//        txtNgayGiao.setText(String.valueOf(XDate.toString(qt.getNgayGiao(), mauNgay)));
//        txtMaHD.setText(qt.getMaHD());
//        txtGhiChu.setText(qt.getGhiChu());
//        txtTenKH.setText(kh.getTenKH());
//        txtSDTKH.setText(kh.getSDT());
//        cboTrangThai.setSelectedItem(qt.getTrangThai());
//        cboNGH.setSelectedItem(ngh.getTenNGH() + " - " + ngh.getMaNGH());
//
////        String s = (String) cboNGH.getSelectedItem();
////        String aa = s.substring(s.length() - 8, s.length());
////        System.out.println(aa);
//    }
//
//    public QuaTang getFormUpdate() {
//        String s = String.valueOf(cboNGH.getSelectedItem());
//        String maNGH = s.substring(s.length() - 8, s.length());
//        QuaTang qt = new QuaTang();
//        qt.setMaDH(txtMaDH.getText());
//        try {
//            KhachHang kh = new KhachHang();
//            listKH = daoKH.selectAll();
//            for (KhachHang khachHang : listKH) {
//                if (khachHang.getSDT().equals(txtSDTKH.getText())) {
//                    kh.setMaKH(khachHang.getMaKH());
//                    kh.setTenKH(txtTenKH.getText());
//                    kh.setSDT(txtSDTKH.getText());
//                    kh.setDiemTichLuy(0);
//                    daoKH.update(kh);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        qt.setMaHD(txtMaHD.getText());
//        qt.setMaNGH(maNGH);
//        qt.setTenNN(txtTenNN.getText());
//        qt.setDiaChiNN(txtDiaChiNN.getText());
//        qt.setSDTNN(txtSDTNN.getText());
//        qt.setNgayGiao(XDate.toDate(txtNgayGiao.getText(), mauNgay));
//        qt.setGhiChu(txtGhiChu.getText());
//        qt.setTrangThai((String) cboTrangThai.getSelectedItem());
//        return qt;
//
//    }
//
//    public void display(int index) {
//        QuaTang qt = listQT.get(index);
//        setForm(qt);
//    }
//
//    public void fillComboboxTrangThai() {
//        String trangThai_GH[] = {"Chưa giao", "Đang giao hàng", "Đã giao xong", "Đã huỷ"};
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTrangThai.getModel();
//        model.removeAllElements();
//
//        for (String string : trangThai_GH) {
//            model.addElement(string);
//        }
//    }
//
//    public void fillComboboxTrangThaiGiaoHang() {
//        String trangThai_GH[] = {"Lọc trạng thái", "Chưa giao", "Đang giao hàng", "Đã giao xong", "Đã huỷ"};
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLocTheoTrangThai.getModel();
//        model.removeAllElements();
//
//        for (String string : trangThai_GH) {
//            model.addElement(string);
//        }
//
//    }
//
//    public void Load_Cbb_TrangTh(String IdAndName) {
//        model = (DefaultTableModel) tblQuaTang.getModel();
//        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
//        tblQuaTang.setRowSorter(trs);
//        trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 7));
//    }
//
//    public void fillComboboxNGH() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNGH.getModel();
//        model.removeAllElements();
//        try {
//            for (NguoiGiaoHang ngh : listNGH) {
//                model.addElement(ngh);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    //tạo nút thêm
//    public void insert() {
//        QuaTang tk = getForm();
//
//        if (tk != null) {
//            try {
//                daoQT.insert(tk);
//                loadData();
//                clear();
//                Notification noti = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Thêm quà tặng thành công");
//                noti.showNotification();
//            } catch (Exception e) {
//                Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Thêm quà tặng không thành công");
//                noti.showNotification();
//            }
//        }
//
//    }
//    public void delete() {
//        if (Msgbox.yesNo("BẠN CÓ CHẮC CHẮN", "muốn xoá đi đơn hàng không")) {
//
//            String maDH = txtMaDH.getText();
//            daoQT.delete(maDH);
//            loadData();
//            clear();
//            Msgbox.success(frame, "Xóa đơn hàng thành công");
//
//        } else {
//            // Msgbox.waring(frame, "Xóa đơn hàng không thành công");
//            return;
//        }
//    }
//
//    public void update() {
//        QuaTang modelQT = getFormUpdate();
//        try {
//            daoQT.update(modelQT);
//            loadData();
//            clear();
//
//        } catch (RuntimeException e) {
//
//        }
//    }
//
//    //cập nhật tình trạng của 4 nút
//    public void updateStatus() {
//        if (index == 0) {
//            btnFirst.setEnabled(false);
//            btnPrev.setEnabled(false);
//        } else {
//            btnFirst.setEnabled(true);
//            btnPrev.setEnabled(true);
//        }
//        if (index == listQT.size() - 1) {
//            btnNext.setEnabled(false);
//            btnLast.setEnabled(false);
//        } else {
//            btnNext.setEnabled(true);
//            btnLast.setEnabled(true);
//        }
//    }
//
//    //lấy giá trị
//    public void edit() {
//        tblQuaTang.setRowSelectionInterval(index, index);
//        display(index);
//        updateStatus();
//    }
//
//    //tạo nút quay lại hàng đầu tiên
//    public void firs() {
//        index = 0;
//        edit();
//    }
//
//    //tạo nút tới hàng cuối cùng
//    public void last() {
//        index = listQT.size() - 1;
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
//        if (index == listQT.size() - 1) {
//            firs();
//        } else {
//            index++;
//        }
//        edit();
//
//    }
//
//    public void findIdAndName(String IdAndName) {
//        model = (DefaultTableModel) tblQuaTang.getModel();
//        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
//        tblQuaTang.setRowSorter(trs);
//        trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 2, 3, 4, 5));
//    }
//    //kiểm trả lỗi
//
//    public boolean check() {
//
//        String Ten_REG = "^([A-Za-zĐỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ']+)((\s{1}[A-Za-zĐỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ']+){1,})$";
//        boolean TenKH = txtTenKH.getText().matches(Ten_REG);
//        boolean TenNN = txtTenNN.getText().matches(Ten_REG);
//
//        String SDT_REG = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})";
//        boolean sdtKH = txtSDTKH.getText().matches(SDT_REG);
//        boolean sdtNN = txtSDTNN.getText().matches(SDT_REG);
//
//        String date_REG = "\\d{2}[-|/]\\\\d{2}[-|/]\\\\d{4}";
//        boolean NgayGiao = txtNgayGiao.getText().matches(date_REG);
//
//        /////////////////////////TenKH/////////////////
//        if (txtTenKH.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Không được để trống tên khách hàng!");
//            txtTenKH.requestFocus();
//            return false;
//        }
//        if (TenKH != true) {
//            Msgbox.waring(frame, "Tên chưa đúng định dạng Vidu: Nguyen Van A!");
//            txtTenKH.requestFocus();
//            return false;
//        }
//        /////////////////////////////SDTKH//////////////////////
//        if (txtSDTKH.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Không được để trống số điện thoại khách hàng!");
//            return false;
//
//        }
//        if (sdtKH != true) {
//            Msgbox.waring(frame, "Số điện thoại chưa đúng định dạng!");
//            txtSDTKH.requestFocus();
//            return false;
//        }
//        /////////////////////////////TENNN//////////////////////
//        if (txtTenNN.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Không được để trống tên người nhận!");
//            txtTenNN.requestFocus();
//            return false;
//
//        }
//        if (TenNN != true) {
//            Msgbox.waring(frame, "Tên chưa đúng định dạng Vidu: Nguyen Van A!");
//            txtTenNN.requestFocus();
//            return false;
//        }
//        /////////////////////////SDTNN////////////////////////////////
//        if (txtSDTNN.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Không được để trống số điện thoại người nhận!");
//            txtSDTNN.requestFocus();
//            return false;
//
//        }
//        if (sdtNN != true) {
//            Msgbox.waring(frame, "Số điện thoại chưa đúng định dạng!");
//            txtSDTNN.requestFocus();
//            return false;
//        }
//
//        //////////////////////////DiaChiNN/////////////////////////////
//        if (txtDiaChiNN.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Không được để trống địa chỉ người nhận!");
//            txtDiaChiNN.requestFocus();
//            return false;
//        }
//        //////////////////////////NgayGiao////////////////////////////
//        if (txtNgayGiao.getText().trim().isEmpty()) {
//            Msgbox.waring(frame, "Không được để trống ngày giao!");
//            txtNgayGiao.requestFocus();
//            return false;
//        }
//        Date presenTime = XDate.toDate(txtNgayGiao.getText(), mauNgay);
//        if (presenTime.before(XDate.now())) {
//            Msgbox.waring(f, "Ngày giao hàng phải sau ngày hôm nay");
//            return false;
//        }
//        /////////////////////////////////////////////////////////////////////
//
//        return true;
//    }
//    ////////////////////////////////////////////Bán hàng////////////////////////////////
//
//    public void updateGHMV(String maVach) {
//        SanPham sp = daoSP.selectByMV(maVach);
//        boolean check = true;
//        for (SanPham s : listSP) {
//            if (s.getMaVach().equalsIgnoreCase(sp.getMaVach())) {
//                if (s.getSoLuong() <= 0) {
//                    Msgbox.waring(frame, "Đã hết hàng !");
//                    return;
//                }
//            }
//        }
//        for (HoaDonChiTiet h : listHDCT) {
//            if (h.getMaSP().equals(sp.getMaSP())) {
//                boolean choice = Msgbox.yesNo("Xác nhận thêm", "Sản phẩm đã được thêm\n Bạn có muốn thêm nữa không?");
//                if (choice) {
//                    break;
//                } else {
//                    return;
//                }
//            }
//        }
//
//        MainJFrame frame = new MainJFrame();
//        String mess = "mã sản phẩm: " + sp.getMaSP() + "\nTên sản phẩm: " + sp.getTenSP() + "\nNhập số lượng: ";
//        sl = JOptionPane.showInputDialog(frame, mess, "Nhập số lượng sản phẩm", JOptionPane.INFORMATION_MESSAGE);
//        if (sl == null) {
//            return;
//        }
//        try {
//            if (sl.trim().isEmpty()) {
//                Msgbox.waring(frame, "Bạn chưa nhập số lượng");
//                return;
//            }
//            int s = Integer.parseInt(sl);
//        } catch (NumberFormatException e) {
//            Msgbox.waring(frame, "Vui lòng nhập số!");
//            return;
//        }
//        if (checkSL()) {
//
//        } else {
//            Msgbox.waring(frame, "Số lượng không đủ!");
//            return;
//        }
//
//        for (HoaDonChiTiet h : listHDCT) {
//            if (h.getMaSP().equals(sp.getMaSP())) {
//                h.setSoLuong(h.getSoLuong() + Integer.parseInt(sl));
//                h.setThanhTien(h.getSoLuong() * sp.getDonGia());
//                check = false;
//                loadDataToGH();
//                break;
//
//            }
//        }
//        for (SanPham s : listSP) {
//            if (s.getMaVach().equalsIgnoreCase(sp.getMaVach())) {
//                s.setSoLuong(s.getSoLuong() - Integer.parseInt(sl));
//                break;
//            }
//        }
//        loadDataToGH();
//        loadDataBH();
//        setTrangThai();
//        if (check) {
//            addDataGH(maVach);
//        }
//    }
//
//    public void addDataGH(String maVach) {
//        SanPham sp = daoSP.selectByMV(maVach);
//        HoaDonChiTiet hdct = new HoaDonChiTiet();
//        hdct.setMaHD(sp.getMaSP());
//        hdct.setMaSP(sp.getMaSP());
//        hdct.setSoLuong(Integer.parseInt(sl));
//        hdct.setThanhTien(hdct.getSoLuong() * sp.getDonGia());
//        listHDCT.add(hdct);
//        loadDataToGH();
//        setTrangThai();
//    }
//
//    public void loadDataToGH() {
//        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
//        model.setRowCount(0);
//        try {
//            int thutu = 0;
//            for (HoaDonChiTiet hdct : listHDCT) {
//                SanPham sp = daoSP.selectByid(hdct.getMaSP());
//                Object[] row = {
//                    thutu++,
//                    hdct.getMaSP(),
//                    sp.getTenSP(),
//                    hdct.getSoLuong(),
//                    sp.getDonGia(),
//                    hdct.getThanhTien()
//                };
//                model.addRow(row);
//                model.fireTableDataChanged();
//            }
//        } catch (Exception e) {
//        }
//    }
//
//    public void loadDataBH() {
//        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
//        model.setRowCount(0);
//        int tt = 0;
//        try {
//            for (SanPham sp : listSP) {
//                Object[] row = {
//                    tt++,
//                    sp.getMaSP(),
//                    sp.getTenSP(),
//                    sp.getSoLuong(),
//                    sp.getDonGia(),
//                    sp.getMaVach(),
//                    sp.getTrangThai()};
//                model.addRow(row);
//            }
//        } catch (Exception e) {
//        }
//    }
//
//    public void deleteBH(int index) {
//        if (listHDCT.isEmpty()) {
//            Msgbox.waring(f, "Không có sản phẩm nào để xóa!");
//            return;
//        }
//        if (index < 0) {
//            Msgbox.waring(f, "Bạn chưa chọn sản phẩm để xóa!");
//            return;
//        }
//        boolean chon = Msgbox.yesNo("Xóa khỏi giỏ hàng", "Bạn có chắc chắn muốn xóa ?");
//        if (chon) {
//            int sl = (int) tblGioHang.getValueAt(index, 3);
//            String maSP = (String) tblGioHang.getValueAt(index, 1);
//            for (SanPham s : listSP) {
//                if (s.getMaSP().equalsIgnoreCase(maSP)) {
//                    s.setSoLuong(s.getSoLuong() + sl);
//                }
//            }
//            listHDCT.remove(index);
//            loadDataToGH();
//            loadDataBH();
//            setTrangThai();
//        }
//
//    }
//
//    public void deleteAllBH() {
//        if (listHDCT.isEmpty()) {
//            Msgbox.waring(f, "Không có sản phẩm nào để xóa!");
//            return;
//        }
//        boolean chon = Msgbox.yesNo("Xóa tất cả giỏ hàng", "Bạn có chắc chắn muốn xóa ?");
//        if (chon) {
//            for (int i = 0; i < tblGioHang.getRowCount(); i++) {
//                int sl = (int) tblGioHang.getValueAt(i, 3);
//                String maSP = (String) tblGioHang.getValueAt(i, 1);
//                for (SanPham s : listSP) {
//                    if (s.getMaSP().equalsIgnoreCase(maSP)) {
//                        s.setSoLuong(s.getSoLuong() + sl);
//                    }
//                }
//            }
//
//            listHDCT.removeAll(listHDCT);
//            loadDataBH();
//            loadDataToGH();
//            setTrangThai();
//        }
//    }
//
//    public void findIdAndNameBH(String IdAndName) {
//        model = (DefaultTableModel) tblSanPham.getModel();
//        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
//        tblSanPham.setRowSorter(trs);
//        trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 2));
//    }
//
//    public void checkDatHang(int index) {
//        if (index < 0) {
//            Msgbox.waring(f, "Bạn chưa có sản phẩm để thanh toán!");
//            return;
//        } else {
//            panelQuaTang.setSelectedIndex(1);
//
//        }
//    }
//
//    public void setTrangThai() {
//        for (SanPham s : listSP) {
//            if (s.getSoLuong() <= 0) {
//                s.setTrangThai("Hết hàng");
//            }
//            if (s.getSoLuong() > 0 && !s.getTrangThai().equalsIgnoreCase("Không còn bán")) {
//                s.setTrangThai("Đang kinh doanh");
//            }
//        }
//        loadDataBH();
//    }
//
//    public boolean checkSL() {
//        int soLuong;
//        if (txtMaVach1.getText().trim().isEmpty()) {
//            soLuong = (int) tblSanPham.getValueAt(index, 3);
//        } else {
//            SanPham s = new SanPham();
//            s = daoSP.selectByMV(txtMaVach1.getText());
//            soLuong = s.getSoLuong();
//        }
//
//        if (soLuong < Integer.parseInt(sl)) {
//            Msgbox.waring(frame, "Số lượng sản phẩm hiện tại không đủ!");
//            return false;
//        }
//        return true;
//    }
//
//    private void initWebcam() {
//        Dimension size = WebcamResolution.VGA.getSize();
//        webcam = Webcam.getWebcams().get(0);
//        webcam.setViewSize(size);
////        webcam.setViewSize(new Dimension(2592, 1944));
//        panel = new WebcamPanel(webcam);
//        panel.setPreferredSize(size);
////        panel.setPreferredSize(new Dimension(2592, 1944));
//        panel.setFPSDisplayed(true);
////2592, 1944
//        lblWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 200));
//
//        executor.execute(this);
//    }

    /**
     *
     */
//    @Override
//    public void run() {
//
//        boolean i = true;
//        do {
//            try {
//                Thread.sleep(100);
//
//            } catch (InterruptedException ex) {
////                java.util.logging.Logger.getLogger(com.duan1.components.Form_QLBanHang.class
////                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            }
//            com.google.zxing.Result result = null;
//            BufferedImage image = null;
//            if (webcam.isOpen()) {
//                if ((image = webcam.getImage()) == null) {
//                    continue;
//                }
//            }
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//            try {
//                result = new MultiFormatReader().decode(bitmap);
//            } catch (NotFoundException ex) {
////                Logger.getLogger(com.duan1.components.MyWebCam.class.getName()).log(Level.SEVERE, null, ex);
//                if (checks == 2) {
//                    List<SanPham> s = new ArrayList<>();
//                    s = daoSP.selectAll();
//                    for (SanPham sanPham : s) {
//                        if (sanPham.getMaVach().equals(txtMaVach1.getText())) {
//                            updateGHMV(txtMaVach1.getText());
//                            txtMaVach1.setText("");
//                        }
//                    }
//                }
//                if (checks == 0 || checks == 1) {
//                    webcam.close();
//                    return;
//                }
//            }
//            if (result != null) {
//                txtMaVach1.setText(result.getText());
//            }
//            if (txtMaVach1.getText().length() > 0) {
//                try {
//                    Desktop.getDesktop().browse(new URI(txtMaVach1.getText()));
//                    i = true;
//                } catch (IOException | URISyntaxException ex) {
////                    i = true;
//                }
//            }
//        } while (i);
//    }
//
//    @Override
//    public Thread newThread(Runnable r) {
//        Thread t = new Thread(r, "My Thread");
//        t.setDaemon(true);
//        return t;
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.duan1.swing.DateChooser();
        panelQuaTang = new com.duan1.swing.MaterialTabbed();
        pnlBanHang = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Tabpane1 = new com.duan1.swing.MaterialTabbed();
        pnlDanhSach1 = new javax.swing.JPanel();
        Scroll_GioHang = new javax.swing.JScrollPane();
        tblGioHang = new com.duan1.swing.Table();
        btnDatHang = new com.duan1.swing.Button();
        btnDatHang1 = new com.duan1.swing.Button();
        btnXoaKhoiGio = new com.duan1.swing.Button();
        txtTimKiemGH = new com.duan1.swing.TextFieldAnimation();
        Tabpane3 = new com.duan1.swing.MaterialTabbed();
        pnlDanhSach3 = new javax.swing.JPanel();
        ScrollQT1 = new javax.swing.JScrollPane();
        tblSanPham = new com.duan1.swing.Table();
        jPanel1 = new javax.swing.JPanel();
        lblWebcam = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtMaVach1 = new com.duan1.swing.MyTextField2();
        btnRead = new com.duan1.swing.Button();
        pnlCapNhat = new javax.swing.JPanel();
        btn = new javax.swing.JPanel();
        pnlDieuHuong = new javax.swing.JPanel();
        btnFirst = new com.duan1.swing.Button();
        btnPrev = new com.duan1.swing.Button();
        btnNext = new com.duan1.swing.Button();
        btnLast = new com.duan1.swing.Button();
        ahihi = new com.duan1.swing.TextAreaScroll();
        txtGhiChu = new com.duan1.swing.TextArea();
        cboTrangThai = new com.duan1.swing.ComboBoxSuggestion();
        cboNGH = new com.duan1.swing.ComboBoxSuggestion();
        txtTenNN = new com.duan1.swing.MyTextField2();
        txtDiaChiNN = new com.duan1.swing.MyTextField2();
        txtMaDH = new com.duan1.swing.MyTextField2();
        txtSDTKH = new com.duan1.swing.MyTextField2();
        txtSDTNN = new com.duan1.swing.MyTextField2();
        txtNgayGiao = new com.duan1.swing.MyTextField2();
        txtMaHD = new com.duan1.swing.MyTextField2();
        txtTenKH = new com.duan1.swing.MyTextField2();
        btnMoi = new com.duan1.swing.Button();
        btnXoa = new com.duan1.swing.Button();
        btnThem = new com.duan1.swing.Button();
        btnSua = new com.duan1.swing.Button();
        pnlDanhSach = new javax.swing.JPanel();
        ScrollQT = new javax.swing.JScrollPane();
        tblQuaTang = new com.duan1.swing.Table();
        cboLocTheoTrangThai = new com.duan1.swing.ComboBoxSuggestion();
        txtTimKiem = new com.duan1.swing.TextFieldAnimation();

        dateChooser1.setForeground(new java.awt.Color(28, 181, 224));
        dateChooser1.setDateFormat("dd-MM-yyyy");
        dateChooser1.setTextRefernce(txtNgayGiao);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(923, 604));
        setMinimumSize(new java.awt.Dimension(923, 604));
        setPreferredSize(new java.awt.Dimension(923, 604));

        panelQuaTang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelQuaTangMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        pnlDanhSach1.setBackground(new java.awt.Color(255, 255, 255));

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
        if (tblGioHang.getColumnModel().getColumnCount() > 0) {
            tblGioHang.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout pnlDanhSach1Layout = new javax.swing.GroupLayout(pnlDanhSach1);
        pnlDanhSach1.setLayout(pnlDanhSach1Layout);
        pnlDanhSach1Layout.setHorizontalGroup(
            pnlDanhSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSach1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Scroll_GioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlDanhSach1Layout.setVerticalGroup(
            pnlDanhSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSach1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Scroll_GioHang, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        Tabpane1.addTab("Giỏ Hàng", pnlDanhSach1);

        btnDatHang.setText("Xóa tất cả");
        btnDatHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        btnDatHang1.setText("Đặt hàng");
        btnDatHang1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDatHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHang1ActionPerformed(evt);
            }
        });

        btnXoaKhoiGio.setText("Xóa khỏi giỏ");
        btnXoaKhoiGio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaKhoiGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoiGioActionPerformed(evt);
            }
        });

        txtTimKiemGH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimKiemGHMousePressed(evt);
            }
        });
        txtTimKiemGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemGHActionPerformed(evt);
            }
        });
        txtTimKiemGH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemGHKeyReleased(evt);
            }
        });

        pnlDanhSach3.setBackground(new java.awt.Color(255, 255, 255));

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
        ScrollQT1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout pnlDanhSach3Layout = new javax.swing.GroupLayout(pnlDanhSach3);
        pnlDanhSach3.setLayout(pnlDanhSach3Layout);
        pnlDanhSach3Layout.setHorizontalGroup(
            pnlDanhSach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 891, Short.MAX_VALUE)
            .addGroup(pnlDanhSach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSach3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ScrollQT1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlDanhSach3Layout.setVerticalGroup(
            pnlDanhSach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(pnlDanhSach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDanhSach3Layout.createSequentialGroup()
                    .addComponent(ScrollQT1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Tabpane3.addTab("Sản Phẩm Bán", pnlDanhSach3);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "WebCam", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(27, 178, 222))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(300, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 300));

        lblWebcam.setBorder(new javax.swing.border.MatteBorder(null));
        lblWebcam.setMaximumSize(new java.awt.Dimension(200, 200));
        lblWebcam.setMinimumSize(new java.awt.Dimension(200, 200));
        lblWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtMaVach1.setEditable(false);
        txtMaVach1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaVach1ActionPerformed(evt);
            }
        });
        txtMaVach1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaVach1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaVach1KeyReleased(evt);
            }
        });

        btnRead.setText("Đọc");
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtMaVach1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRead, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMaVach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnXoaKhoiGio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnDatHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Tabpane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Tabpane3, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemGH, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Tabpane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaKhoiGio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDatHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(txtTimKiemGH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Tabpane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 927, Short.MAX_VALUE)
            .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBanHangLayout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
            .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, Short.MAX_VALUE))
        );

        panelQuaTang.addTab("Bán hàng", pnlBanHang);

        pnlCapNhat.setBackground(new java.awt.Color(255, 255, 255));

        btn.setBackground(new java.awt.Color(255, 255, 255));

        pnlDieuHuong.setBackground(new java.awt.Color(255, 255, 255));
        pnlDieuHuong.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnFirst.setText("<");
        btnFirst.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnFirst);

        btnPrev.setText("|<");
        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnPrev);

        btnNext.setText(">|");
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnNext);

        btnLast.setText(">");
        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnLast);

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        ahihi.setViewportView(txtGhiChu);

        cboTrangThai.setToolTipText("");
        cboTrangThai.setDoubleBuffered(true);

        txtMaDH.setEditable(false);
        txtMaDH.setBackground(new java.awt.Color(255, 255, 255));

        txtMaHD.setEditable(false);
        txtMaHD.setBackground(new java.awt.Color(255, 255, 255));

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

        btnThem.setText("Xác nhận");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnLayout = new javax.swing.GroupLayout(btn);
        btn.setLayout(btnLayout);
        btnLayout.setHorizontalGroup(
            btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(btnLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(pnlDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ahihi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, btnLayout.createSequentialGroup()
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDTNN, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNN, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(113, 113, 113)
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaDH, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(cboNGH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChiNN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayGiao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        btnLayout.setVerticalGroup(
            btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLayout.createSequentialGroup()
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btnLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtDiaChiNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDTNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNGH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ahihi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDieuHuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelQuaTang.addTab("Cập Nhật", pnlCapNhat);

        pnlDanhSach.setBackground(new java.awt.Color(255, 255, 255));

        ScrollQT.setBackground(new java.awt.Color(255, 255, 255));
        ScrollQT.setBorder(null);

        tblQuaTang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DH", "Mã HD", "Tên NGH", "Tên NN", "Địa chỉ NN", "SĐT NN", "Ngày giao", "Trạng thái", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuaTang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuaTangMouseClicked(evt);
            }
        });
        ScrollQT.setViewportView(tblQuaTang);

        cboLocTheoTrangThai.setEditable(false);
        cboLocTheoTrangThai.setToolTipText("112");
        cboLocTheoTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocTheoTrangThaiActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap(458, Short.MAX_VALUE)
                .addComponent(cboLocTheoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addComponent(ScrollQT)
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLocTheoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollQT, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );

        panelQuaTang.addTab("Danh Sách", pnlDanhSach);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelQuaTang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelQuaTang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblQuaTangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuaTangMouseClicked

//        if (evt.getClickCount() == 2) {
//
//            int sec = tblQuaTang.getSelectedRow();
//            display(sec);
//            panelQuaTang.setSelectedIndex(1);
//            btnSua.setVisible(true);
//            btnXoa.setVisible(true);
//            btnThem.setVisible(false);
//            hideDelete();
//
//        }
    }//GEN-LAST:event_tblQuaTangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
//
//        JDL_XacNhanThongTin_QuaTang xn = new JDL_XacNhanThongTin_QuaTang(frame, true);
//        Form_QLBanHang.checks = 0;
//        boolean ss = false;
//        String s = String.valueOf(cboNGH.getSelectedItem());
//        String maNGH = s.substring(s.length() - 8, s.length());
//
//        if (check()) {
//            listKH = daoKH.selectAll();
//            for (KhachHang kh : listKH) {
//                if (kh.getSDT().equalsIgnoreCase(txtSDTKH.getText())) {
//                    xn.seta(kh.getMaKH(), txtTenKH.getText(), txtSDTKH.getText(), txtTenNN.getText(), txtDiaChiNN.getText(), txtSDTNN.getText(), txtNgayGiao.getText(), String.valueOf(cboTrangThai.getSelectedItem()), maNGH, txtMaHD.getText());
//                    ss = true;
//                    break;
//                }
//            }
//            if (ss == false) {
//                try {
//                    xn.seta("KH" + daoKH.initID(), txtTenKH.getText(), txtSDTKH.getText(), txtTenNN.getText(), txtDiaChiNN.getText(), txtSDTNN.getText(), txtNgayGiao.getText(), String.valueOf(cboTrangThai.getSelectedItem()), maNGH, txtMaHD.getText());
//                    System.out.println(daoKH.initID());
//                    System.out.println("ss false");
//                } catch (SQLException ex) {
//                    java.util.logging.Logger.getLogger(Form_QuaTang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                }
//            }
//
//            int tongTien = 0;
//            for (HoaDonChiTiet hdct : listHDCT) {
//                tongTien += hdct.getThanhTien();
//            }
//            xn.geList(listSP);
//            xn.setTongTienSP(tongTien);
//            xn.getList(listHDCT);
//            xn.setVisible(true);
//
//        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
//        int vitri = tblQuaTang.getSelectedRow();
//        if (vitri >= 0) {
//            update();
//
//        } else {
//            Msgbox.waring(frame, "Bạn chưa chọn đơn hàng nào để cập nhật");
//        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
//        int vitri = tblQuaTang.getSelectedRow();
//        if (vitri >= 0) {
//            delete();
//
//        } else {
//            Msgbox.waring(frame, "Bạn chưa chọn đơn hàng nào để xoá");
//        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
//        clear();
//        loadData();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        firs();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        String timKiem = txtTimKiem.getText();
        findIdAndName(timKiem);
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMousePressed
        String timKiem = txtTimKiem.getText();
        findIdAndName(timKiem);
    }//GEN-LAST:event_txtTimKiemMousePressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String timKiem = txtTimKiem.getText();
        findIdAndName(timKiem);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cboLocTheoTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocTheoTrangThaiActionPerformed
        String load = (String) cboLocTheoTrangThai.getSelectedItem();
        if (load == "Lọc trạng thái") {
            load = "";//quay lại ban đầu
        }
        Load_Cbb_TrangTh(load);
    }//GEN-LAST:event_cboLocTheoTrangThaiActionPerformed

    private void txtTimKiemGHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemGHKeyReleased
        String timKiem = txtTimKiemGH.getText();
        findIdAndNameBH(timKiem);
    }//GEN-LAST:event_txtTimKiemGHKeyReleased

    private void txtTimKiemGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemGHActionPerformed
        String timKiem = txtTimKiemGH.getText();
        findIdAndNameBH(timKiem);
    }//GEN-LAST:event_txtTimKiemGHActionPerformed

    private void txtTimKiemGHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemGHMousePressed
        // TODO add your handling code here:
        String timKiem = txtTimKiemGH.getText();
        findIdAndNameBH(timKiem);
    }//GEN-LAST:event_txtTimKiemGHMousePressed

    private void btnXoaKhoiGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoiGioActionPerformed
        index = tblGioHang.getSelectedRow();
        deleteBH(index);
    }//GEN-LAST:event_btnXoaKhoiGioActionPerformed

    private void btnDatHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHang1ActionPerformed

        if (listHDCT.isEmpty()) {
            Msgbox.waring(frame, "Bạn chưa có đơn hàng nào để thanh toán");
            return;
        } else {
            initIDForm();
            panelQuaTang.setSelectedIndex(1);
            btnSua.setVisible(false);
            btnXoa.setVisible(false);
            btnThem.setVisible(true);
        }
    }//GEN-LAST:event_btnDatHang1ActionPerformed

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        deleteAllBH();
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

        index = tblSanPham.getSelectedRow();
        String mv = (String) tblSanPham.getValueAt(index, 5);
        //        txtMaVach.setText(mv);
        if (evt.getClickCount() == 2) {
            updateGHMV(mv);
        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamMouseEntered

    private void txtMaVach1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaVach1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(f, "ss");
    }//GEN-LAST:event_txtMaVach1ActionPerformed

    private void txtMaVach1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaVach1KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            List<SanPham> s = new ArrayList<>();
            for (SanPham sanPham : s) {
                if (sanPham.getMaVach().equals(txtMaVach1.getText())) {

                    updateGHMV(txtMaVach1.getText());
                    return;
                }
            }
            Msgbox.waring(frame, "Mã vạch không tồn tại!");
        }
    }//GEN-LAST:event_txtMaVach1KeyPressed

    private void txtMaVach1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaVach1KeyReleased

    }//GEN-LAST:event_txtMaVach1KeyReleased

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        Barcodes s = new Barcodes();
        s.Read_IMG(f, txtMaVach1);
        loadDataToGH();
    }//GEN-LAST:event_btnReadActionPerformed

    private void panelQuaTangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelQuaTangMouseClicked
        // TODO add your handling code here:
        System.out.println(panelQuaTang.getSelectedIndex());
        if (panelQuaTang.getSelectedIndex() == 2) {
            loadData();
        }
    }//GEN-LAST:event_panelQuaTangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollQT;
    private javax.swing.JScrollPane ScrollQT1;
    private javax.swing.JScrollPane Scroll_GioHang;
    private com.duan1.swing.MaterialTabbed Tabpane1;
    private com.duan1.swing.MaterialTabbed Tabpane3;
    private com.duan1.swing.TextAreaScroll ahihi;
    private javax.swing.JPanel btn;
    private com.duan1.swing.Button btnDatHang;
    private com.duan1.swing.Button btnDatHang1;
    private com.duan1.swing.Button btnFirst;
    private com.duan1.swing.Button btnLast;
    private com.duan1.swing.Button btnMoi;
    private com.duan1.swing.Button btnNext;
    private com.duan1.swing.Button btnPrev;
    private com.duan1.swing.Button btnRead;
    private com.duan1.swing.Button btnSua;
    private com.duan1.swing.Button btnThem;
    private com.duan1.swing.Button btnXoa;
    private com.duan1.swing.Button btnXoaKhoiGio;
    private com.duan1.swing.ComboBoxSuggestion cboLocTheoTrangThai;
    private com.duan1.swing.ComboBoxSuggestion cboNGH;
    private com.duan1.swing.ComboBoxSuggestion cboTrangThai;
    private com.duan1.swing.DateChooser dateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel lblWebcam;
    private com.duan1.swing.MaterialTabbed panelQuaTang;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlDanhSach1;
    private javax.swing.JPanel pnlDanhSach3;
    private javax.swing.JPanel pnlDieuHuong;
    private com.duan1.swing.Table tblGioHang;
    private com.duan1.swing.Table tblQuaTang;
    private com.duan1.swing.Table tblSanPham;
    private com.duan1.swing.MyTextField2 txtDiaChiNN;
    private com.duan1.swing.TextArea txtGhiChu;
    private com.duan1.swing.MyTextField2 txtMaDH;
    private com.duan1.swing.MyTextField2 txtMaHD;
    private com.duan1.swing.MyTextField2 txtMaVach1;
    private com.duan1.swing.MyTextField2 txtNgayGiao;
    private com.duan1.swing.MyTextField2 txtSDTKH;
    private com.duan1.swing.MyTextField2 txtSDTNN;
    private com.duan1.swing.MyTextField2 txtTenKH;
    private com.duan1.swing.MyTextField2 txtTenNN;
    private com.duan1.swing.TextFieldAnimation txtTimKiem;
    private com.duan1.swing.TextFieldAnimation txtTimKiemGH;
    // End of variables declaration//GEN-END:variables
}
