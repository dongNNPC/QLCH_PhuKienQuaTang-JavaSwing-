/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.swing;

import com.barcodelib.barcode.Linear;
import java.awt.TextField;
import javax.swing.JTextField;

/**
 *
 * @author asus
 */
public class Write_Barcode {

    public static void Write(JTextField text) {
        try {

            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(text.getText());
            barcode.setI(11.0f);

            String fname = text.getText();

            barcode.renderBarcode("D:\\" + fname + ".png");
        } catch (Exception e) {

        }
    }
}
