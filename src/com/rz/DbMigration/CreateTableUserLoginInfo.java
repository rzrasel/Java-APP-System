/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.DbMigration;

import com.rz.libraries.DBConnection.SQLiteConnection;
import com.rz.libraries.RandomValue;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rz Rasel
 */
public class CreateTableUserLoginInfo {

    private static SQLiteConnection sQLiteConnection;

    public static void main(String args[]) {
        String tblPrefix = DbConostans.DB_INFO.TBL_PREFIX;
        String tblName = "user_login_info";
        String sqlQuery = "";
        sQLiteConnection = SQLiteConnection.getInstance(DbConostans.DB_INFO.DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
        sqlQuery = "DROP TABLE IF EXISTS " + tblPrefix + tblName;
        sQLiteConnection.onExecuteQuery(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + tblPrefix + tblName + " ("
                + " uli_user_id BIGINT(20) NOT NULL,"
                + " uli_identity TEXT NOT NULL,"
                + " uli_password TEXT NOT NULL,"
                + " uli_status BOOLEAN NOT NULL,"
                
                + " uli_create_date DATETIME NOT NULL,"
                + " uli_modify_date DATETIME NOT NULL,"
                + " CONSTRAINT pk_user_login_info_uli_user_id PRIMARY KEY (uli_user_id)"
                + ");";
        sQLiteConnection.onExecuteQuery(sqlQuery);
        String userId = RandomValue.getRandId(1111, 9999);
        userId = "15104726559355";
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        sqlQuery = "INSERT INTO " + tblPrefix + tblName + " VALUES ("
                + "'" + userId + "', "
                + "'3a189103d6787cb92d318edadf6f84e3', " //sysapp@app.com
                + "'67d38bd27ec3655d53b29761686cfe31', " //sysapp123456
                + "1, "
                + "'" + strDate + "', "
                + "'" + strDate + "' "
                + ");";
        sQLiteConnection.onExecuteRawQuery(sqlQuery);
        sQLiteConnection.onCloseStatement();
        sQLiteConnection.onClose();
        System.out.println("ID# " + RandomValue.getRandId(1111, 9999));
        System.out.println("SQL: " + sqlQuery);
    }
}
