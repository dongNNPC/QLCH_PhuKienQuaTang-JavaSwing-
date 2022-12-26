package com.duan1.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class ButtonCustomMessageDiaLog extends JButton {

    private Color background = new Color(69, 191, 71);
    private Color colorHover = new Color(16, 104, 159);
    private Color colorPressed = new Color(63, 175, 65);
    private boolean mouseOver = false;

    public ButtonCustomMessageDiaLog() {
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(background);
        setForeground(Color.WHITE);
        setOpaque(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver = true;
                ButtonCustomMessageDiaLog.super.setBackground(getColorHover());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver = false;
                ButtonCustomMessageDiaLog.super.setBackground(background);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ButtonCustomMessageDiaLog.super.setBackground(getColorPressed());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (mouseOver) {
                    ButtonCustomMessageDiaLog.super.setBackground(getColorHover());
                } else {
                    ButtonCustomMessageDiaLog.super.setBackground(background);
                }
            }
        });
    }

    @Override
    public void setBackground(Color bg) {
        background = bg;
        super.setBackground(bg);
    }

    public Color getColorHover() {
        return colorHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    public Color getColorPressed() {
        return colorPressed;
    }

    public void setColorPressed(Color colorPressed) {
        this.colorPressed = colorPressed;
    }
}
