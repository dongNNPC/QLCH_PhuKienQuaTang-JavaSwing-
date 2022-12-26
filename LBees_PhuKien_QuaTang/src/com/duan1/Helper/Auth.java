
package com.duan1.Helper;

import com.duan1.DAO.TaiKhoanDAO;

import com.duan1.Entity.TaiKhoan;

/**
 *
 * @author asus
 */
public class Auth {

    //Đối tượng chứa thông tin người sử dụng sau khi đăng nhập
    public static TaiKhoan tk = null;

    //Xóa người đăng nhập sau khi đăng xuất
    public static void clear() {
        Auth.tk = null;
    }

    //Kiểm tra xem Nhân viên có đăng nhập hay không
    public static boolean isLogin() {
        return Auth.tk != null;
    }

    //Kiểm tra vai trò của nhân viên đó
    public static boolean role() {
        TaiKhoanDAO daoTK = new TaiKhoanDAO();
        String name = daoTK.selectByCV(tk.getMaNV()) ? "QUẢN LÝ" : "NHÂN VIÊN";
        if (name.equals("QUẢN LÝ")) {
            return true;
        }else{
            return false;
        }
    }

    public static String tenNV() {

        TaiKhoanDAO daoTK = new TaiKhoanDAO();
        String name = daoTK.selectByTen(tk.getMaNV());

        return name;
    }
}
