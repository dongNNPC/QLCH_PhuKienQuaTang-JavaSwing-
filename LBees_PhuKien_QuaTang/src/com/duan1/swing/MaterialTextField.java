/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;

/**
 *
 * @author phung
 */
public class MaterialTextField extends JTextField {

    private final Animator ANIMATOR = null;
    private boolean isMouseOver;
    private double location;
    private double show;
    private String labelText = "Text";
    private Color lineColor = new Color(3, 155,216);
    public MaterialTextField() {
        setBorder(new EmptyBorder(20, 3, 10, 3));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOver = false;
                repaint();
            }

        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = getWidth();
        int height = getHeight();
        if (isMouseOver) {
            g2.setColor(Color.yellow);
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
        g2.fillRect(2, height - 1, width - 4, 1);
        g2.dispose();
    }

}
