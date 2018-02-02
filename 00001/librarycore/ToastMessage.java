/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Rz Rasel 2017-12-12
 */
public class ToastMessage extends JDialog {

    int miliseconds;

    public ToastMessage(JFrame parent, String toastString, int time) {
        this.miliseconds = time;
        Point point = new Point(0, 0);
        if (parent != null) {
            Dimension parentSize = parent.getSize();
            point = parent.getLocation();
            point.x = point.x + parentSize.width / 2;
            point.y = point.y + parentSize.height / 2;
            //setLocation(point.x + parentSize.width / 4, point.y + parentSize.height / 4);
            setLocation(point.x + parentSize.width, point.y + parentSize.height);
            //setLocation(0, 0);
            System.out.println("LOCATION: " + point.x + " - " + point.y);
        }
        Point pointA = parent.getLocationOnScreen();
        System.out.println("LOCATION: " + pointA.x + " - " + pointA.y);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("LOCATION: " + screenSize.width + " - " + screenSize.height);
        this.setLocationRelativeTo(parent);
        this.setUndecorated(true);
        this.setModal(true);
        this.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel toastLabel = new JLabel("");
        toastLabel.setText(toastString);
        toastLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        toastLabel.setForeground(Color.WHITE);

        //setBounds(100, 100, toastLabel.getPreferredSize().width + 20, 31);
        point.x = point.x - toastLabel.getPreferredSize().width / 2;
        point.y = point.y - toastLabel.getPreferredSize().height / 2;
        setBounds(point.x, point.y, toastLabel.getPreferredSize().width + 20, 31);

        setAlwaysOnTop(true);
        /*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = dim.height / 2 - getSize().height / 2;
        int half = y / 2;
        setLocation(dim.width / 2 - getSize().width / 2, y + half);*/
        panel.add(toastLabel);
        setVisible(false);

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(miliseconds);
                    dispose();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
