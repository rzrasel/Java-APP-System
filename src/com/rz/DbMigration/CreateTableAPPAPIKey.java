/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.DbMigration;

import com.rz.libraries.DBConnection.SQLiteConnection;
import java.sql.Connection;

/**
 *
 * @author Rz Rasel
 */
public class CreateTableAPPAPIKey {

    private static SQLiteConnection sQLiteConnection;

    public static void main(String args[]) {
        String tblPrefix = DbConostans.DB_INFO.TBL_PREFIX;
        String tblName = "app_api_key";
        String sqlQuery = "";
        sQLiteConnection = SQLiteConnection.getInstance(DbConostans.DB_INFO.DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
        sqlQuery = "DROP TABLE IF EXISTS " + tblPrefix + tblName;
        sQLiteConnection.onExecuteQuery(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + tblPrefix + tblName + " ("
                + " taap_project_id BIGINT(20) NOT NULL,"
                + " taak_api_id BIGINT(20) NOT NULL,"
                + " taak_api_auth_key TEXT NOT NULL,"
                + " taak_api_key_type VERCHAR(255) NOT NULL," //Debug Or Release Type
                
                + " taak_api_usage_type VERCHAR(255) NOT NULL," //OS Type, Android, iOS, Windows Or Other
                + " taak_api_status BOOLEAN NOT NULL,"
                + " taak_api_create_date DATETIME NOT NULL,"
                + " taak_api_modify_date DATETIME NOT NULL,"
                
                + " taak_api_created_by BIGINT(20) NOT NULL,"
                + " taak_api_modified_by BIGINT(20) NOT NULL,"
                + " CONSTRAINT pk_app_api_key_taak_api_id PRIMARY KEY (taak_api_id)"
                + ");";
        sQLiteConnection.onExecuteQuery(sqlQuery);
        sQLiteConnection.onClose();
        System.out.println("SQL: " + sqlQuery);
    }
}
