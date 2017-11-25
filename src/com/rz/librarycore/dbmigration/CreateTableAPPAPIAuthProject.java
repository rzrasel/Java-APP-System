/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore.dbmigration;

import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;

/**
 *
 * @author Rz Rasel
 */
public class CreateTableAPPAPIAuthProject {

    private static SQLiteConnection sQLiteConnection;

    public static void main(String args[]) {
        String tblPrefix = DbConostans.DB_INFO.TBL_PREFIX;
        String tblName = "appapi_auth_project";
        String colPrefix = "aaap";
        String sqlQuery = "";
        sQLiteConnection = SQLiteConnection.getInstance(DbConostans.DB_INFO.DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
        sqlQuery = "DROP TABLE IF EXISTS " + tblPrefix + tblName;
        sQLiteConnection.onExecuteQuery(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + tblPrefix + tblName + " ( "
                + colPrefix + "_project_id BIGINT(20) NOT NULL PRIMARY KEY, "
                + colPrefix + "_project_name TEXT NOT NULL, "
                + colPrefix + "_project_details TEXT NULL, "
                + colPrefix + "_project_package_bundle TEXT NOT NULL, "
                
                + colPrefix + "_project_release_ver_code TEXT NOT NULL, "
                + colPrefix + "_project_release_ver_name TEXT NOT NULL, " //Like release
                + colPrefix + "_project_latest_ver_code TEXT NOT NULL, "
                + colPrefix + "_project_latest_ver_name TEXT NOT NULL, " //Like latest is 8.2, and valid latest 6+

                + colPrefix + "_project_lowest_valid_code TEXT NOT NULL, "
                + colPrefix + "_project_lowest_valid_name TEXT NOT NULL, " //Like latest is 4, and valid latest 4/<4
                + colPrefix + "_project_status BOOLEAN NOT NULL, " //Active, Inactive Or Published, Unpublished
                + colPrefix + "_project_create_date DATETIME NOT NULL, "
                
                + colPrefix + "_project_modify_date DATETIME NOT NULL, "
                + colPrefix + "_project_created_by BIGINT(20) NOT NULL, "
                + colPrefix + "_project_modified_by BIGINT(20) NOT NULL "
                //+ " CONSTRAINT pk_app_api_project_taap_project_id PRIMARY KEY (taap_project_id) "
                + "); ";
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
