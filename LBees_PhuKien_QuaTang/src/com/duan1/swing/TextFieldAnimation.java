/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.swing;



/**
 *
 * @author MSI PC
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
public class TextFieldAnimation extends JTextField{
    
    
    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public Color getAnimationColor() {
        return animationColor;
    }

    public void setAnimationColor(Color animationColor) {
        this.animationColor = animationColor;
    }

    private Color backgroundColor = Color.WHITE;
    private Color animationColor = new Color(3, 175, 255);
    private final Icon iconSearch;
    private final Icon iconClose;
    private final Icon iconLoading;
    private String hintText = "";
    private boolean show;
    private double location = -1;
    private com.duan1.swing.EventTextField event;
    private com.duan1.swing.EventCallBack callBack;
    private Thread thread;
    private final Animator animator;
    
    
    public TextFieldAnimation() {
        super.setBackground(new Color(255,255,255, 0)); //  Gỡ bỏ nền
        setOpaque(false);
        setBorder(new EmptyBorder(10, 10, 10, 50)); //  Đặt đường viền phải 50
        setFont(new java.awt.Font("sansserif", 0, 14)); 
        setSelectionColor(new Color(80, 199, 255));
        iconSearch = new ImageIcon(getClass().getResource("/com/duan1/icon/search.png"));
        iconClose = new ImageIcon(getClass().getResource("/com/duan1/icon/close.png"));
        iconLoading = new ImageIcon(getClass().getResource("/com/duan1/icon/loading.gif"));
        //  Tạo và kiểm tra xem nút di chuột qua
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                //lia chuột thay đổi con trỏ chuột
                if (checkMouseOver(me.getPoint())) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.TEXT_CURSOR));
                }
            }
        });
        //  Tạo cú nhấp chuột
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {  //  khi một nút chuột đã được nhấn trên một thành phần
                
                if (SwingUtilities.isLeftMouseButton(me)) {  // chỉ định sử dụng chuột trái >...(chuột giữa)
                    if (checkMouseOver(me.getPoint())) {
                        if (!animator.isRunning()) {
                            if (show) {
                                setEditable(true);
                                show = false;
                                location = 0;
                                animator.start();
                                if (thread != null) {
                                    thread.interrupt();
                                }
                                if (event != null) {
                                    event.onCancel();
                                }
                            } else {
                                setEditable(false);
                                show = true;
                                location = getWidth();
                                animator.start();
                                
                                if (event != null) {
                                    thread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            event.onPressed(callBack);
                                        }
                                    });
                                    thread.start();
                                }
                            }
                        }
                    }
                }
            }
        });
        callBack =  new EventCallBack() {
            @Override
            public void done() {
                setEditable(true);
                show = false;
                location = 0;
                animator.start();
            }
        };
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width = getWidth();
                if (show) {
                    location = width * (1f - fraction);
                } else {
                    location = width * fraction;
                }
                repaint();
            }
        };
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);    //  Cho đường trơn
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //  Cho hình ảnh mượt mà
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, width, height, height, height);
        super.paintComponent(grphcs);
        // tạo Button
        int marginButton = 5;
        int buttonSize = height - marginButton * 2;
        GradientPaint gra = new GradientPaint(0, 0, new Color(255, 255, 255), width, 0, animationColor);
        g2.setPaint(gra);
        g2.fillOval(width - height + 3, marginButton, buttonSize, buttonSize);
        //  Tạo hoạt ảnh khi nhấp vào nút
        if (location > -1) {
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, height, height));
            area.intersect(new Area(new RoundRectangle2D.Double(location, 0, width - location, height, height, height)));
            g2.fill(area);
            //  tạo Loading icon
            int iconSize = iconLoading.getIconHeight();
            //  tạo Alpha
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha()));
            g2.drawImage(((ImageIcon) iconLoading).getImage(), (int) (location + 5), (height - iconSize) / 2, this);
        }
        //  tạo Button Icon
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); //  Đặt thành mặc định
        int marginImage = 5;
        int imageSize = buttonSize - marginImage * 2;
        Image image;
        if (show) {
            image = ((ImageIcon) iconClose).getImage();
        } else {
            image = ((ImageIcon) iconSearch).getImage();
        }
        g2.drawImage(image, width - height + marginImage + 3, marginButton + marginImage, imageSize, imageSize, null);
        g2.dispose();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (show == false && getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(hintText, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    private float getAlpha() {
        float width = getWidth() / 2;
        float alpha = ((float) location) / (-width);
        alpha += 1;
        if (alpha < 0) {
            alpha = 0;
        }
        if (alpha > 1) {
            alpha = 1;
        }
        return alpha;
    }

    private boolean checkMouseOver(Point mouse) {
        int width = getWidth();
        int height = getHeight();
        int marginButton = 5;
        int buttonSize = height - marginButton * 2;
        Point point = new Point(width - height + 3, marginButton);
        Ellipse2D.Double circle = new Ellipse2D.Double(point.x, point.y, buttonSize, buttonSize);
        return circle.contains(mouse);
    }
 
    @Override
    public void setBackground(Color color) {
        this.backgroundColor = color;
    }

    public void addEvent(com.duan1.swing.EventTextField event) {
        this.event = event;
    }
    
}
