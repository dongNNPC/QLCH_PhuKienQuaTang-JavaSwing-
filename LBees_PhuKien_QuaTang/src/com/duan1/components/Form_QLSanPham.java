package com.duan1.components;

import com.duan1.DAO.DanhMucDAO;
import com.duan1.DAO.HoaDonChiTietDAO;
import com.duan1.DAO.PhieuNhapChiTietDAO;
import com.duan1.DAO.PhieuNhapKhoDAO;
import com.duan1.DAO.SanPhamDAO;
import com.duan1.Entity.DanhMuc;
import com.duan1.Entity.HoaDon;
import com.duan1.Entity.HoaDonChiTiet;
import com.duan1.Entity.PhieuNhapChiTiet;
import com.duan1.Entity.PhieuNhapKho;
import com.duan1.Entity.SanPham;
import com.duan1.Helper.Auth;
import com.duan1.Helper.ImgHelper;
import com.duan1.Helper.Msgbox;
import com.duan1.Helper.XDate;
import com.duan1.swing.ScrollBarCustom;
import com.duan1.ui.MainJFrame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ASUS
 */
public class Form_QLSanPham extends javax.swing.JPanel {

    SanPhamDAO daoSP = new SanPhamDAO();
    DanhMucDAO daoDM = new DanhMucDAO();
    List<DanhMuc> listDM = new ArrayList<>();
    List<SanPham> listSP = new ArrayList<>();
    MainJFrame frame = new MainJFrame();
    HoaDonChiTietDAO daoHDCT = new HoaDonChiTietDAO();
    PhieuNhapKhoDAO daoPNK = new PhieuNhapKhoDAO();
    PhieuNhapChiTietDAO daoPNK_ct = new PhieuNhapChiTietDAO();
    int index = -1;
    int i = -1;

    /**
     * Creates new form Form_QLSanPham
     */
    public Form_QLSanPham() {
        initComponents();
//        init();
//        hideDelete();
    }

    String maPNK = "";
    public static boolean checkBtn = true;
//
//    public void hideDelete() {
//        if (Auth.role() == false) {
//            btnXoa.setVisible(false);
//        }
//
//    }

//    public void init() {
//        updateStatus();
//        scroll_SPBan.setVerticalScrollBar(new ScrollBarCustom());
//        scroll_PhieuNK.setVerticalScrollBar(new ScrollBarCustom());
//        setHint();
//        fillComboBox();
//        loadDaTa();
//        Tabpane.setSelectedIndex(1);
//        try {
//            if (checkBtn) {
//                maPNK = "";
//                lblMaPNK.setText(maPNK);
//            } else {
//                maPNK = daoPNK.getLastID();
//                lblMaPNK.setText(maPNK);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        txtHinh.setVisible(false);
//    }

    public void setHint() {
        txtTimKiem.setHintText("Nhập tên, mã sản phẩm");
        txtMaSP.setHint("Mã sản phẩm");
        txtTenSP.setHint("Tên sản phẩm");
        txtSoLuong.setHint("Số lượng");
        txtDonGia.setHint("Đơn giá");
    }

    void loadDaTa() {
        int stt = 0;
        listSP = daoSP.selectAll();
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        DefaultTableModel tblDsPhieuNhap = (DefaultTableModel) tblPhieuNhapKho.getModel();
        model.setRowCount(0);
        tblDsPhieuNhap.setRowCount(0);
        try {
            for (SanPham sp : listSP) {
                Object[] row = {
                    stt++,
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getSoLuongTonKho(),
                    sp.getHinhAnh(),
                    sp.getTrangThai()
                };
                model.addRow(row);
            }
            List<Object[]> listPN = daoPNK_ct.getSoLuong_TongTien();
            i = -1;
            for (Object[] row : listPN) {
                tblDsPhieuNhap.addRow(row);
                i++;
                chuyenNgay(String.valueOf(tblPhieuNhapKho.getValueAt(i, 1)),i);
            }
        } catch (Exception e) {
            Msgbox.waring(new MainJFrame(), "Lỗi đổ dữ liệu!");
        }
    }

    void chuyenNgay(String str, int index) {
        Date ds = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ds = sf.parse(str);
            str = XDate.toString(ds, "dd-MM-yyyy");
            tblPhieuNhapKho.setValueAt(str, index, 1);
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.waring(new MainJFrame(), "Lỗi chuyển ngày");
        }
    }

    void fillComboBox() {
        listDM = daoDM.selectAll();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDanhMuc.getModel();
        model.removeAllElements();
        model.addElement("Danh mục");
        try {
            for (DanhMuc d : listDM) {
                model.addElement(d.getMaDM() + " - " + d.getTenDM());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPham getForm() {
//        String maDM = (String) cboDanhMuc.getSelectedItem();
//        maDM = maDM.substring(0, 7);
//        SanPham s = new SanPham();
//        s.setMaSP(txtMaSP.getText());
//        s.setTenSP(txtTenSP.getText());
//        s.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
//        s.setDonGia(Float.parseFloat(txtDonGia.getText()));
//        s.setDonViTinh(String.valueOf(cboDonViTinh.getSelectedItem()));
//        s.setTrangThai(String.valueOf(cboTrangThai.getSelectedItem()));
//        s.setMaDM(maDM);
//        s.setMaVach(txtMaSP.getText());
//        s.setMaNV("NV00001");
//        s.setHinhAnh(txtHinh.getText());

        SanPham s = new SanPham();
//        s.setMaSP(maPNK);
        return s;
    }

    public void setForm(SanPham s) {

//        txtMaSP.setText(s.getMaSP());
//        txtTenSP.setText(s.getTenSP());
//        txtSoLuong.setText(String.valueOf(s.getSoLuong()));
//        txtDonGia.setText(String.valueOf(s.getDonGia()));
//        cboDonViTinh.setSelectedItem(s.getDonViTinh());
//        DanhMuc d = daoDM.selectByid(s.getMaDM());
//        cboDanhMuc.setSelectedItem(d.getMaDM() + " - " + d.getTenDM());
//        cboTrangThai.setSelectedItem(s.getTrangThai());

    }

//    public void clear() {
//
//        txtMaSP.setText("");
//        txtTenSP.setText("");
//        txtSoLuong.setText("");
//        txtDonGia.setText("");
//        cboDonViTinh.setSelectedIndex(0);
//        cboDanhMuc.setSelectedIndex(0);
//        cboTrangThai.setSelectedIndex(0);
//        lblMaPNK.setText("");
//        txtHinh.setText("");
//        index = - 1;
//        updateStatus();
//    }

    public void taoPhieuNhapKho() {
//        boolean choice = Msgbox.yesNo("Tạo phiếu nhập kho", "Bạn có chắc chắn muốn tạo phiếu nhập");
//        if (!choice) {
//            return;
//        }
//        try {
//            maPNK = "MP" + daoPNK.initID();
//            lblMaPNK.setText(maPNK);
//            PhieuNhapKho pnk = new PhieuNhapKho(maPNK, new Date());
//            daoPNK.insert(pnk);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        checkBtn = false;
//        btnThemMNK.setEnabled(checkBtn);

    }

    public void ketThucLanNhapKho() {
//        boolean choice = Msgbox.yesNo("Kết thúc lần nhập kho", "Bạn có chắn chắn muốn kế thúc lần nhập!");
//        if (!choice) {
//            return;
//        }
//        maPNK = "";
//        lblMaPNK.setText(maPNK);
//        checkBtn = true;
//        btnThemMNK.setEnabled(checkBtn);
    }

//    public boolean validateForm() {
//        MainJFrame f = new MainJFrame();
//        boolean check = true;
//        // Mã sản phẩm
//
//        if (txtMaSP.getText().trim().isEmpty()) {
//            Msgbox.waring(f, "Vui lòng nhập mã SP!");
//            check = false;
//        } else {
//            if (txtMaSP.getText().length() > 10) {
//                Msgbox.waring(f, "Mã sản phẩm vượt quá số lượng cho phép!");
//                check = false;
//            }
//        }
//
//        if (txtTenSP.getText().trim().isEmpty()) {
//            Msgbox.waring(f, "Vui lòng nhập tên SP!");
//            check = false;
//        }
////        if (txtHinh.getText().trim().isEmpty()) {
////            Msgbox.waring(f, "Vui lòng chọn ảnh!");
////            check = false;
////        }
//        if (txtSoLuong.getText().trim().isEmpty()) {
//            Msgbox.waring(f, "Vui lòng nhập số lượng!");
//            check = false;
//        } else {
//
//            try {
//                int sl = Integer.parseInt(txtSoLuong.getText());
//                if (sl < 0) {
//                    Msgbox.waring(f, "Số lượng phải là số dương!");
//                    check = false;
//                }
//            } catch (Exception e) {
//                Msgbox.waring(f, "Vui lòng nhập số lượng là số!");
//                check = false;
//            }
//        }
//        if (txtDonGia.getText().trim().isEmpty()) {
//            Msgbox.waring(f, "Vui lòng nhập đơn giá!");
//            check = false;
//        } else {
//            try {
//                double dg = Double.parseDouble(txtDonGia.getText());
//                if (dg < 0) {
//                    Msgbox.waring(f, "Đơn giá phải là số dương!");
//                    check = false;
//                }
//            } catch (Exception e) {
//                Msgbox.waring(f, "Vui lòng nhập đơn giá là số!");
//                check = false;
//            }
//        }
//
//        if (cboDanhMuc.getSelectedIndex() == 0) {
//            Msgbox.waring(f, "Vui lòng chọn danh mục!");
//            check = false;
//        }
//        if (cboDonViTinh.getSelectedIndex() == 0) {
//            Msgbox.waring(f, "Vui lòng chọn đơn vị tính!");
//            check = false;
//        }
//        if (cboTrangThai.getSelectedIndex() == 0) {
//            Msgbox.waring(f, "Vui lòng chọn trạng thái!");
//            check = false;
//        }
//        return check;
//
//    }

//    public void insert() {
//
//        if (maPNK.trim().isEmpty()) {
//            Msgbox.waring(new MainJFrame(), "Bạn chưa tạo mã nhập kho!");
//            return;
//        }
//        if (!validateForm()) {
//            return;
//        }
//        try {
//            SanPham s = new SanPham();
//            SanPham sp = daoSP.selectByid(s.getMaSP());
//            s = getForm();
//            //trùng nhau cập nhật số lượng
//            if (sp != null) {
//                boolean choice = Msgbox.yesNo("Cập nhật số lượng", "Sản phẩm đã tồn tại! \nBạn có muốn cập nhật lại số lượng không?");
//                if (!choice) {
//                    return;
//                }
//                String sl_new = JOptionPane.showInputDialog(new MainJFrame(), "Mời nhập số lượng!");
//                try {
//                    daoSP.updateSL(s.getMaSP(), Integer.parseInt(sl_new) + sp.getSoLuong());
//                    Msgbox.success(new MainJFrame(), "Cập nhật thành công!");
//                } catch (Exception e) {
//                    Msgbox.waring(new MainJFrame(),
//                            "Lỗi cập nhật dữ liệu khi thêm!");
//                }
//                update_SLSP(sp);
//            } else {
//                daoSP.insert(s);
//                PhieuNhapChiTiet pnct;
//                pnct = new PhieuNhapChiTiet(maPNK, s.getMaSP(), s.getSoLuong(), s.getDonGia());
//                daoPNK_ct.insert(pnct);
//                ImgHelper.Write(s.getMaSP());
//                Msgbox.success(frame, "Thêm thành công sản phẩm !");
//            }
//            loadDaTa();
//            clear();
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public void Update() {
//        if (!validateForm()) {
//            return;
//        }
//        try {
//            SanPham s = new SanPham();
//            s = getForm();
//            daoSP.update(s);
//            Msgbox.success(frame, "Cập nhật thành công sản phẩm !");
//            clear();
//            loadDaTa();
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//
//    }
//
//    private void updateStatus() {
//        boolean edit = (this.index >= 0);
//        boolean first = (this.index == 0);
//        boolean last = (this.index == tblSanPham.getRowCount() - 1);
//
//        txtMaSP.setEditable(!edit);
//        btnThem.setEnabled(!edit);
//        btnSua.setEnabled(edit);
//        btnXoa.setEnabled(edit);
//
//        btnFirst.setEnabled(edit && !first);
//        btnPrev.setEnabled(edit && !first);
//        btnNext.setEnabled(edit && !last);
//        btnLast.setEnabled(edit && !last);
//
//        btnThemMNK.setVisible(!edit);
//        btnketThucLanNhapKho.setVisible(!edit);
//        lblMaPNK.setVisible(!edit);
//        lblTieuDeMaPNK.setVisible(!edit);
//    }
//
//    public void delete() {
//        if (txtMaSP.getText().trim().isEmpty()) {
//            Msgbox.waring(new MainJFrame(), "Bạn chưa chọn sản phẩm để xóa!");
//            return;
//        }
//        try {
//            if (Msgbox.yesNo("bạn có muốn xóa", "bạn chắc chắn không???")) {
//                daoSP.delete(txtMaSP.getText());
//                loadDaTa();
//                Msgbox.success(frame, "Xóa thành công!");
//                clear();
//            }
//        } catch (Exception ex) {
//            Msgbox.waring(frame, "Không thể xóa do dữ liệu cũng được lưu ở nơi khác");
//        }
//
//    }
//
//    public void display(int index) {
//        SanPham s = listSP.get(index);
//        setForm(s);
//        ImgHelper m = new ImgHelper();
//        m.loadHinhVaoForm(s.getHinhAnh(), lblHinh);
//        updateStatus();
//
//    }
//
//    public void edit() {
//        tblSanPham.setRowSelectionInterval(index, index);
//
//    }
//    //fist
//
//    public void first() {
//        index = 0;
//        Msgbox.info(new MainJFrame(), "Bạn đang ở đầu danh sách");
//        display(index);
//    }
//
//    //end
//    public void last() {
//        index = listSP.size() - 1;
//        Msgbox.info(new MainJFrame(), "Bạn đang ở cuối danh sách");
//        display(index);
//    }
//
//    //prev
//    public void prev() {
//        if (index > 0) {
//            index--;
//        }
//        if (index <= 0) {
//            Msgbox.info(new MainJFrame(), "Bạn đang ở đầu danh sách");
//        }
//        display(index);
//    }
//
//    //next
//    public void next() {
//        if (index < listSP.size() - 1) {
//            index++;
//        }
//        if (index >= listSP.size() - 1) {
//            Msgbox.info(new MainJFrame(), "Bạn đang ở cuối danh sách");
//        }
//        display(index);
//    }
//
//    public void update_SLSP(SanPham sp) {
//        if (sp != null) {
//            boolean choice = Msgbox.yesNo("Cập nhật số lượng", "Sản phẩm đã tồn tại! \nBạn có muốn cập nhật lại số lượng không?");
//            if (!choice) {
//                clear();
//                return;
//            }
//            String sl_new = JOptionPane.showInputDialog(new MainJFrame(), "Mời nhập số lượng!");
//            try {
//                daoSP.updateSL(sp.getMaSP(), Integer.parseInt(sl_new) + sp.getSoLuong());
//                Msgbox.success(new MainJFrame(), "Cập nhật thành công!");
//            } catch (Exception e) {
//                Msgbox.waring(new MainJFrame(), "Lỗi cập nhật dữ liệu khi thêm!");
//            }
//            loadDaTa();
//            clear();
//        }
//    }
//
//    public void findIdAndName(String IdAndName) {
//        DefaultTableModel model = new DefaultTableModel();
//        model = (DefaultTableModel) tblSanPham.getModel();
//        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
//        tblSanPham.setRowSorter(trs);
//        trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 1, 5));
//
//    }
//
//    public void filterByStatus(String IdAndName) {
//        DefaultTableModel model = new DefaultTableModel();
//        model = (DefaultTableModel) tblSanPham.getModel();
//        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
//        tblSanPham.setRowSorter(trs);
//        trs.setRowFilter(RowFilter.regexFilter("(?i)" + IdAndName, 5));
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tabpane = new com.duan1.swing.MaterialTabbed();
        pnlCapNhat = new javax.swing.JPanel();
        pnlText = new javax.swing.JPanel();
        txtMaSP = new com.duan1.swing.MyTextField();
        pnlThemSuaXoa = new javax.swing.JPanel();
        btnThem = new com.duan1.swing.Button();
        btnSua = new com.duan1.swing.Button();
        btnXoa = new com.duan1.swing.Button();
        btnMoi = new com.duan1.swing.Button();
        pnlDieuHuong = new javax.swing.JPanel();
        btnFirst = new com.duan1.swing.Button();
        btnPrev = new com.duan1.swing.Button();
        btnNext = new com.duan1.swing.Button();
        btnLast = new com.duan1.swing.Button();
        txtTenSP = new com.duan1.swing.MyTextField();
        txtSoLuong = new com.duan1.swing.MyTextField();
        cboDonViTinh = new com.duan1.swing.ComboBoxSuggestion();
        txtDonGia = new com.duan1.swing.MyTextField();
        cboDanhMuc = new com.duan1.swing.ComboBoxSuggestion();
        cboTrangThai = new com.duan1.swing.ComboBoxSuggestion();
        txtHinh = new com.duan1.swing.MyTextField();
        jPanel1 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        btnThemMNK = new com.duan1.swing.Button();
        lblTieuDeMaPNK = new javax.swing.JLabel();
        lblMaPNK = new javax.swing.JLabel();
        btnketThucLanNhapKho = new com.duan1.swing.Button();
        pnlDanhSach = new javax.swing.JPanel();
        scroll_SPBan = new javax.swing.JScrollPane();
        tblSanPham = new com.duan1.swing.Table();
        cboTab = new com.duan1.swing.ComboBoxSuggestion();
        pnlDanhSach1 = new javax.swing.JPanel();
        scroll_PhieuNK = new javax.swing.JScrollPane();
        tblPhieuNhapKho = new com.duan1.swing.Table();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new com.duan1.swing.TextFieldAnimation();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        pnlCapNhat.setBackground(new java.awt.Color(255, 255, 255));

        pnlText.setBackground(new java.awt.Color(255, 255, 255));

        txtMaSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSPKeyReleased(evt);
            }
        });

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

        btnFirst.setText("|<");
        btnFirst.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnFirst);

        btnPrev.setText("<<");
        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnPrev);

        btnNext.setText(">>");
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnNext);

        btnLast.setText(">|");
        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlDieuHuong.add(btnLast);

        cboDonViTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đơn vị tính", "Kg", "Chai", "Hộp", "Lon", "Con", " " }));
        cboDonViTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDonViTinhActionPerformed(evt);
            }
        });

        cboDanhMuc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Danh mục" }));
        cboDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDanhMucActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trạng thái", "Đang kinh doanh", "Không còn bán", "Hết hàng" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Picture", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(27, 178, 222))); // NOI18N

        lblHinh.setBackground(new java.awt.Color(0, 51, 255));
        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan1/icon/Bird.jpg"))); // NOI18N
        lblHinh.setToolTipText("");
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblHinh)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        btnThemMNK.setText("Tạo phiếu nhập kho");
        btnThemMNK.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemMNK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMNKActionPerformed(evt);
            }
        });

        lblTieuDeMaPNK.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTieuDeMaPNK.setForeground(new java.awt.Color(80, 80, 80));
        lblTieuDeMaPNK.setText("Mã PNK:");

        btnketThucLanNhapKho.setText("Kết thúc lần nhập");
        btnketThucLanNhapKho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnketThucLanNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnketThucLanNhapKhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTextLayout = new javax.swing.GroupLayout(pnlText);
        pnlText.setLayout(pnlTextLayout);
        pnlTextLayout.setHorizontalGroup(
            pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTextLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlThemSuaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTextLayout.createSequentialGroup()
                        .addComponent(btnThemMNK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnketThucLanNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDieuHuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTextLayout.createSequentialGroup()
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboDonViTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                .addComponent(cboDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlTextLayout.createSequentialGroup()
                                .addComponent(lblTieuDeMaPNK, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMaPNK, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        pnlTextLayout.setVerticalGroup(
            pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTextLayout.createSequentialGroup()
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTextLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThemMNK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnketThucLanNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTieuDeMaPNK, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaPNK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTextLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(pnlTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlThemSuaXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDieuHuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
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

        scroll_SPBan.setBackground(new java.awt.Color(255, 255, 255));
        scroll_SPBan.setBorder(null);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "TT", "Mã  SP", "Tên SP", "Số Lượng", "Đơn Giá", "Trạng Thái", "Mã Danh Mục", "Mã Nhân Viên", "Hình Ảnh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        scroll_SPBan.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        cboTab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Đang kinh doanh", "Không còn bán", "Hết hàng" }));
        cboTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_SPBan, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cboTab, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(cboTab, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(scroll_SPBan, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Tabpane.addTab("Danh Sách", pnlDanhSach);

        pnlDanhSach1.setBackground(new java.awt.Color(255, 255, 255));

        scroll_PhieuNK.setBackground(new java.awt.Color(255, 255, 255));
        scroll_PhieuNK.setBorder(null);

        tblPhieuNhapKho.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "Mã PNK", "Ngày Tạo", "Tổng Số Lượng SP", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhapKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapKhoMouseClicked(evt);
            }
        });
        scroll_PhieuNK.setViewportView(tblPhieuNhapKho);
        if (tblPhieuNhapKho.getColumnModel().getColumnCount() > 0) {
            tblPhieuNhapKho.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        javax.swing.GroupLayout pnlDanhSach1Layout = new javax.swing.GroupLayout(pnlDanhSach1);
        pnlDanhSach1.setLayout(pnlDanhSach1Layout);
        pnlDanhSach1Layout.setHorizontalGroup(
            pnlDanhSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSach1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll_PhieuNK, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDanhSach1Layout.setVerticalGroup(
            pnlDanhSach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSach1Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(scroll_PhieuNK, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Tabpane.addTab("Danh sách phiếu nhập", pnlDanhSach1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tabpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Tabpane, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
//        if (evt.getClickCount() == 2) {
//            index = tblSanPham.getSelectedRow();
//            display(index);
//            Tabpane.setSelectedIndex(0);
//        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cboTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTabActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTabActionPerformed

    private void cboDonViTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDonViTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDonViTinhActionPerformed

    private void cboDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDanhMucActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        ImgHelper n = new ImgHelper();
        n.ChonHinh(frame, lblHinh, txtHinh);
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnThemMNKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMNKActionPerformed
        taoPhieuNhapKho();
    }//GEN-LAST:event_btnThemMNKActionPerformed

    private void btnketThucLanNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnketThucLanNhapKhoActionPerformed
        if (lblMaPNK.getText().trim().isEmpty()) {
            Msgbox.waring(new MainJFrame(), "Bạn chưa tạo phiếu nhập nào!");
            return;
        }
        ketThucLanNhapKho();

    }//GEN-LAST:event_btnketThucLanNhapKhoActionPerformed

    private void tblPhieuNhapKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapKhoMouseClicked
        int row = tblPhieuNhapKho.getSelectedRow();
        if (evt.getClickCount() == 2) {
            JDL_ThongTinChiTiet_PhieuNhap ttct = new JDL_ThongTinChiTiet_PhieuNhap(new MainJFrame(), true);
            ttct.loadData(String.valueOf(tblPhieuNhapKho.getValueAt(row, 0)));
            ttct.setVisible(true);
        }

    }//GEN-LAST:event_tblPhieuNhapKhoMouseClicked

    private void txtMaSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSPKeyReleased
    }//GEN-LAST:event_txtMaSPKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan1.swing.MaterialTabbed Tabpane;
    private com.duan1.swing.Button btnFirst;
    private com.duan1.swing.Button btnLast;
    private com.duan1.swing.Button btnMoi;
    private com.duan1.swing.Button btnNext;
    private com.duan1.swing.Button btnPrev;
    private com.duan1.swing.Button btnSua;
    private com.duan1.swing.Button btnThem;
    private com.duan1.swing.Button btnThemMNK;
    private com.duan1.swing.Button btnXoa;
    private com.duan1.swing.Button btnketThucLanNhapKho;
    private com.duan1.swing.ComboBoxSuggestion cboDanhMuc;
    private com.duan1.swing.ComboBoxSuggestion cboDonViTinh;
    private com.duan1.swing.ComboBoxSuggestion cboTab;
    private com.duan1.swing.ComboBoxSuggestion cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblMaPNK;
    private javax.swing.JLabel lblTieuDeMaPNK;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JPanel pnlDanhSach1;
    private javax.swing.JPanel pnlDieuHuong;
    private javax.swing.JPanel pnlText;
    private javax.swing.JPanel pnlThemSuaXoa;
    private javax.swing.JScrollPane scroll_PhieuNK;
    private javax.swing.JScrollPane scroll_SPBan;
    private com.duan1.swing.Table tblPhieuNhapKho;
    private com.duan1.swing.Table tblSanPham;
    private com.duan1.swing.MyTextField txtDonGia;
    private com.duan1.swing.MyTextField txtHinh;
    private com.duan1.swing.MyTextField txtMaSP;
    private com.duan1.swing.MyTextField txtSoLuong;
    private com.duan1.swing.MyTextField txtTenSP;
    private com.duan1.swing.TextFieldAnimation txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
