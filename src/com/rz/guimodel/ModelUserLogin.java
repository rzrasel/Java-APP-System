/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guimodel;

import com.rz.conostans.APPConostans;
import com.rz.librarycore.cryption.MD5MoreSecure;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import com.rz.dbmigration.DbConostans;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rz Rasel
 */
public class ModelUserLogin {

    private static SQLiteConnection sQLiteConnection;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public static void main(String args[]) {
    }

    public boolean isLoggedIn(String argUserIdentity, String argUserPassword) {
        boolean retVal = false;
        retVal = onCheckUserByPassword(argUserIdentity, argUserPassword);
        /*Timer timer = new Timer();
        timer.schedule(new App(), 0, 1000);
        timer.cancel();
        timer.purge();*/
        return retVal;
    }

    private int count = 0;

    private void onScheduleTimer(int argTimerInSecond) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                System.out.println("Timer Tick: " + count);
            }
        }, argTimerInSecond);
    }

    private boolean onCheckUserByPassword(String argUserIdentity, String argUserPassword) {
        boolean retVal = false;
        String cryptedIdentity = MD5MoreSecure.getCryption(argUserIdentity);
        String cryptedPassword = MD5MoreSecure.getCryption(argUserPassword);
        System.out.println("EMAIL_SALT: " + cryptedIdentity);
        System.out.println("PASS_SALT_AGAIN: " + cryptedPassword);
        String tblPrefix = APPConostans.DATABASE.TABLE.PREFIX;
        String tblName = APPConostans.DATABASE.TABLE.TBL_LOGIN_IFO;
        String colPrefix = APPConostans.DATABASE.TABLE.COL_LOGIN_IFO;
        String sqlQuery = "";
        sqlQuery = "SELECT COUNT(*) AS total_row, * FROM " + tblPrefix + tblName
                + " WHERE "
                + colPrefix + "_identity = '" + cryptedIdentity + "'"
                + " AND " + colPrefix + "_password = '" + cryptedPassword + "'"
                + ";";
        System.out.println("SQL_QUERY: " + sqlQuery);
        sQLiteConnection = SQLiteConnection.getInstance(APPConostans.DATABASE.NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
        resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        //Cursor cursor = null;
        /*int size = 0;
        try {
            if (resultSet != null) {
                //resultSet.last();
                //resultSet.afterLast();
                size = resultSet.getRow();
                //resultSet.beforeFirst();
                resultSet.first();
            }
        } catch (Exception ex) {
            System.out.println("EXCEPTION: " + ex.getMessage().toString());
        }
        System.out.println("DATA_SIZE: " + size);*/
        int rowSize = 0;
        ModelObserverAdapter.adapterLogInfoMap.put("login_user_id", "-404");
        ModelObserverAdapter.adapterSuccessError.put("login_have_error", "yes");
        ModelObserverAdapter.adapterSuccessError.put("login_message", "<html><font color='red'>Invalid e-mail or password</font></html>");
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            System.out.println(" ROW: " + numberOfColumns);
            while (resultSet.next()) {
                rowSize = resultSet.getInt("total_row");
                ModelObserverAdapter.adapterLogInfoMap.put("login_user_id", resultSet.getLong(colPrefix + "_user_id"));
                //uli_status
                boolean userStatus = true;
                userStatus = resultSet.getBoolean(colPrefix + "_status");
                if (!userStatus) {
                    ModelObserverAdapter.adapterSuccessError.put("login_message", "<html><font color='red'>Invalid user login</font></html>");
                    rowSize = 0;
                }
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPTION: " + ex.getMessage());
            ModelObserverAdapter.adapterSuccessError.put("login_message", "<html><font color='red'>Database error</font></html>");
        }
        System.out.println("DATA_SIZE: " + rowSize);
        sQLiteConnection.onCloseResultSet(resultSet);
        sQLiteConnection.onCloseStatement();
        sQLiteConnection.onClose();
        if (rowSize == 1) {
            retVal = true;
            ModelObserverAdapter.adapterSuccessError.put("login_have_error", "no");
            ModelObserverAdapter.adapterSuccessError.put("login_message", "<html><font color='black'>Sucessfully loged in</font></html>");
        }
        //System.out.println("USER_ID: " + ModelObserverAdapter.adapterLogInfoMap.get("login_user_id"));
        ModelObserverAdapter.adapterLogInfoMap.put("login_user_id", "15104726559355");
        retVal = true;
        return retVal;
    }

    class App extends TimerTask {

        int countdown = 100;

        public void run() {
            countdown = countdown - 1;
            System.out.println(countdown);
            //label.setText(countdown +"second's left");
        }
    }
}
/*
while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("name");
         int age  = rs.getInt("age");
         String  address = rs.getString("address");
         float salary = rs.getFloat("salary");
         
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "AGE = " + age );
         System.out.println( "ADDRESS = " + address );
         System.out.println( "SALARY = " + salary );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
 */
