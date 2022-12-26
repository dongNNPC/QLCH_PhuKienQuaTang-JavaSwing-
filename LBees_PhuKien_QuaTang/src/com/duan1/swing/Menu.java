package com.duan1.swing;


import com.duan1.swing.ScrollBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javaswingdev.GoogleMaterialDesignIcon;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Menu extends JPanel {
    private String bg_1 = "#1CB5E0";
    private String bg_2 = "#000046";
    private int index = -1;
    private final List<EventMenuSelected> events = new ArrayList<>();

    public Menu() {
        init();
    }
     @Override
    protected void paintChildren(Graphics graph) {
        Graphics2D g2 = (Graphics2D) graph;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode(bg_1), 0, getHeight(), Color.decode(bg_2));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        g2.fillRect(getWidth() - 15, 0, getWidth(), getHeight());
        super.paintChildren(graph);
    }
    private void init() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JScrollPane scroll = createScroll();
        panelMenu = createPanelMenu();
        scroll.setViewportView(panelMenu);
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        add(scroll);
        addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.HOME, "Trang chủ"));
        addTitle("Các form quản lý");
        addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.GROUP, "Quản lý","Khách hàng","Nhân viên","Người giao hàng" ,"Tài khoản", "Danh mục" ));
        addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.STORE, "Sản phẩm"));
        addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.STORAGE, "Bán hàng"));
        addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.RECEIPT, "Lịch sử hoá đơn"));
        addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.MONETIZATION_ON, "Thống kê"));
        addTitle("Dịch vụ");
        addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.REDEEM, "Quà tặng"));
        
    }

    private JScrollPane createScroll() {
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBar(new ScrollBar());
        return scroll;
    }

    private JPanel createPanelMenu() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        menuLayout = new MigLayout("wrap,fillx,inset 0,gapy 0", "[fill]");
        panel.setLayout(menuLayout);

        return panel;
    }

    private JPanel createMenuItem(ModelMenuItem item) {
        MenuItem menuItem = new MenuItem(item, ++index, menuLayout);
        menuItem.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int index, int indexSubMenu) {
                if (!menuItem.isHasSubMenu() || indexSubMenu != 0) {
                    clearSelected();
                    setSelectedIndex(index, indexSubMenu);
                }
            }
        });
        return menuItem;
    }

    private void runEvent(int index, int indexSubMenu) {
        for (EventMenuSelected event : events) {
            event.menuSelected(index, indexSubMenu);
        }
    }

    //  Public Method
    public void addMenuItem(ModelMenuItem menu) {
        panelMenu.add(createMenuItem(menu), "h 35!");
    }

    public void addTitle(String title) {
        JLabel label = new JLabel(title);
        label.setBorder(new EmptyBorder(10, 10, 5, 5));
        label.setFont(label.getFont().deriveFont(Font.ITALIC));
        label.setForeground(new Color(255,255,255));
        panelMenu.add(label);
    }

    public void addSpace(int size) {
        panelMenu.add(new JLabel(), "h " + size + "!");
    }

    public void setSelectedIndex(int index, int indexSubMenu) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getIndex() == index) {
                    item.setSelectedIndex(indexSubMenu);
                    runEvent(index, indexSubMenu);
                    break;
                }
            }
        }
    }

    public void clearSelected() {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                item.clearSelected();
            }
        }
    }

    public void addEvent(EventMenuSelected event) {
        events.add(event);
    }

    private MigLayout menuLayout;
    private JPanel panelMenu;
    
    
     
    
}
