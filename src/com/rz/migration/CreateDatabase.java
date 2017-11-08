/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.migration;

import com.rz.libraries.DBConnection.SQLiteConnection;
import java.sql.Connection;

/**
 *
 * @author Rz Rasel
 */
public class CreateDatabase {
    private static SQLiteConnection sQLiteConnection;
    public static void main(String args[]) {
        sQLiteConnection = SQLiteConnection.getInstance("sqlite/create.sqlite3");
        Connection conn = sQLiteConnection.onOpenConnection();
        sQLiteConnection.onClose();
    }
}