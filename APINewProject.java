/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import App.ConstantsInterface;
import com.rz.Libraries.DBConnection.SQLiteConnection;
import com.rz.Libraries.RandomID;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Rz Rasel
 */
public class APINewProject extends javax.swing.JFrame {

    private JFrame jFrame;
    private List<String> errorList = new ArrayList<String>();
    private SQLiteConnection sQLiteConnection;
    private Connection connection = null;
    private Statement statement = null;
    private String DbName = "SQLiteDbAppApiWordpress.sqlite3";
    private String sqlQuery = "";
    private HashMap<String, Object> mapDbTableData = new HashMap<String, Object>();

    /**
     * Creates new form APIPages
     */
    public APINewProject() {
        initComponents();
        jFrame = this;
        setTitle("API - New Project");
        setResizable(false);
        //setLocationRelativeTo(this);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //sysTblProjectDetails
        sysTblProjectDetails.setRowHeight(28);
        TableColumn tableColumn0 = sysTblProjectDetails.getColumnModel().getColumn(0);
        tableColumn0.setMinWidth(0);
        tableColumn0.setMaxWidth(0);
        tableColumn0.setPreferredWidth(0);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent argWindowEvent) {
                //JFrame jFrameParent = (APIDashboard) jFrame.getRootPane().getParent().getParent();
                //JFrame jFrameParent = (APIDashboard) jFrame.getRootPane().getParent().getParent();
                //jFrameParent.setEnabled(true);
            }
        });
        sqlQuery = " SELECT * FROM tbl_api_projects ORDER BY tap_name ";
        onPopulateDbTable(sqlQuery);
        //System.out.println(".........." + RandomID.getLongId() + "......" + ConstantsInterface.USER_TEMP_INFO.USER_ID);
        sysTxtProjectName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent argDocumentEvent) {
                onTextChange();
            }

            public void removeUpdate(DocumentEvent argDocumentEvent) {
                onTextChange();
            }

            public void insertUpdate(DocumentEvent argDocumentEvent) {
                onTextChange();
            }

            private void onTextChange() {
                sqlQuery = " SELECT * FROM tbl_api_projects "
                        + "WHERE tap_name LIKE '" + sysTxtProjectName.getText() + "%'"
                        + " AND tap_directory_name LIKE '" + sysTxtDirectoryName.getText() + "%'"
                        + " ORDER BY tap_name ";
                onPopulateDbTable(sqlQuery);
            }
        });
        sysTxtDirectoryName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent argDocumentEvent) {
                onTextChange();
            }

            public void removeUpdate(DocumentEvent argDocumentEvent) {
                onTextChange();
            }

            public void insertUpdate(DocumentEvent argDocumentEvent) {
                onTextChange();
            }

            private void onTextChange() {
                sqlQuery = " SELECT * FROM tbl_api_projects "
                        + "WHERE tap_name LIKE '" + sysTxtProjectName.getText() + "%'"
                        + " AND tap_directory_name LIKE '" + sysTxtDirectoryName.getText() + "%'"
                        + " ORDER BY tap_name ";
                onPopulateDbTable(sqlQuery);
            }
        });
    }

    private void onDBInsert(String argTableName, HashMap<String, Object> argMapDbTableData) {
        String columnName = "";
        String columnValue = "";
        sqlQuery = " INSERT INTO " + argTableName + " ";
        int mapCounter = 0;
        for (Entry<String, Object> entry : mapDbTableData.entrySet()) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            columnName += key;
            columnValue += "'" + value + "'";
            if (mapCounter < mapDbTableData.size() - 1) {
                columnName += ", ";
                columnValue += ", ";
            }
            mapCounter++;
        }
        sqlQuery += "( " + columnName + " ) VALUES ( " + columnValue + " )";
        //System.out.println("SQL_QUERY: " + sqlQuery);
        sQLiteConnection = new SQLiteConnection(DbName);
        sQLiteConnection.onOpenConnection();
        sQLiteConnection.onExecuteRawQuery(sqlQuery);
        if (sQLiteConnection != null) {
            sQLiteConnection.onClose();
        }
    }

    private void onPopulateDbTable(String argSqlQuery) {
        sQLiteConnection = new SQLiteConnection(DbName);
        sQLiteConnection.onOpenConnection();
        System.out.println("SQL_QUERY: " + argSqlQuery);
        ResultSet resultSet = sQLiteConnection.onSqlQuery(argSqlQuery);
        try {
            //resultSet.beforeFirst();
            if (resultSet != null) {
                DefaultTableModel tableModel = (DefaultTableModel) sysTblProjectDetails.getModel();
                tableModel.setRowCount(0);
                int rowCounter = 0;
                while (resultSet.next()) {
                    //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString("tap_name"));
                    long tapId = resultSet.getLong("tap_id");
                    String tapName = resultSet.getString("tap_name");
                    String tapDirectoryName = resultSet.getString("tap_directory_name");
                    String tapCreateDate = resultSet.getString("tap_create_date");
                    String tapModifyDate = resultSet.getString("tap_modify_date");
                    long tapCreatedBy = resultSet.getLong("tap_created_by");
                    long tapModifiedBy = resultSet.getLong("tap_modified_by");
                    rowCounter++;
                    Object[] tblRow = {tapId, rowCounter, tapName, tapDirectoryName, tapModifyDate};
                    tableModel.addRow(tblRow);
                }
                tableModel.fireTableDataChanged();
            }
            sQLiteConnection.onCloseResultSet(resultSet);
            //sQLiteConnection.onCloseStatement();
            //sQLiteConnection.onClose();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        }
        if (sQLiteConnection != null) {
            sQLiteConnection.onClose();
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

        idPanFieldHolder = new javax.swing.JPanel();
        idLblProjectName = new javax.swing.JLabel();
        sysTxtProjectName = new javax.swing.JTextField();
        idLblDirectoryName = new javax.swing.JLabel();
        sysTxtDirectoryName = new javax.swing.JTextField();
        idScrlPanTableProjectDetails = new javax.swing.JScrollPane();
        sysTblProjectDetails = new javax.swing.JTable();
        idPanButtonHolder = new javax.swing.JPanel();
        sysBtnNewProject = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        idPanFieldHolder.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        idPanFieldHolder.setName(""); // NOI18N

        idLblProjectName.setText("Project Name:");

        idLblDirectoryName.setText("Directory Name:");

        sysTblProjectDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "SL", "Project Name", "Directory Name", "Modify Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        idScrlPanTableProjectDetails.setViewportView(sysTblProjectDetails);
        if (sysTblProjectDetails.getColumnModel().getColumnCount() > 0) {
            sysTblProjectDetails.getColumnModel().getColumn(1).setMinWidth(32);
            sysTblProjectDetails.getColumnModel().getColumn(1).setPreferredWidth(32);
            sysTblProjectDetails.getColumnModel().getColumn(1).setMaxWidth(32);
        }

        javax.swing.GroupLayout idPanFieldHolderLayout = new javax.swing.GroupLayout(idPanFieldHolder);
        idPanFieldHolder.setLayout(idPanFieldHolderLayout);
        idPanFieldHolderLayout.setHorizontalGroup(
            idPanFieldHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idPanFieldHolderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(idPanFieldHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idScrlPanTableProjectDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addGroup(idPanFieldHolderLayout.createSequentialGroup()
                        .addGroup(idPanFieldHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLblDirectoryName)
                            .addComponent(idLblProjectName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(idPanFieldHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sysTxtDirectoryName, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(sysTxtProjectName))))
                .addContainerGap())
        );
        idPanFieldHolderLayout.setVerticalGroup(
            idPanFieldHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idPanFieldHolderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(idPanFieldHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLblProjectName)
                    .addComponent(sysTxtProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(idPanFieldHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLblDirectoryName)
                    .addComponent(sysTxtDirectoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(idScrlPanTableProjectDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );

        idPanButtonHolder.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        idPanButtonHolder.setName(""); // NOI18N

        sysBtnNewProject.setText("Create Project");
        sysBtnNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sysBtnNewProjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout idPanButtonHolderLayout = new javax.swing.GroupLayout(idPanButtonHolder);
        idPanButtonHolder.setLayout(idPanButtonHolderLayout);
        idPanButtonHolderLayout.setHorizontalGroup(
            idPanButtonHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, idPanButtonHolderLayout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .addComponent(sysBtnNewProject)
                .addContainerGap())
        );
        idPanButtonHolderLayout.setVerticalGroup(
            idPanButtonHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idPanButtonHolderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sysBtnNewProject, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idPanFieldHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idPanButtonHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idPanFieldHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(idPanButtonHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sysBtnNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sysBtnNewProjectActionPerformed
        // TODO add your handling code here:
        errorList.clear();
        errorList.add("Please enter ");
        String projectName = "";
        String directoryName = "";
        projectName = sysTxtProjectName.getText().trim();
        directoryName = sysTxtDirectoryName.getText().trim();
        boolean isProjectNameError = false;
        if (projectName.isEmpty() || projectName.equals("")) {
            errorList.add("project name");
            isProjectNameError = true;
        }
        if (directoryName.isEmpty() || directoryName.equals("")) {
            if (isProjectNameError) {
                errorList.add("and directory name");
            } else {
                errorList.add("directory name");
            }
        }
        if (errorList.size() < 2) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String insertDateTime = simpleDateFormat.format(now);
            mapDbTableData.clear();
            mapDbTableData.put("tap_id", RandomID.getLongId() + "");
            mapDbTableData.put("tap_name", projectName + "");
            mapDbTableData.put("tap_directory_name", directoryName + "");
            mapDbTableData.put("tap_create_date", insertDateTime + "");
            mapDbTableData.put("tap_modify_date", insertDateTime + "");
            mapDbTableData.put("tap_created_by", ConstantsInterface.USER_TEMP_INFO.USER_ID + "");
            mapDbTableData.put("tap_modified_by", ConstantsInterface.USER_TEMP_INFO.USER_ID + "");
            onDBInsert("tbl_api_projects", mapDbTableData);
            sysTxtProjectName.setText("");
            sysTxtDirectoryName.setText("");
            sysTxtProjectName.grabFocus();
            sqlQuery = " SELECT * FROM tbl_api_projects ORDER BY tap_name ";
            onPopulateDbTable(sqlQuery);
        } else {
            String errorStr = String.join(" ", errorList);
            JOptionPane.showMessageDialog(null, errorStr, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sysBtnNewProjectActionPerformed

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
            java.util.logging.Logger.getLogger(APINewProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(APINewProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(APINewProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(APINewProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new APINewProject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idLblDirectoryName;
    private javax.swing.JLabel idLblProjectName;
    private javax.swing.JPanel idPanButtonHolder;
    private javax.swing.JPanel idPanFieldHolder;
    private javax.swing.JScrollPane idScrlPanTableProjectDetails;
    private javax.swing.JButton sysBtnNewProject;
    private javax.swing.JTable sysTblProjectDetails;
    private javax.swing.JTextField sysTxtDirectoryName;
    private javax.swing.JTextField sysTxtProjectName;
    // End of variables declaration//GEN-END:variables
}
