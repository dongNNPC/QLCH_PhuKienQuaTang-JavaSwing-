/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.swing;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author asus
 */
public class Barcodes {

    private javax.swing.JFileChooser FileChooser;

    public void Read_IMG(Component parentComponent, JTextField text) {

        FileChooser = new javax.swing.JFileChooser();
        File file = null;

        try {
            if (FileChooser.showOpenDialog(parentComponent) == JFileChooser.APPROVE_OPTION) {
                file = FileChooser.getSelectedFile();
            }
            InputStream barInputStream = new FileInputStream(file.getAbsolutePath());
            BufferedImage barBufferedImage = ImageIO.read(barInputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(barBufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);
            text.setText(result.getText());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Write(JTextField text) {
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
