/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan1.Helper;

import com.barcodelib.barcode.Linear;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL-PC
 */
public class ImgHelper {

    public static final Image APP_ICON;

    static {
        String file = "/Bird.jpg";
        APP_ICON = new ImageIcon(ImgHelper.class.getResource(file)).getImage();

    }

    public static boolean saveLogo(File file) {
        File dir = new File("Picture");
        if (!dir.exists()) {   //Không tìm thấy tệp!  .check file có tồn tại không.
            dir.mkdirs();    //Phương thức mkdir() được sử dụng để tạo ra dường dẫn thư mục và tất cả các đường dẫn thư mục con của nó.
        }
        File newFile = new File(dir, file.getName()); //Cú pháp sau tạo một đối tượng File mới bởi việc biến đổi chuỗi pathname đã cho thành một pathname trừu tượng.

        try {
            Path source = Paths.get(file.getAbsolutePath());  //Lấy đường dẫn của file  cũ
            Path destination = Paths.get(newFile.getAbsolutePath()); //mới
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING); //Replace exitsting thực hiện duy chuyển ngay cả khi các tập tin đích tồn tại, nếu đích là một liên kết tượng trưng thì nó được thay thế những gì nó trỏ tới không bị ảnh hưởng
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * * Đọc hình ảnh logo chuyên đề * @param fileName là tên file logo
     *
     *
     * @return ảnh đọc được
     */
    public static ImageIcon readLogo(String fileName) {
        File path = new File("Picture", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    private javax.swing.JFileChooser FileChooser;

    public void ChonHinh(Component parentComponent, JLabel lblHinh, JTextField txtHinh) {
        FileChooser = new javax.swing.JFileChooser();
        if (FileChooser.showOpenDialog(parentComponent) == JFileChooser.APPROVE_OPTION) {
            File file = FileChooser.getSelectedFile();
            System.out.println(file);
            if (ImgHelper.saveLogo(file)) {
                // Hiển thị hình lên form 
                ImageIcon hinhanh = new ImageIcon(new ImageIcon(String.valueOf(ImgHelper.readLogo(file.getName()))).getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH));
                lblHinh.setIcon(hinhanh);
                txtHinh.setText(file.getName());
            }
        }
    }

    public void loadHinhVaoForm(String s, JLabel lblHinh) {
        if (!s.equals("")) {
            ImageIcon hinhanh = new ImageIcon(new ImageIcon(String.valueOf(ImgHelper.readLogo(s))).getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH));
            lblHinh.setIcon(hinhanh);
            // lblHinh.setIcon(ImgHelper.readLogo(cd.getHinhString()));
        }
    }

    public static void Write(String text) {
        try {

            File dir = new File("barcodePicture");
            if (!dir.exists()) {   //Không tìm thấy tệp!  .check file có tồn tại không.
                dir.mkdirs();    //Phương thức mkdir() được sử dụng để tạo ra dường dẫn thư mục và tất cả các đường dẫn thư mục con của nó.
            }
            File newFile = new File(dir, text);
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(text);
            barcode.setI(11.0f);
            String fname = text;

            barcode.renderBarcode(newFile + ".png");
        } catch (Exception e) {

        }
    }

}
