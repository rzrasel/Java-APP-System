/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guiform;

/**
 *
 * @author developer
 */
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class WinLsnrExp extends JFrame {

    public WinLsnrExp() {
        setTitle("Window listener Example : ");
        setSize(150, 150);
    }

    public static void main(String[] args) {

        /*WinLsnrExp wle = new WinLsnrExp();
        wle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wle.setVisible(true);
        wle.addWindowListener(new WindowListener() {
            public void windowClosed(WindowEvent arg0) {
                System.out.println("Window close event occur");
            }
            public void windowActivated(WindowEvent arg0) {
                System.out.println("Window Activated");
            }
            public void windowClosing(WindowEvent arg0) {
                System.out.println("Window Closing");
            }
            public void windowDeactivated(WindowEvent arg0) {
                System.out.println("Window Deactivated");
            }
            public void windowDeiconified(WindowEvent arg0) {
                System.out.println("Window Deiconified");
            }
            public void windowIconified(WindowEvent arg0) {
                System.out.println("Window Iconified");
            }
            public void windowOpened(WindowEvent arg0) {
                System.out.println("Window Opened");
            }
        });*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WinLsnrExp wle = new WinLsnrExp();
                wle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                wle.setLocationRelativeTo(null);
                wle.setUndecorated(true);
                wle.setVisible(true);
                wle.addWindowListener(new WindowListener() {
                    public void windowClosed(WindowEvent arg0) {
                        System.out.println("Window close event occur");
                    }

                    public void windowActivated(WindowEvent arg0) {
                        System.out.println("Window Activated");
                    }

                    public void windowClosing(WindowEvent arg0) {
                        System.out.println("Window Closing");
                    }

                    public void windowDeactivated(WindowEvent arg0) {
                        System.out.println("Window Deactivated");
                    }

                    public void windowDeiconified(WindowEvent arg0) {
                        System.out.println("Window Deiconified");
                    }

                    public void windowIconified(WindowEvent arg0) {
                        System.out.println("Window Iconified");
                    }

                    public void windowOpened(WindowEvent arg0) {
                        System.out.println("Window Opened");
                    }
                });
            }
        });
    }
}
