/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.swing;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author MSI PC
 */
public class Tableheader extends JLabel{
    public Tableheader(String text) {
        super(text);
        setBorder(null);
        setOpaque(true);
        setBackground(new Color(204,204,204));
        setFont(new Font("Segoe UI",Font.BOLD,12));
        setForeground(new Color(102, 102, 102));
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }
}
