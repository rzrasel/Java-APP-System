/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore.dbhandler;

/**
 *
 * @author Rz Rasel
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * <p>
 * A public class for <b>SQLite connection</b>. <i>Support singleton
 * pattern</i>, also create an object using <i>new keyword</i>.
 *
 * <br /><br />
 * - Set up SQLite JDBC driver. Download latest version of
 * sqlite-jdbc-(VERSION).jar from sqlite-jdbc repository.
 * <ul>
 * <li>getInstance - SQLiteConnection.getInstance(String argDbFilePath)</li>
 * <li>Create an object using new Keyword new SQLiteConnection(String
 * argDbFilePath)</li>
 * </ul>
 *
 * <br />
 * - <b>Methods</b> inside this class:
 * <ul>
 * <li>public static SQLiteConnection
 * {@link #getInstance(String argDbFilePath) getInstance(String argDbFilePath)}</li>
 * <li>public
 * {@link #SQLiteConnection(String argDbFilePath) SQLiteConnection(String argDbFilePath)}</li>
 * <li>public Connection {@link #onOpenConnection() onOpenConnection()}</li>
 * <li>public void
 * {@link #onExecuteRawQuery(String argSqlQuery) onExecuteRawQuery(String argSqlQuery)}</li>
 * <li>public ResultSet
 * {@link #onSqlQuery(String argSqlQuery) onSqlQuery(String argSqlQuery)}</li>
 * <li>public void
 * {@link #onCloseResultSet(ResultSet argResultSet) onCloseResultSet(ResultSet argResultSet)}</li>
 * <li>public void {@link #onCloseStatement() onCloseStatement()}</li>
 * <li>public void
 * {@link #onCloseStatement(Statement argStatement) onCloseStatement(Statement argStatement)}</li>
 * <li>public void {@link #onCloseConnection() onCloseConnection()}</li>
 * <li>public void {@link #onClose() onClose()}</li>
 * </ul>
 *
 * See {@link
 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.1">HTTP/1.1
 * documentation</a>}. null {@link package.class#member label}
 * {@link com.rz.libraries.SQLiteConnection#getInstance(java.lang.String)}
 * {@link getFoo()}
 * {@link #getInstance(String argDbFilePath) getInstance(String argDbFilePath)}
 *
 * <br /><br />
 * Note: Additional information, e.g. your implementation notes or remarks.
 * </p>
 *
 * @param argDbFilePath use String SQLite database path
 * @version 20171101.0.1
 * @author Rz Rasel
 * @since V-20171101.0.1
 * @see
 * <a href="https://bitbucket.org/xerial/sqlite-jdbc/downloads/">SQLite-Jdbc</a>
 * @note If you spin a quark, it will spin forever!
 */
public class SQLiteConnection {

    private static SQLiteConnection instance = null;
    private String JDBC_DRIVER = "org.sqlite.JDBC";
    private String DB_URL = "jdbc:sqlite:";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * <p>
     * A public static method
     * {@link #getInstance(String argDbFilePath) getInstance(String argDbFilePath)}
     * </p>
     *
     * @param argDbFilePath use String SQLite database path
     * @return SQLiteConnection
     */
    public static SQLiteConnection getInstance(String argDbFilePath) {
        if (instance == null) {
            instance = new SQLiteConnection(argDbFilePath);
        }
        return instance;
    }

    /**
     * <p>
     * A public method
     * {@link #SQLiteConnection(String argDbFilePath) SQLiteConnection(String argDbFilePath)}
     * </p>
     *
     * @param argDbFilePath use String SQLite database path
     */
    public SQLiteConnection(String argDbFilePath) {
        DB_URL += argDbFilePath;
    }

    /**
     * <p>
     * A public method {@link #onOpenConnection() onOpenConnection()}
     * </p>
     *
     * @return Connection
     */
    public Connection onOpenConnection() {
        try {
            //String url = "jdbc:sqlite:test.sqlite3";
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not init JDBC driver - driver not found");
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void onExecuteRawQuery(String argSqlQuery) {
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //int numRowsChanged = pst.executeUpdate(sql);
            statement = connection.createStatement();
            statement.executeUpdate(argSqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean onExecuteQuery(String argSqlQuery) {
        boolean isExecuteQuery = false;
        try {
            statement = connection.createStatement();
            isExecuteQuery = statement.execute(argSqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return isExecuteQuery;
    }

    public ResultSet onSqlQuery(String argSqlQuery) {
        try {
            //statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(argSqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

    public void onCloseResultSet(ResultSet argResultSet) {
        if (argResultSet != null) {
            try {
                argResultSet.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void onCloseStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void onCloseStatement(Statement argStatement) {
        if (argStatement != null) {
            try {
                argStatement.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void onCloseConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void onClose() {
        //onCloseResultSet(resultSet);
        onCloseStatement();
        onCloseConnection();
    }
}
/*
https://www.tutorialspoint.com/sqlite/sqlite_java.htm
https://www.experts-exchange.com/questions/27497760/How-do-I-connect-to-a-Remote-Server-or-Computer-for-updating-a-SQLite-Database.html
https://stackoverflow.com/questions/1525444/how-to-connect-sqlite-with-java
if (c.moveToFirst())
    {
        while ( !c.isAfterLast() ){
           dirArray.add( c.getString( c.getColumnIndex("name")) );
           c.moveToNext();
        }
    }
 */
