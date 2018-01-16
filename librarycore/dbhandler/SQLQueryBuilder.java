/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore.dbhandler;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Rz Rasel
 */
public class SQLQueryBuilder {
    private static String getSQLInsert(String argTableName, HashMap<String, Object> argMapDbTableData) {
        String sqlQuery = "";
        String columnName = "";
        String columnValue = "";
        sqlQuery = " INSERT INTO " + argTableName + " ";
        int mapCounter = 0;
        for (Entry<String, Object> entry : argMapDbTableData.entrySet()) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            columnName += key;
            columnValue += "'" + value + "'";
            if (mapCounter < argMapDbTableData.size() - 1) {
                columnName += ", ";
                columnValue += ", ";
            }
            mapCounter++;
        }
        sqlQuery += "( " + columnName + " ) VALUES ( " + columnValue + " )";
        System.out.println("SQL_QUERY: " + sqlQuery);
        return sqlQuery;
    }
}
