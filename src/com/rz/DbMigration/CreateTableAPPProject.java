/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.DbMigration;

import com.rz.libraries.DBConnection.SQLiteConnection;
import com.rz.libraries.RandomValue;
import java.sql.Connection;

/**
 *
 * @author Rz Rasel
 */
public class CreateTableAPPProject {

    private static SQLiteConnection sQLiteConnection;

    public static void main(String args[]) {
        String tblPrefix = DbConostans.DB_INFO.TBL_PREFIX;
        String tblName = "app_api_project";
        String sqlQuery = "";
        sQLiteConnection = SQLiteConnection.getInstance(DbConostans.DB_INFO.DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
        sqlQuery = "DROP TABLE IF EXISTS " + tblPrefix + tblName;
        sQLiteConnection.onExecuteQuery(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + tblPrefix + tblName + " ("
                + " taap_project_id BIGINT(20) NOT NULL,"
                + " taap_project_name TEXT NOT NULL,"
                + " taap_project_details TEXT NULL,"
                + " taap_project_package_bundle TEXT NOT NULL,"
                
                + " taap_project_release_ver_code TEXT NOT NULL,"
                + " taap_project_release_ver_name TEXT NOT NULL," //Like release
                + " taap_project_latest_ver_code TEXT NOT NULL,"
                + " taap_project_latest_ver_name TEXT NOT NULL," //Like latest is 8.2, and valid latest 6+
                
                + " taap_project_lowest_valid_code TEXT NOT NULL,"
                + " taap_project_lowest_valid_name TEXT NOT NULL," //Like latest is 4, and valid latest 4/<4
                + " taap_project_status BOOLEAN NOT NULL," //Active, Inactive Or Published, Unpublished
                + " taap_project_create_date DATETIME NOT NULL,"
                
                + " taap_project_modify_date DATETIME NOT NULL,"
                + " taap_project_created_by BIGINT(20) NOT NULL,"
                + " taap_project_modified_by BIGINT(20) NOT NULL,"
                + " CONSTRAINT pk_app_api_project_taap_project_id PRIMARY KEY (taap_project_id)"
                + ");";
        sQLiteConnection.onExecuteQuery(sqlQuery);
        sQLiteConnection.onClose();
        System.out.println("ID# " + RandomValue.getRandId(1111, 9999));
        System.out.println("SQL: " + sqlQuery);
    }
}
/*
https://github.com/rzrasel/Android-Module-Library
http://www.sqlitetutorial.net/sqlite-java/create-table/
https://www.tutorialspoint.com/sql/sql-drop-table.htm

Cursor c = null;
try {
    c = db.rawQuery("select * from " + tableName + " limit 1", null);
    if (c != null) {
        ar = new ArrayList<String>(Arrays.asList(c.getColumnNames()));
    }
} catch (Exception e) {
    Log.v(tableName, e.getMessage(), e);
    e.printStackTrace();
} finally {
    if (c != null)
        c.close();
}
String query = String.format("SELECT %s FROM %s LIMIT 1", column, table);
 */
