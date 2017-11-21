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
public class CreateTableAPPAPIAuthKey {

    private static SQLiteConnection sQLiteConnection;

    public static void main(String args[]) {
        String tblPrefix = DbConostans.DB_INFO.TBL_PREFIX;
        String tblName = "appapi_auth_key";
        String colPrefix = "aaak";
        String sqlQuery = "";
        sQLiteConnection = SQLiteConnection.getInstance(DbConostans.DB_INFO.DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
        sqlQuery = "DROP TABLE IF EXISTS " + tblPrefix + tblName;
        sQLiteConnection.onExecuteQuery(sqlQuery);
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + tblPrefix + tblName + " ( "
                + colPrefix + "_project_id BIGINT(20) NOT NULL PRIMARY KEY, "
                + colPrefix + "_api_id BIGINT(20) NOT NULL, "
                + colPrefix + "_api_auth_key TEXT NOT NULL, "
                + colPrefix + "_api_key_type VERCHAR(255) NOT NULL, " //Debug Or Release Type

                + colPrefix + "_api_usage_type VERCHAR(255) NOT NULL, " //OS Type, Android, iOS, Windows Or Other
                + colPrefix + "_api_status BOOLEAN NOT NULL, "
                + colPrefix + "_api_create_date DATETIME NOT NULL, "
                + colPrefix + "_api_modify_date DATETIME NOT NULL, "
                + colPrefix + "_api_created_by BIGINT(20) NOT NULL, "
                + colPrefix + "_api_modified_by BIGINT(20) NOT NULL "
                //+ " CONSTRAINT pk_app_api_key_taak_api_id PRIMARY KEY (taak_api_id)"
                + "); ";
        sQLiteConnection.onExecuteQuery(sqlQuery);
        sQLiteConnection.onClose();
        System.out.println("SQL: " + sqlQuery);
    }
}
