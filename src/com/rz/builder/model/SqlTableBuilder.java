/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.builder.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Rz Rasel
 */
public class SqlTableBuilder {

    private String tableName = "";
    private String sqlQuery = "";
    private String tablePrefix = "";
    private String colPrefix = "";
    private SqlStringBuilder sqlStringBuilder;

    public static void main(String args[]) {
        HashMap<String, String> mapTblColVal = new HashMap<String, String>();
        mapTblColVal.put("key", "value");
        mapTblColVal.put("kone", "one");
        List<String> keyList = new ArrayList<String>(mapTblColVal.keySet());
        Set<String> keySet = mapTblColVal.keySet();
        System.out.println("DEBUG_PRINT:\n" + keyList);
        System.out.println("DEBUG_PRINT:\n" + keySet);
        /*for (Map.Entry<String, String> e : mapTblColVal.entrySet()) {
            //to get key
            e.getKey();
            //and to get value
            e.getValue();
        }
        for (String key : mapTblColVal.keyset()) {
            //list.add(key + "|" + map.get(key));
        }*/
 /*Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = it.next();
            i += pair.getKey() + pair.getValue();
        }
        Iterator<Integer> itr2 = map.keySet().iterator();
        while (itr2.hasNext()) {
            Integer key = itr2.next();
            i += key + map.get(key);
        }*/
        //SqlTableBuilder sqlTableBuilder = new SqlTableBuilder();
        //sqlTableBuilder.onCreateAuthProject();
        /*Iterator entries = myMap.entrySet().iterator();
        while (entries.hasNext()) {
            Entry thisEntry = (Entry) entries.next();
            Object key = thisEntry.getKey();
            Object value = thisEntry.getValue();
            // ...
        }*/
        /*for (Map.Entry entry : hm.entrySet()) {
            System.out.print("key,val: ");
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
        Iterator iter = hm.keySet().iterator();
        while (iter.hasNext()) {
            Integer key = (Integer) iter.next();
            String val = (String) hm.get(key);
            System.out.println("key,val: " + key + "," + val);
        }*/
    }

    public void onCreateAuthProject() {
        sqlStringBuilder = new SqlStringBuilder();
        tableName = "auth_project";
        sqlQuery = "";
        tablePrefix = "appapi_";
        colPrefix = "aaap_";
        sqlQuery = "CREATE TABLE IF NOT EXISTS " + tablePrefix + tableName + " ( "
                + "project_id BIGINT(20) NOT NULL PRIMARY KEY, "
                + "project_name TEXT NOT NULL  UNIQUE, "
                + "project_details TEXT NULL, "
                + "project_package_bundle TEXT NOT NULL UNIQUE, "
                + "project_release_ver_code TEXT NOT NULL, "
                + "project_release_ver_name TEXT NOT NULL, " //Like release
                + "project_latest_ver_code TEXT NOT NULL, "
                + "project_latest_ver_name TEXT NOT NULL, " //Like latest is 8.2, and valid latest 6+

                + "project_lowest_valid_code TEXT NOT NULL, "
                + "project_lowest_valid_name TEXT NOT NULL, " //Like latest is 4, and valid latest 4/<4
                + "project_status BOOLEAN NOT NULL, " //Active, Inactive Or Published, Unpublished
                + "project_create_date DATETIME NOT NULL, "
                + "project_modify_date DATETIME NOT NULL, "
                + "project_created_by BIGINT(20) NOT NULL, "
                + "project_modified_by BIGINT(20) NOT NULL "
                + "CONSTRAINT pk_" + tableName + "_" + colPrefix + "project_id PRIMARY KEY (" + colPrefix + "project_id),"
                //+ " CONSTRAINT pk_app_api_project_taap_project_id PRIMARY KEY (taap_project_id) "
                + "); ";
        sqlQuery = sqlStringBuilder.onCreateTable(tablePrefix + tableName, sqlQuery, colPrefix);
        sqlQuery = sqlStringBuilder.onSimplifyCreateTable(sqlQuery);
        System.out.println("DEBUG_PRINT:\n" + sqlQuery);
        //////////////////////
        colPrefix = "";
        sqlQuery = "CREATE TABLE IF NOT EXISTS appapi_auth_project (\n"
                + "aaap_project_id BIGINT(20) NOT NULL PRIMARY KEY,\n"
                + "aaap_project_name TEXT NOT NULL UNIQUE,\n"
                + "aaap_project_details TEXT NULL,\n"
                + "aaap_project_package_bundle TEXT NOT NULL UNIQUE,\n"
                + "aaap_project_release_ver_code TEXT NOT NULL,\n"
                + "aaap_project_release_ver_name TEXT NOT NULL,\n"
                + "aaap_project_latest_ver_code TEXT NOT NULL,\n"
                + "aaap_project_latest_ver_name TEXT NOT NULL,\n"
                + "aaap_project_lowest_valid_code TEXT NOT NULL,\n"
                + "aaap_project_lowest_valid_name TEXT NOT NULL,\n"
                + "aaap_project_status BOOLEAN NOT NULL,\n"
                + "aaap_project_create_date DATETIME NOT NULL,\n"
                + "aaap_project_modify_date DATETIME NOT NULL,\n"
                + "aaap_project_created_by BIGINT(20) NOT NULL,\n"
                + "aaap_project_modified_by BIGINT(20) NOT NULL,\n"
                + "CONSTRAINT pk_auth_project_aaap_project_id PRIMARY KEY (aaap_project_id)\n"
                + ");";
        //CONSTRAINT FK_PersonOrder FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
        sqlQuery = sqlStringBuilder.onCreateTable(tablePrefix + tableName, sqlQuery, colPrefix);
        System.out.println("DEBUG_PRINT:\n" + sqlQuery);
    }

    public void onCreateUserRole() {
        sqlStringBuilder = new SqlStringBuilder();
        tableName = "userrole";
        sqlQuery = "";
        tablePrefix = "tbl_";
        colPrefix = "usrl_";
        sqlQuery = "create table " + tablePrefix + tableName + " ("
                + "role_id bigint(20) not null,"
                + "role_title varchar(255) not null,"
                + "role_priority int(3) not null,"
                + "role_is_default boolean not null,"
                + "role_create_date DATETIME NOT NULL, "
                + "role_modify_date DATETIME NOT NULL, "
                + "CONSTRAINT pk_" + tableName + "_" + colPrefix + "role_id PRIMARY KEY (" + colPrefix + "role_id),"
                + "CONSTRAINT uk_" + tableName + "_" + colPrefix + "role_title UNIQUE (" + colPrefix + "role_title)"
                + ");";
        sqlQuery = sqlStringBuilder.onCreateTable(tablePrefix + tableName, sqlQuery, colPrefix);
        sqlQuery = sqlStringBuilder.onSimplifyCreateTable(sqlQuery);
        System.out.println("DEBUG_PRINT:\n" + sqlQuery);
        //////////////////////
        colPrefix = "";
        sqlQuery = "CREATE TABLE tbl_userrole (\n"
                + "usrl_role_id BIGINT(20) NOT NULL,\n"
                + "usrl_role_title VARCHAR(255) NOT NULL,\n"
                + "usrl_role_priority INT(3) NOT NULL,\n"
                + "usrl_role_is_default BOOLEAN NOT NULL,\n"
                + "usrl_role_create_date DATETIME NOT NULL,\n"
                + "usrl_role_modify_date DATETIME NOT NULL,\n"
                + "CONSTRAINT pk_userrole_usrl_role_id PRIMARY KEY (usrl_role_id),\n"
                + "CONSTRAINT uk_userrole_usrl_role_title UNIQUE (usrl_role_title)\n"
                + ");";
        //CONSTRAINT FK_PersonOrder FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
        sqlQuery = sqlStringBuilder.onCreateTable(tablePrefix + tableName, sqlQuery, colPrefix);
        System.out.println("DEBUG_PRINT:\n" + sqlQuery);
    }
}
/*
RegEx to extract the table name from a SQL script file
https://stackoverflow.com/questions/30223057/regex-to-extract-the-table-name-from-a-sql-script-file
Regular Expression to retrieve column and tables from SQL statement
https://stackoverflow.com/questions/20285940/regular-expression-to-retrieve-column-and-tables-from-sql-statement

*/