/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.builder.model;

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
        SqlTableBuilder sqlTableBuilder = new SqlTableBuilder();
        sqlTableBuilder.onCreateAuthProject();
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
        sqlQuery = "CREATE TABLE appapi_auth_project (\n"
                + "aaap_CREATE TABLE IF NOT EXISTS appapi_auth_project (project_id BIGINT(20) NOT NULL PRIMARY KEY,\n"
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
