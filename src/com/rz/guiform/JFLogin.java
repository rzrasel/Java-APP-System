/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guiform;

import com.rz.guimodel.ModelObserverAdapter;
import com.rz.guimodel.ModelUserLogin;
import com.rz.libraries.MD5MoreSecure;
import com.rz.libraries.PasswordEncryptionService;
import com.rz.libraries.RandomValue;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author developer
 */
public class JFLogin extends JFrame {

    private Timer appTimer = new Timer();

    //private JFLogin jFLogin;
    /**
     * Creates new form JFLogin
     */
    public JFLogin() {
        initComponents();
        this.setTitle("Login");
        //this.setSize(150, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        /*System.out.println("RAND_VALUE: " + RandomValue.getRandId(1111, 9999));
        System.out.println("RAND_VALUE: " + RandomValue.randInt(1, 9));
        System.out.println("SALT: " + MD5MoreSecure.usage("testPassword"));
        System.out.println("SALT_PREV: " + MD5MoreSecure.usage("testPasswordd"));
        try {
            String example = "This is an example";
            byte[] salty = PasswordEncryptionService.generateSalt();
            byte[] salt = new byte[1024];
            salt = example.getBytes("UTF-8");
            String strSaltByte = new String(salt, "UTF-8");
            System.out.println("SALTY:--" + strSaltByte + "--" + example.getBytes());
            System.out.println("PASSDORW:--" + PasswordEncryptionService.getEncryptedPassword("testPassword", salt) + "--");

            String msg = "Hello";
            byte[] buff = new byte[1024];
            buff = msg.getBytes("UTF-8");
            System.out.println(buff);
            String m = new String(buff);
            System.out.println(m);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JFLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(JFLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(JFLogin.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        ImageIcon imageIcon = onResizeLabelIcon("/images/login-icon.png", 90, 90);
        jLblLoginLogo.setIcon(imageIcon);
        jLblLoginLogo.setText("");
        //jLblMsg.setText("Please login");
        jLblMsg.setText("<html><font color='black'>Please login</font></html>");
        jLblLoginLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Yay you clicked me");
            }

        });
        jBtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isLoggedIn = false;
                String userIdentity = "";
                String userPassword = "";
                userIdentity = jTxtEmail.getText();
                userPassword = new String(jTxtPassword.getPassword());
                ModelUserLogin modelUserLogin = new ModelUserLogin();
                isLoggedIn = modelUserLogin.isLoggedIn(userIdentity, userPassword);
                if (isLoggedIn) {
                    JFDashboard jFDashboard = new JFDashboard();
                    jFDashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    /*GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
                    device.setFullScreenWindow(jFDashboard);*/
                    jFDashboard.setLocationRelativeTo(null);
                    jFDashboard.setVisible(true);
                    //jFDashboard.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                    dispose();
                } else {
                    appTimer = new Timer();
                    appTimer.schedule(new AppMsgTimer(), 0, 1000);
                    //jLblMsg.setText("Invalid e-mail or password");
                    jLblMsg.setText(ModelObserverAdapter.adapterSuccessError.get("login_message").toString());
                }
            }
        });
        jBtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jLblForgotPassword.setText("<html><u>Forgot Password</u></html>");
        jLblRegistration.setText("<html><u>Registration</u></html>");
        jBtnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jBtnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLblForgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLblRegistration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        /*jTxtEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTxtEmail.setText("Enter ttt");
            }
        });*/
        jTxtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                onKeyEventHandler(e, "txt_email");
            }
        });
        jTxtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                onKeyEventHandler(e, "txt_password");
            }
        });
        jBtnLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                onKeyEventHandler(e, "btn_login");
            }
        });
    }

    private void onKeyEventHandler(KeyEvent e, String argFieldSelf) {
        if (argFieldSelf.equalsIgnoreCase("txt_email")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                //jTxtPassword.requestFocus();
                jBtnLogin.doClick();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                jTxtPassword.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                jTxtPassword.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                jBtnLogin.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                jBtnLogin.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        } else if (argFieldSelf.equalsIgnoreCase("txt_password")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                jBtnLogin.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                jBtnLogin.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                jBtnLogin.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                jTxtEmail.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                jTxtEmail.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        } else if (argFieldSelf.equalsIgnoreCase("btn_login")) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                jTxtEmail.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                jTxtEmail.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                jTxtPassword.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                jTxtPassword.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLoginBg = new javax.swing.JPanel();
        jPanelLoginForm = new javax.swing.JPanel();
        jLabelEmail = new javax.swing.JLabel();
        jTxtEmail = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jTxtPassword = new javax.swing.JPasswordField();
        jBtnExit = new javax.swing.JButton();
        jBtnLogin = new javax.swing.JButton();
        jLblLoginLogo = new javax.swing.JLabel();
        jLblForgotPassword = new javax.swing.JLabel();
        jLblRegistration = new javax.swing.JLabel();
        jLblMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("JFLogin"); // NOI18N

        jPanelLoginBg.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(185, 185, 185), 1, true), "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(185, 185, 185))); // NOI18N
        jPanelLoginBg.setName("jPanelLoginBg"); // NOI18N

        jPanelLoginForm.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(185, 185, 185), 1, true));
        jPanelLoginForm.setName("jPanelLoginForm"); // NOI18N

        jLabelEmail.setText("E-mail:");

        jLabelPassword.setText("Password:");

        jBtnExit.setText("Exit");

        jBtnLogin.setText("Login");

        jLblLoginLogo.setText("Login Logo");

        jLblForgotPassword.setText("Forgot Password");

        jLblRegistration.setText("Registration");

        jLblMsg.setText("Please Login");

        javax.swing.GroupLayout jPanelLoginFormLayout = new javax.swing.GroupLayout(jPanelLoginForm);
        jPanelLoginForm.setLayout(jPanelLoginFormLayout);
        jPanelLoginFormLayout.setHorizontalGroup(
            jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginFormLayout.createSequentialGroup()
                        .addComponent(jLblLoginLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtEmail)
                            .addComponent(jTxtPassword)
                            .addComponent(jLblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginFormLayout.createSequentialGroup()
                        .addGap(0, 64, Short.MAX_VALUE)
                        .addComponent(jLblForgotPassword)
                        .addGap(18, 18, 18)
                        .addComponent(jLblRegistration)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnExit)))
                .addContainerGap())
        );
        jPanelLoginFormLayout.setVerticalGroup(
            jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLblLoginLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLoginFormLayout.createSequentialGroup()
                        .addComponent(jLblMsg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEmail)
                            .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPassword)
                            .addComponent(jTxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoginFormLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnExit)
                            .addComponent(jBtnLogin))
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginFormLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelLoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblRegistration)
                            .addComponent(jLblForgotPassword))
                        .addGap(14, 14, 14))))
        );

        javax.swing.GroupLayout jPanelLoginBgLayout = new javax.swing.GroupLayout(jPanelLoginBg);
        jPanelLoginBg.setLayout(jPanelLoginBgLayout);
        jPanelLoginBgLayout.setHorizontalGroup(
            jPanelLoginBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginBgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelLoginForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelLoginBgLayout.setVerticalGroup(
            jPanelLoginBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginBgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelLoginForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelLoginBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelLoginBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new JFLogin().setVisible(true);
                JFLogin jFLogin = new JFLogin();
                jFLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jFLogin.setLocationRelativeTo(null);
                /*jFLogin.setUndecorated(true);*/
                jFLogin.dispose();
                jFLogin.setUndecorated(true);
                if (!jFLogin.isUndecorated()) {
                    //jFLogin.setUndecorated(!jFLogin.isUndecorated());
                }
                jFLogin.setVisible(true);

                jFLogin.addWindowListener(new WindowListener() {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnExit;
    private javax.swing.JButton jBtnLogin;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLblForgotPassword;
    private javax.swing.JLabel jLblLoginLogo;
    private javax.swing.JLabel jLblMsg;
    private javax.swing.JLabel jLblRegistration;
    private javax.swing.JPanel jPanelLoginBg;
    private javax.swing.JPanel jPanelLoginForm;
    private javax.swing.JTextField jTxtEmail;
    private javax.swing.JPasswordField jTxtPassword;
    // End of variables declaration//GEN-END:variables
    public ImageIcon onResizeLabelIcon(String argImageLocation, int argWidth, int argHeight) {
        //ImageIcon imageIcon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login-icon.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        ImageIcon imageIcon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(argImageLocation)).getImage().getScaledInstance(argWidth, argHeight, Image.SCALE_SMOOTH));
        return imageIcon;
    }

    class AppMsgTimer extends TimerTask {

        //int countdown = 100;
        int countdown = 0;

        /*public AppMsgTimer() {
            //
        }*/
        public void run() {
            //countdown = countdown - 1;
            countdown++;
            System.out.println(countdown);
            //label.setText(countdown +"second's left");
            if (countdown > 3) {
                //jLblMsg.setText("Please login");
                jLblMsg.setText("<html><font color='#000000'>Please login</font></html>");
                appTimer.cancel();
                appTimer.purge();
                jTxtEmail.requestFocus();
            }
        }
    }
}
/*
Generate Secure Password Hash : MD5, SHA, PBKDF2, BCrypt Examples
https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
https://dzone.com/articles/storing-passwords-java-web
https://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
https://gist.github.com/dweymouth/11089238

JLabel label = new JLabel("Underlined Label");
Font font = label.getFont();
Map attributes = font.getAttributes();
attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
label.setFont(font.deriveFont(attributes));
 */
