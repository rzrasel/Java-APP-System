/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guiform;

import com.rz.conostans.APPConostans;
import com.rz.guimodel.ModelAppApiAuthProjectAdd;
import com.rz.guimodel.ModelObserverAdapter;
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Rz Rasel
 */
public class JFAppApiAuthProjectAdd extends javax.swing.JFrame {

    public JFAppApiAuthProjectAdd jFAppApiAuthProjectAdd;

    /**
     * Creates new form JFAppProjects
     */
    public JFAppApiAuthProjectAdd() {
        initComponents();
        jFAppApiAuthProjectAdd = this;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ////////////
        /*jCBoxStatus.addItem(new CBoxItem(1, "Published"));
        jCBoxStatus.addItem(new CBoxItem(0, "Unpublished"));
        jCBoxStatus.setMaximumRowCount(2);
        jCBoxStatus.setPrototypeDisplayValue(" None of the above ");*/
        ////////////
        //jPanel3.setPreferredSize(new Dimension(640, 50));
        System.out.println("USER_ID: " + ModelObserverAdapter.adapterLogInfoMap.get("login_user_id"));
        ////////////
        jTableDetails.setRowHeight(28);
        TableColumn tableColumn0 = jTableDetails.getColumnModel().getColumn(0);
        tableColumn0.setMinWidth(0);
        tableColumn0.setMaxWidth(0);
        tableColumn0.setPreferredWidth(0);
        TableColumn tableColumn1 = jTableDetails.getColumnModel().getColumn(1);
        tableColumn1.setMinWidth(26);
        tableColumn1.setMaxWidth(26);
        tableColumn1.setPreferredWidth(26);
        TableColumn tableColumn4 = jTableDetails.getColumnModel().getColumn(4);
        tableColumn4.setMinWidth(20);
        tableColumn4.setMaxWidth(20);
        tableColumn4.setPreferredWidth(20);
        ////////////
        String tblPrefix = APPConostans.DATABASE.TABLE.PREFIX;
        String tblName = APPConostans.DATABASE.TABLE.TBL_AUTH_PROJECT;
        String colPrefix = APPConostans.DATABASE.TABLE.COL_AUTH_PROJECT;
        onPopulateTable("SELECT * FROM " + tblPrefix + tblName + " ORDER BY " + colPrefix + "_project_name ASC ");
        ////////////
        jTxtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jBtnSave.doClick();
                }
            }
        });
        jTxtReleaseCode.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        jTxtLatestCode.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        jTxtLowestCode.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        jBtnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*ArrayList<FormFieldMeta> arrayList = new ArrayList<FormFieldMeta>();
                arrayList.add(new FormFieldMeta(1, "Name_1"));
                arrayList.add(new FormFieldMeta(5, "Name_5"));
                arrayList.add(new FormFieldMeta(2, "Name_2"));
                Collections.sort(arrayList);

                for (FormFieldMeta str : arrayList) {
                    System.out.println(str.serial + " - " + str.name);
                }
                String firstname = "Hello This is (78Java) Not (.NET6 66)";
                new FieldMeta().onIntRegexMatcher("Hello This is (78Java) Not (.NET6 66)");
                firstname = firstname.replaceAll("[^A-Za-z]", "");
                //firstname = firstname.replaceAll("^([0-9]+)", "");
                firstname = firstname.replaceAll("\\d+", "");
                System.out.println(firstname);*/
                ////
                ArrayList<FormFieldMeta> formFieldMetas = new ArrayList<FormFieldMeta>();
                FormFieldMeta regexFound = new FormFieldMeta();
                int serial = -1;
                boolean isError = false;
                Component[] children = jPanelFormOne.getComponents();
                for (Component item : children) {
                    if (item instanceof JTextField) {
                        System.out.println("TEXT_VALUE: " + ((JTextField) item).getName());
                        /*if (((JTextField) item).getText().isEmpty() || ((JTextField) item).getText() == null) {
                            //isError = true;
                            //item.setFocusable(true);
                            //break;
                            String componentName = ((JTextField) item).getName();
                            serial = regexFound.onIntRegexMatcher(componentName);
                            String name = componentName.replaceAll("[^A-Za-z]", "");
                            formFieldMetas.add(new FormFieldMeta(serial, name, item));
                        }*/
                        if (((JTextField) item).getName() != null) {
                            String componentName = ((JTextField) item).getName();
                            serial = regexFound.onIntRegexMatcher(componentName);
                            String name = componentName.replaceAll("[^A-Za-z]", "");
                            String value = ((JTextField) item).getText().trim();
                            value = value.replaceAll("\\s+", " ");
                            formFieldMetas.add(new FormFieldMeta(serial, name, item, value));
                        }
                    } else if (item instanceof JComboBox) {
                        String componentName = ((JComboBox) item).getName();
                        serial = regexFound.onIntRegexMatcher(componentName);
                        String name = componentName.replaceAll("[^A-Za-z]", "");
                        String value = String.valueOf(((JComboBox) item).getSelectedItem());
                        if (value.equalsIgnoreCase("Published")) {
                            value = "1";
                        } else {
                            value = "0";
                        }
                        formFieldMetas.add(new FormFieldMeta(serial, name, item, value));
                    }
                }
                Collections.sort(formFieldMetas);
                FormFieldMeta errorField = null;
                for (FormFieldMeta item : formFieldMetas) {
                    System.out.println(item.serial + " - " + item.name);
                    if (item.value.isEmpty() || item.value == null) {
                        errorField = item;
                        isError = true;
                        break;
                    }
                }
                if (errorField != null) {
                    Component component = errorField.component;
                    component.requestFocus();
                }
                if (isError) {
                    JOptionPane.showMessageDialog(jFAppApiAuthProjectAdd, "Required field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
                    Date now = new Date();
                    String strDate = sdfDate.format(now);
                    String tblPrefix = APPConostans.DATABASE.TABLE.PREFIX;
                    String tblName = APPConostans.DATABASE.TABLE.TBL_AUTH_PROJECT;
                    String colPrefix = APPConostans.DATABASE.TABLE.COL_AUTH_PROJECT;
                    String sqlQuery = "";
                    String authProjectId = RandomValue.getRandId(1111, 9999);
                    String userId = "15104726559355";
                    StringBuilder stringBuilder = new StringBuilder();
                    for (FormFieldMeta item : formFieldMetas) {
                        stringBuilder.append("'");
                        stringBuilder.append(item.value);
                        stringBuilder.append("', ");
                    }
                    System.out.println("SQL: " + stringBuilder.toString());
                    sqlQuery = "INSERT INTO " + tblPrefix + tblName + " VALUES ("
                            + "'" + authProjectId + "', "
                            + stringBuilder.toString()
                            + "'" + strDate + "', "
                            + "'" + strDate + "', "
                            + "'" + userId + "', "
                            + "'" + userId + "' "
                            + ");";
                    SQLiteConnection sQLiteConnection;
                    sQLiteConnection = SQLiteConnection.getInstance(APPConostans.DATABASE.NAME);
                    Connection conn = sQLiteConnection.onOpenConnection();
                    sQLiteConnection.onExecuteRawQuery(sqlQuery);
                    sQLiteConnection.onCloseStatement();
                    sQLiteConnection.onClose();
                    //void clearAll(Container aContainer)
                    for (FormFieldMeta item : formFieldMetas) {
                        Component component = item.component;
                        if (component instanceof JTextField || component instanceof JTextArea) {
                            ((JTextComponent) component).setText("");
                        }
                        /*else if (component instanceof JRadioButton) {
                            ((JRadioButton) component).setSelected(false);
                        } else if (component instanceof JDateChooser) {
                            ((JDateChooser) component).setDate(null);
                        } else if (component instanceof Container) {
                            //clearAll((Container) component);
                        }*/
                    }
                    formFieldMetas.get(0).component.requestFocus();
                    //https://stackoverflow.com/questions/35554467/how-to-clear-all-the-component-values-in-a-jframe-on-clicking-jbutton
                    //http://www.java2s.com/Tutorials/Java/Swing_How_to/JComboBox/Store_key_value_pair_in_JComboBox.htm
                    //https://stackoverflow.com/questions/5205339/regular-expression-matching-fully-qualified-class-names
                    JOptionPane.showMessageDialog(jFAppApiAuthProjectAdd, "Sucesfully inserted", "Success", JOptionPane.INFORMATION_MESSAGE);
                    ////////////
                    /*String tblPrefix = APPConostans.DATABASE.TABLE.PREFIX;
                    String tblName = tblPrefix + APPConostans.DATABASE.TABLE.TBL_AUTH_PROJECT;
                    String colPrefix = APPConostans.DATABASE.TABLE.COL_AUTH_PROJECT;*/
                    onPopulateTable("SELECT * FROM " + tblPrefix + tblName + " ORDER BY " + colPrefix + "_project_name ASC ");
                    ////////////
                }
            }
        });
        jBtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Close Pressed");
                jFAppApiAuthProjectAdd.dispose();
            }
        });
    }

    public void onPopulateTable(String argSqlQuery) {
        ModelAppApiAuthProjectAdd modelAppApiAuthProjectAdd = new ModelAppApiAuthProjectAdd();
        ArrayList<ArrayList<String>> dbResultSet = new ArrayList<ArrayList<String>>();
        dbResultSet = modelAppApiAuthProjectAdd.onPopulateTable(argSqlQuery);
        System.out.println("SIZE: " + dbResultSet.size());
        if (dbResultSet.size() > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) jTableDetails.getModel();
            tableModel.setRowCount(0);
            int counter = 0;
            for (ArrayList<String> itemsTop : dbResultSet) {
                counter++;
                Object[] tblRow = itemsTop.toArray();
                System.out.println("ROW_VALUE: " + Arrays.toString(tblRow));
                tableModel.addRow(tblRow);
            }
            tableModel.fireTableDataChanged();
        }
        /*ModelAppApiAuthProjectAdd modelAppApiAuthProjectAdd = new ModelAppApiAuthProjectAdd();
        //ArrayList<HashMap<String, String>> dbResultSet = new ArrayList<HashMap<String, String>>();
        ArrayList<HashMap<String, String>> dbResultSet = new ArrayList<HashMap<String, String>>();
        dbResultSet = modelAppApiAuthProjectAdd.onPopulateTable(argSqlQuery);
        System.out.println("SIZE: " + dbResultSet.size());
        if (dbResultSet.size() > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) jTableDetails.getModel();
            //tableModel.setRowCount(0);
            int counter = 0;
            for (HashMap<String, String> itemsTop : dbResultSet) {
                counter++;
                Object[] tblRow = itemsTop.values().toArray();
                System.out.println("ROW_VALUE: " + Arrays.toString(tblRow));
                tableModel.addRow(tblRow);
            }
            tableModel.fireTableDataChanged();
        }*/
 /*try {
            //resultSet.beforeFirst();
            if (resultSet != null) {
                DefaultTableModel tableModel = (DefaultTableModel) jTableDetails.getModel();
                tableModel.setRowCount(0);
                int rowCounter = 0;
                while (resultSet.next()) {
                    //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString("tap_name"));
                    long rowId = resultSet.getLong(colPrefix + "_project_id");
                    String rowName = resultSet.getString(colPrefix + "_project_name");
                    /*String tapDirectoryName = resultSet.getString("tap_directory_name");
                    String tapCreateDate = resultSet.getString("tap_create_date");
                    String tapModifyDate = resultSet.getString("tap_modify_date");
                    long tapCreatedBy = resultSet.getLong("tap_created_by");
                    long tapModifiedBy = resultSet.getLong("tap_modified_by");*--/
                    String rowPackage = resultSet.getString(colPrefix + "_project_package_bundle");
                    String rowStatus = resultSet.getString(colPrefix + "_project_status");
                    String rowRelease = resultSet.getString(colPrefix + "_project_release_ver_name");
                    String rowLatest = resultSet.getString(colPrefix + "_project_latest_ver_name");
                    String rowLowest = resultSet.getString(colPrefix + "_project_lowest_valid_name");
                    rowCounter++;
                    Object[] tblRow = {rowId, rowCounter, rowName, rowPackage, rowStatus, rowRelease, rowLatest, rowLowest,};
                    tableModel.addRow(tblRow);
                }
                tableModel.fireTableDataChanged();
            }
            sQLiteConnection.onCloseResultSet(resultSet);
            //sQLiteConnection.onCloseStatement();
            //sQLiteConnection.onClose();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        }*/
 /*
        hashMap.keySet().toArray(); // returns an array of keys
        hashMap.values().toArray(); // returns an array of values
        for(Entry<String, String> e : m.entrySet()) {
        String key = e.getKey();
        String value = e.getValue();
        }
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFormOne = new javax.swing.JPanel();
        jTxtName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTxtDescription = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTxtPackageBundle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtReleaseCode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtReleaseName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtLatestCode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTxtLatestName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTxtLowestCode = new javax.swing.JTextField();
        jTxtLowestName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCBoxStatus = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetails = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jBtnClose = new javax.swing.JButton();
        jBtnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("App Add Projects");
        setName("JFAppProjects"); // NOI18N
        setResizable(false);

        jPanelFormOne.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(185, 185, 185), 1, true));

        jTxtName.setName("Name1"); // NOI18N

        jLabel1.setText("Name:");

        jLabel2.setText("Description:");

        jTxtDescription.setName("Description2"); // NOI18N

        jLabel3.setText("Package Bundle:");

        jTxtPackageBundle.setName("Package Bundle 3"); // NOI18N

        jLabel4.setText("Release Code:");

        jTxtReleaseCode.setName("Release Code 4"); // NOI18N

        jLabel5.setText("Release Name:");

        jTxtReleaseName.setName("Release Name 5"); // NOI18N

        jLabel6.setText("Latest Code:");

        jTxtLatestCode.setName("Latest Code 6"); // NOI18N

        jLabel7.setText("Latest Name:");

        jTxtLatestName.setName("Latest Name 7"); // NOI18N

        jLabel8.setText("Lowest Code:");

        jTxtLowestCode.setName("Lowest Code 8"); // NOI18N

        jTxtLowestName.setName("Lowest Name 9"); // NOI18N

        jLabel9.setText("Lowest Name:");

        jLabel10.setText("Status:");

        jCBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Published", "Unpublished" }));
        jCBoxStatus.setName("Status 10"); // NOI18N

        javax.swing.GroupLayout jPanelFormOneLayout = new javax.swing.GroupLayout(jPanelFormOne);
        jPanelFormOne.setLayout(jPanelFormOneLayout);
        jPanelFormOneLayout.setHorizontalGroup(
            jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormOneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormOneLayout.createSequentialGroup()
                        .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(36, 36, 36)
                        .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtDescription)
                            .addComponent(jTxtName)))
                    .addGroup(jPanelFormOneLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtPackageBundle))
                    .addGroup(jPanelFormOneLayout.createSequentialGroup()
                        .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelFormOneLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(26, 26, 26)
                                .addComponent(jTxtReleaseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFormOneLayout.createSequentialGroup()
                                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelFormOneLayout.createSequentialGroup()
                                        .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel8))
                                        .addGap(29, 29, 29))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFormOneLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTxtLatestCode)
                                    .addComponent(jTxtLowestCode)
                                    .addComponent(jCBoxStatus, 0, 156, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtLatestName)
                            .addComponent(jTxtReleaseName)
                            .addComponent(jTxtLowestName, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelFormOneLayout.setVerticalGroup(
            jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormOneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtPackageBundle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtReleaseCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jTxtReleaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtLatestCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jTxtLatestName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtLowestCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTxtLowestName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanelFormOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jCBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(185, 185, 185), 1, true));

        jTableDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Sl", "Name", "Package", "Status", "Release", "Latest", "Lowest"
            }
        ));
        jTableDetails.setGridColor(new java.awt.Color(185, 185, 185));
        jTableDetails.setShowGrid(true);
        jScrollPane1.setViewportView(jTableDetails);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(185, 185, 185), 1, true));

        jBtnClose.setText("Close");

        jBtnSave.setText("Save");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnSave)
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
                    .addComponent(jBtnSave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFormOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFormOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            java.util.logging.Logger.getLogger(JFAppApiAuthProjectAdd.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFAppApiAuthProjectAdd.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFAppApiAuthProjectAdd.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFAppApiAuthProjectAdd.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFAppApiAuthProjectAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JComboBox<String> jCBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelFormOne;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDetails;
    private javax.swing.JTextField jTxtDescription;
    private javax.swing.JTextField jTxtLatestCode;
    private javax.swing.JTextField jTxtLatestName;
    private javax.swing.JTextField jTxtLowestCode;
    private javax.swing.JTextField jTxtLowestName;
    private javax.swing.JTextField jTxtName;
    private javax.swing.JTextField jTxtPackageBundle;
    private javax.swing.JTextField jTxtReleaseCode;
    private javax.swing.JTextField jTxtReleaseName;
    // End of variables declaration//GEN-END:variables

    /*private void createComponentMap() {
        componentMap = new HashMap<String, Component>();
        Component[] components = yourForm.getContentPane().getComponents();
        for (int i = 0; i < components.length; i++) {
            componentMap.put(components[i].getName(), components[i]);
        }
    }

    public Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        } else {
            return null;
        }
    }*/
    public class CBoxItem {

        private int id;
        private String description;

        public CBoxItem(int id, String description) {
            this.id = id;
            this.description = description;
        }
    }
}


/*
buttonA.setNextFocusableComponent(buttonB);
\stitle="(.*)?"\s*(/>*)
https://www.jetbrains.com/help/idea/finding-and-replacing-text-in-file-using-regular-expressions.html
https://stackoverflow.com/questions/24256478/pattern-to-extract-text-between-parenthesis
https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java



https://stackoverflow.com/questions/11666356/how-to-get-regex-matched-group-values
https://stackoverflow.com/questions/836704/print-regex-matches-in-java

https://stackoverflow.com/questions/6754552/regex-to-find-a-float-probably-a-really-simple-question
https://stackoverflow.com/questions/13066225/how-to-get-float-value-from-string

https://stackoverflow.com/questions/22652881/get-float-or-integer-value-from-the-string-in-java
https://stackoverflow.com/questions/12234963/java-searching-float-number-in-string/12235002#12235002
https://stackoverflow.com/questions/25707779/regex-to-find-integer-or-decimal-from-a-string-in-java-in-a-single-group
https://stackoverflow.com/questions/12234963/java-searching-float-number-in-string/12235002#12235002


([a-z][a-z_0-9]*\.)*[A-Z_]($[A-Z_]|[\w_])*
^[a-z][a-z0-9_]*(\.[a-z0-9_]+)+$
(\\w+\\.?)+
 */
