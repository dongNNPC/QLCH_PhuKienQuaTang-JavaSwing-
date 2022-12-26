/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.swing;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author asus
 */
public class Read_Barcode {
       public static void Read_IMG(JTextField text) {
        try {
             InputStream barInputStream = new FileInputStream(text.getText());
             BufferedImage barBufferedImage = ImageIO.read(barInputStream);
             LuminanceSource source = new BufferedImageLuminanceSource(barBufferedImage);
             BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
             Reader reader = new MultiFormatReader();
             Result result = reader.decode(bitmap);
             //lbl.setText(result.getText());
             JOptionPane.showMessageDialog(text, result.getText());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
