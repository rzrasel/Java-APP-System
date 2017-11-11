/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guiform;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Rz Rasel
 */
public class MyDialog extends JDialog {

    //private StageModalDialog smd;

    public MyDialog(JFrame frame) {
        super(frame, "Stage Modal Dialog", true);

        setResizable(false);
        setSize(0, 0);
        setUndecorated(true);

        new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //smd = new StageModalDialog();
                //smd.showAndWait();
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                //if (smd != null && smd.isShowing()) {
                    //smd.toFront();
                //}
            }
        });

        setVisible(true);
    }
}
