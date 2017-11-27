/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guimodel;

import com.rz.conostans.APPConostans;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rz Rasel
 */
public class ModelAppApiAuthProjectAdd {

    private static SQLiteConnection sQLiteConnection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String tblPrefix = APPConostans.DATABASE.TABLE.PREFIX;
    private String tblName = tblPrefix + APPConostans.DATABASE.TABLE.TBL_AUTH_PROJECT;
    private String colPrefix = APPConostans.DATABASE.TABLE.COL_AUTH_PROJECT;
    private ArrayList<HashMap<String, String>> dbResultSet = new ArrayList<HashMap<String, String>>();

    public static void main(String args[]) {
    }

    public ArrayList<HashMap<String, String>> onPopulateTable(String argSqlQuery) {
        //HashMap<String, Object> mapDbTableData = new HashMap<String, Object>();

        sQLiteConnection = SQLiteConnection.getInstance(APPConostans.DATABASE.NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
        System.out.println("SQL_QUERY: " + argSqlQuery);
        ResultSet resultSet = sQLiteConnection.onSqlQuery(argSqlQuery);
        try {
            //resultSet.beforeFirst();
            if (resultSet != null) {
                dbResultSet.clear();
                int rowCounter = 0;
                while (resultSet.next()) {
                    HashMap<String, String> mapedItem = new HashMap<String, String>();
                    //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString("tap_name"));
                    long rowId = resultSet.getLong(colPrefix + "_project_id");
                    String rowName = resultSet.getString(colPrefix + "_project_name");
                    String rowPackage = resultSet.getString(colPrefix + "_project_package_bundle");
                    String rowStatus = resultSet.getString(colPrefix + "_project_status");
                    String rowRelease = resultSet.getString(colPrefix + "_project_release_ver_name");
                    String rowLatest = resultSet.getString(colPrefix + "_project_latest_ver_name");
                    String rowLowest = resultSet.getString(colPrefix + "_project_lowest_valid_name");
                    mapedItem.put("auth_project_id", rowId + "");
                    mapedItem.put("auth_project_name", rowName);
                    mapedItem.put("auth_project_package", rowPackage);
                    mapedItem.put("auth_project_status", rowStatus);
                    mapedItem.put("auth_project_release_ver_name", rowRelease);
                    mapedItem.put("auth_project_latest_ver_name", rowLatest);
                    mapedItem.put("auth_project_lowest_valid_name", rowLowest);
                    dbResultSet.add(mapedItem);
                    rowCounter++;
                }
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
        return dbResultSet;
    }
}
