package com.duan1.swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class MyTextField extends JTextField {

    private Icon iconTrc;
    private Icon iconSau;

    public Icon getIconTrc() {
        return iconTrc;
    }

    public void setIconTrc(Icon iconTrc) {
        this.iconTrc = iconTrc;
    }

    public Icon getIconSau() {
        return iconSau;
    }

    public void setIconSau(Icon iconSau) {
        this.iconSau = iconSau;
    }

    public MyTextField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintIcon(g);
        initBorder();
        if (isFocusOwner()) {
            g.setColor(new Color(0, 78, 146));
            g.fillRect(1, getHeight() - 5, getWidth() - 4, 2);
        } else {
            g.setColor(new Color(150, 150, 150));
            g.fillRect(1, getHeight() - 5, getWidth() - 4, 1);
        }
    }

    public void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (iconTrc != null) {
            Image trc = ((ImageIcon) iconTrc).getImage();
            int y = (getHeight() - iconTrc.getIconHeight()) / 2;
            g2.drawImage(trc, 3, y, this);
        }
        if (iconSau != null) {
            Image sau = ((ImageIcon) iconSau).getImage();
            int y = (getHeight() - iconSau.getIconHeight()) / 2;
            int x = getWidth() - iconSau.getIconWidth() - 3;

            g2.drawImage(sau, x, y, this);
        }

    }
    String hint = "";

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().isEmpty()) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    public void initBorder() {
        int left = 5;
        int right = 5;

        if (iconTrc != null) {
            left = iconTrc.getIconWidth() + 7;
        }
        if (iconSau != null) {
            right = iconSau.getIconWidth() + 7;
        }

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, left, 5, right));
    }

    public void setHintText(String nhập_mã_tên_giao_hàng) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addEvent(EventTextField eventTextField) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
