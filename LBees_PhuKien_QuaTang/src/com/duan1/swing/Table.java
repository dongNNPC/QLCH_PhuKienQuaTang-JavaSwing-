/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author MSI PC
 */
public class Table extends JTable{
    public Table() {
        
        setBackground(Color.WHITE);
        setShowHorizontalLines(false);
        setRowHeight(40);
        setSelectionBackground(new Color(121, 177, 231));
        setFillsViewportHeight(true);
        setFocusable(false);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean bln, boolean bln1, int i, int i1) {
                Tableheader header = new Tableheader(o + "");
                
                 
                return header;
            }
             
        });
        
    }
}
