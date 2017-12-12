/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.builder.model;

import com.rz.librarycore.CommentGenerate;
import com.rz.librarycore.OnFileWrite;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.rz.librarycore.Toast;
import com.rz.librarycore.ToastMessage;

/**
 *
 * @author developer
 */
public class JFModelGenerate extends javax.swing.JFrame {

    public JFModelGenerate jFModelGenerate;

    /**
     * Creates new form JFModelGenerate
     */
    public JFModelGenerate() {
        initComponents();
        jFModelGenerate = this;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //Remove specific character from string from last index
        //System.out.println("REPLACED: " + "aa+ a+bc + ".replaceAll("[\\+|\\s]+$", ""));
        TableColumn tableColumn0 = jTableProperty.getColumnModel().getColumn(0);
        tableColumn0.setMinWidth(26);
        tableColumn0.setMaxWidth(26);
        tableColumn0.setPreferredWidth(26);
        /////////////
        onAddTableBlankRow();
        jBtnAddTblRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddTableBlankRow();
                //System.out.println("CLICKED");
            }
        });
        /////////////
        jBtnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onExtractTableRow();
                //Toast.showToast(jPanel1, "Success");
                ToastMessage toastMessage = new ToastMessage(jFModelGenerate, "Sample text to toast ", 3000);
                toastMessage.setVisible(true);
                //JOptionPane.showMessageDialog(jFModelGenerate, "Succesfully genetated", "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("CLICKED");
            }
        });
        /////////////
        jBtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /////////////
    }

    public void onAddTableBlankRow() {
        /*List<String> rowDataItem = new ArrayList<String>();
        rowDataItem.add("");
        rowDataItem.add("");
        rowDataItem.add("");*/
        DefaultTableModel tableModel = (DefaultTableModel) jTableProperty.getModel();
        //tableModel.setRowCount(0);
        //Object[] tblRow = rowDataItem.toArray();
        int row = tableModel.getRowCount() + 1;
        Object[] tblRow = {row + "", "", ""};
        tableModel.addRow(tblRow);
        //tableModel.fireTableDataChanged();
        ////
        ////
        String[] typeItems = {"int", "String", "boolean", "long", "float", "double",};
        Arrays.sort(typeItems);
        JComboBox jComboBox1 = new JComboBox(typeItems);
        jTableProperty.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jComboBox1));
    }

    public void onExtractTableRow() {
        ///Set<String> letter = new HashSet<String>();
        ArrayList<ArrayList<String>> modelData = new ArrayList<ArrayList<String>>();
        DefaultTableModel tableModel = (DefaultTableModel) jTableProperty.getModel();
        for (int rowCount = 0; rowCount < tableModel.getRowCount(); rowCount++) {
            ArrayList<String> dataItems = new ArrayList<String>();
            int row = rowCount;
            /*for (int columnCount = 0; columnCount < tableModel.getColumnCount(); columnCount++) {
                int column = columnCount;
                String rowValue = tableModel.getValueAt(row, column).toString();
                //System.out.println("VALUE: " + rowValue);
                if (columnCount > 0) {
                    dataItems.add(rowValue);
                }
            }*/
            String rowValueTitle = tableModel.getValueAt(row, 1).toString().trim();
            String rowValueType = tableModel.getValueAt(row, 2).toString().trim();
            if (!rowValueTitle.isEmpty() && !rowValueType.isEmpty()) {
                dataItems.add(rowValueTitle);
                dataItems.add(rowValueType);
                modelData.add(dataItems);
            }
            //modelData.add(dataItems);
        }
        Collections.sort(modelData, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> argItem1, ArrayList<String> argItem2) {
                return argItem1.get(0).toLowerCase().compareTo(argItem2.get(0).toLowerCase());
                /*String value1 = String.valueOf(argItem1.toArray()[0]);
                String value2 = String.valueOf(argItem2.toArray()[0]);
                return value1.compareTo(value2);*/
            }
        });
        System.out.println("VALUE: " + modelData.toString());
        //OnFileWrite fileWriter = new OnFileWrite();
        if (!jTextName.getText().isEmpty() && modelData.size() > 0) {
            onGenerate(jTextName.getText(), modelData);
        }
    }

    public void onGenerate(String argClassName, ArrayList<ArrayList<String>> argCodeData) {
        String retVal = "";
        retVal += "public class " + argClassName + " {";
        retVal += "\n";
        //retVal += argCodeData.toString();
        String varString = "";
        String methodParamConostructor = "";
        String methodSetParamConostructor = "";
        String methodDesc = "";
        String methodToString = "";
        String toStringVal = "";
        String comment = "";
        int counter = 0;
        for (ArrayList<String> items : argCodeData) {
            counter++;
            String methodName = "";
            String varName = items.get(0);
            String varType = items.get(1);
            ////////////
            varString += "private " + varType + " " + varName + ";";
            comment = varName + " is a " + varType + " type variable";
            varString += " " + CommentGenerate.inLine(comment);
            varString += "\n";
            ////////////
            String paramName = "";
            paramName = varName.substring(0, 1).toUpperCase() + varName.substring(1);
            methodParamConostructor += varType + " arg" + paramName + ", ";
            methodSetParamConostructor += "this." + varName + " = " + " arg" + paramName + ";\n";
            methodDesc += onGetterSetterMethod("get", varType, varName);
            methodDesc += "\n";
            methodDesc += onGetterSetterMethod("set", varType, varName);
            methodDesc += "\n";
            ////////////
            toStringVal += "this." + varName + " + ";
            ////////////
        }
        retVal += varString;
        String methodConostructor = "";
        methodParamConostructor = methodParamConostructor.replaceAll("[,|\\s]+$", "");
        methodConostructor += "public " + argClassName + "(" + methodParamConostructor + ") {";
        methodConostructor += "\n";
        methodConostructor += methodSetParamConostructor;
        methodConostructor += "}";
        methodConostructor += "\n";
        retVal += methodConostructor;
        retVal += methodDesc;
        ////////////
        methodToString += onGetterSetterMethod("tostring", argClassName, toStringVal);
        ////////////
        retVal += methodToString;
        retVal += "\n";
        retVal += "}";
        OnFileWrite.onWrite("src/" + argClassName + ".java", retVal);
    }

    public String onGetterSetterMethod(String argMethodType, String argVarType, String argVarName) {
        String methodName = "", methodBody = "";
        String param = "";
        methodName = argVarName.substring(0, 1).toUpperCase() + argVarName.substring(1);
        param = argVarName.substring(0, 1).toUpperCase() + argVarName.substring(1);
        if (argMethodType.equalsIgnoreCase("get")) {
            String comment = "get" + methodName + " method returns " + argVarType;
            methodBody += " " + CommentGenerate.onLine(comment, 1);
            methodBody += "\n";
            methodBody += "public " + argVarType + " " + "get" + methodName + "() {";
            methodBody += "\n";
            methodBody += "return this." + argVarName + ";";
            methodBody += "\n";
            methodBody += "}";
            //methodBody += "\n";
        } else if (argMethodType.equalsIgnoreCase("set")) {
            String comment = "set" + methodName + " method take " + argVarType + " type argument";
            methodBody += " " + CommentGenerate.onLine(comment, 1);
            methodBody += "\n";
            methodBody += "public void " + " " + argMethodType + methodName;
            methodBody += "(" + argVarType + " " + "arg" + param + ") {";
            methodBody += "\n";
            methodBody += "this." + argVarName + " = arg" + param + ";";
            methodBody += "\n";
            methodBody += "}";
            //methodBody += "\n";
        } else if (argMethodType.equalsIgnoreCase("tostring")) {
            methodBody += "public String toString() {";
            methodBody += "\n";
            methodBody += "return \"STRING_VALUE_OF_THE_" + argVarType.toUpperCase() + "_MODEL: \" ";
            methodBody += "+ " + argVarName.replaceAll("[\\+|\\s]+$", "") + ";";
            methodBody += "\n";
            methodBody += "}";
            //methodBody += "\n";
        }
        return methodBody;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProperty = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jBtnClose = new javax.swing.JButton();
        jBtnAddTblRow = new javax.swing.JButton();
        jBtnGenerate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Model Generate");
        setName("JFModelGenerate"); // NOI18N

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(185, 185, 185), 1, true));

        jLabel1.setText("Name:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextName)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(185, 185, 185), 1, true));

        jTableProperty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sl", "Title", "Type"
            }
        ));
        jTableProperty.setGridColor(new java.awt.Color(185, 185, 185));
        jTableProperty.setRowHeight(32);
        jScrollPane1.setViewportView(jTableProperty);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(185, 185, 185), 1, true));

        jBtnClose.setText("Close");

        jBtnAddTblRow.setText("+");

        jBtnGenerate.setText("Generate");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnAddTblRow, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnGenerate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnClose)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnClose)
                    .addComponent(jBtnAddTblRow)
                    .addComponent(jBtnGenerate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(JFModelGenerate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFModelGenerate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFModelGenerate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFModelGenerate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFModelGenerate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAddTblRow;
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnGenerate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProperty;
    private javax.swing.JTextField jTextName;
    // End of variables declaration//GEN-END:variables

}

/*
http://www.java2s.com/Code/Java/Swing-JFC/UsingaJComboBoxinaCellinaJTableComponent.htm
 */
