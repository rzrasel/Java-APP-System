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
        String tblPrefix = "tbl_";
        String sqlQuery = "";
        sQLiteConnection = SQLiteConnection.getInstance("sqlite/create.sqlite3");
        Connection conn = sQLiteConnection.onOpenConnection();
        sqlQuery = "DROP TABLE IF EXISTS " + tblPrefix + "app_api_project";
        sQLiteConnection.onSqlExecute(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + tblPrefix + "app_api_project ("
                + " taap_project_id BIGINT(20) NOT NULL,"
                + " taap_project_modified_by BIGINT(20) NULL,"
                + " CONSTRAINT pk_app_api_project_taap_project_id PRIMARY KEY (taap_project_id)"
                + ");";
        sQLiteConnection.onSqlExecute(sqlQuery);
        sQLiteConnection.onClose();
        System.out.println("ID# " + RandomValue.getRandId(1111, 9999));
    }
}
/*
https://github.com/rzrasel/Android-Module-Library
http://www.sqlitetutorial.net/sqlite-java/create-table/

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
