/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.Helper;

import com.duan1.swing.MessageDialog;
import com.duan1.swing.Notification;
import com.duan1.ui.MainJFrame;

import java.awt.Component;
import java.awt.Frame;

/**
 *
 * @author MSI PC
 */
public class Msgbox {

    public static void info(Component parentComponent, String mes) {
        Notification no = new Notification((Frame) parentComponent, Notification.Type.INFO, Notification.Location.TOP_RIGHT, mes);
        no.showNotification();
    }
    
     public static void infoCT(Component parentComponent, String mes) {
        Notification no = new Notification((Frame) parentComponent, Notification.Type.INFO, Notification.Location.TOP_CENTER, mes);
        no.showNotification();
    }

    public static void success(Component parentComponent, String mes) {
        Notification no = new Notification((Frame) parentComponent, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, mes);
        no.showNotification();
    }

    public static void waring(Component parentComponent, String mes) {
        Notification no = new Notification((Frame) parentComponent, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, mes);
        no.showNotification();
    }

    public static void CheckDN(Component parentComponent, String mes) {
        Notification no = new Notification((Frame) parentComponent, Notification.Type.WARNING, Notification.Location.CENTER, mes);
        no.showNotification();
    }
    

    public static boolean yesNo(String tieuDe, String text) {
        MainJFrame f = new MainJFrame();
        MessageDialog mdl = new MessageDialog(f);
        mdl.showMessage(tieuDe, text);
        return mdl.getMessageType() == MessageDialog.MessageType.OK;

    }
}
