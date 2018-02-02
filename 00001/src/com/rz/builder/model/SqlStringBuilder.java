/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.builder.model;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

/**
 *
 * @author Rz Rasel
 */
public class SqlStringBuilder {

    public static void main(String args[]) {
        String tableName = "userrole";
        String sqlQuery = "";
        String tablePrefix = "tbl_";
        String colPrefix = "usrl_";
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
        sqlQuery = new SqlStringBuilder().onCreateTable(tablePrefix + tableName, sqlQuery, colPrefix);
        sqlQuery = new SqlStringBuilder().onSimplifyCreateTable(sqlQuery);
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
        sqlQuery = new SqlStringBuilder().onCreateTable(tablePrefix + tableName, sqlQuery, colPrefix);
        System.out.println("DEBUG_PRINT:\n" + sqlQuery);
    }

    public String onSimplifyCreateTable(String argSql) {
        String generatedSql = argSql;
        //generatedSql = generatedSql.replaceAll("\\s+", " ");
        //generatedSql = generatedSql.replaceAll("^\\s+|\\s+$|\\s*(\n)\\s*|(\\s)\\s*", "$1$2").replace("[-]"," ");
        generatedSql = generatedSql.replaceAll("^\\s+|\\s+$|\\s*(\n)\\s*|(\\s)\\s*", "$1$2");
        //System.out.println("DEBUG_PRINT:\n" + generatedSql);
        return generatedSql;
    }

    public String onCreateTable(String argTableName, String argSql, String argColPrefix) {
        String retVal = "";
        String completeSql = "CREATE TABLE IF NOT EXISTS %s (\n%s\n);";
        String originalSql = argSql.replaceAll("[\\s|\n]+", " ");
        //|----|add space in between table name and first bracket
        originalSql = originalSql.replace(argTableName + "(", argTableName + " (");
        //originalSql = originalSql.replace(originalSql, ");");
        String generatedSql = "";
        String modifiedSql = "";
        String createStatement = String.format("CREATE TABLE %s \\(", argTableName);
        modifiedSql = originalSql.replaceAll("(?i)" + createStatement, "").trim();
        createStatement = String.format("CREATE TABLE IF NOT EXISTS %s \\(", argTableName);
        modifiedSql = originalSql.replaceAll("(?i)" + createStatement, "").trim();
        modifiedSql = modifiedSql.replace(");", "").trim();
        //System.out.println("DEBUG_PRINT:\n" + modifiedSql);
        modifiedSql = createTableBuilder(modifiedSql, argColPrefix);
        //modifiedSql = createTableBuilder(modifiedSql, "");
        generatedSql = String.format(completeSql, argTableName, modifiedSql);
        //System.out.println("DEBUG_PRINT:\n" + generatedSql);
        List<String> patternValue = new ArrayList<String>();
        patternValue.add("CONSTRAINT");
        patternValue.add("FOREIGN");
        patternValue.add("FOREIGN KEY");
        patternValue.add("PRIMARY");
        patternValue.add("PRIMARY KEY");
        patternValue.add("REFERENCES");
        patternValue.add("UNIQUE");
        for (String item : patternValue) {
            generatedSql = generatedSql.replaceAll("(?i)" + item, item);
        }
        return generatedSql;
    }

    private String createTableBuilder(String argSql, String argColPrefix) {
        String retVal = "";
        String sqlQueryBulder = argSql;
        sqlQueryBulder = sqlQueryBulder.replaceAll("[\\s|\n]+", " ");
        sqlQueryBulder = sqlQueryBulder.replaceAll("\\(\\s", "\\(");
        sqlQueryBulder = sqlQueryBulder.replaceAll("\\s\\)", "\\)");
        //////////////////////
        String[] splitedByComma = sqlQueryBulder.split(",(?![^()]*\\))");
        StringBuilder queryBuilder = new StringBuilder();
        for (int i = 0; i < splitedByComma.length; i++) {
            String appendValue = "";
            appendValue = repeat(":", 4);
            queryBuilder.append(appendValue);
            splitedByComma[i] = splitedByComma[i].trim();
            //////////////////////
            String[] splitedByWhiteSpace = splitedByComma[i].split("\\s(?![^()]*\\))");
            for (int j = 0; j < splitedByWhiteSpace.length; j++) {
                String splitedValue = splitedByWhiteSpace[j].trim();
                //splitedValue = splitedValue.replaceAll("\\s+$", "");
                if (j == 0) {
                    queryBuilder.append(argColPrefix + splitedValue);
                } else if (j == 1) {
                    if (!splitedByComma[i].toLowerCase().startsWith("CONSTRAINT".toLowerCase())) {
                        queryBuilder.append(splitedValue.toUpperCase());
                    } else {
                        queryBuilder.append(splitedValue);
                    }
                } else if (j == 2) {
                    if (!splitedByComma[i].toLowerCase().startsWith("CONSTRAINT".toLowerCase())) {
                        queryBuilder.append(splitedValue.toUpperCase());
                    } else {
                        queryBuilder.append(splitedValue);
                    }
                } else if (j == 3 && splitedValue.equalsIgnoreCase("null")) {
                    queryBuilder.append(splitedValue.toUpperCase());
                } else {
                    queryBuilder.append(splitedValue);
                }
                if (!splitedValue.isEmpty() && j < splitedByWhiteSpace.length - 1) {
                    int gapLen = 24;
                    //System.out.print("-" + splitedValue + "-" + splitedValue.length());
                    if (j == 0) {
                        gapLen = 36;
                        gapLen = gapLen - splitedValue.length();
                        appendValue = repeat(":", gapLen);
                        queryBuilder.append(appendValue);
                    } else if (j == 1) {
                        gapLen = 24;
                        gapLen = gapLen - splitedValue.length();
                        appendValue = repeat(":", gapLen);
                        queryBuilder.append(appendValue);
                    } else if (j == 2) {
                        gapLen = 12;
                        if (splitedByWhiteSpace.length > 3) {
                            String nextValue = splitedByWhiteSpace[3];
                            if (nextValue.equalsIgnoreCase("NULL")) {
                                appendValue = repeat(":", 1);
                                queryBuilder.append(appendValue);
                            } else {
                                appendValue = repeat(":", 4);
                                queryBuilder.append(appendValue);
                            }
                        }
                    } else {
                        appendValue = repeat(":", 1);
                        queryBuilder.append(appendValue);
                    }
                }
            }
            //////////////////////
            if (i < splitedByComma.length - 1) {
                queryBuilder.append(",\n");
            }
        }
        //////////////////////
        retVal = queryBuilder.toString().replaceAll(":", " ");
        return retVal;
    }

    public static String repeat(String str, int times) {
        if (times > 0) {
            return Stream.generate(() -> str).limit(times).collect(joining());
        } else {
            return ":";
        }
    }

    public static void mainTested2(String args[]) {
        String sqlQuery = "CREATE TABLE CUSTOMERS(\n"
                + "   ID   INT              NOT NULL,\n"
                + "   NAME varchar(20)     NOT NULL,\n"
                + "   AGE  INT              NOT NULL,\n"
                + "   ADDRESS  CHAR(25) ,\n"
                + "   SALARY   DECIMAL(18, 2),       \n"
                + "   PRIMARY KEY(ID)\n"
                + ");";
        sqlQuery = "create table email (id varchar(255) not null PRIMARY);";
        //new SqlStringBuilder().onSimplifyCreateTable(sqlQuery);
        //String sqlQuery = "";
        sqlQuery = new SqlStringBuilder().onCreateTable("email", sqlQuery, "prefix");
        sqlQuery = new SqlStringBuilder().onSimplifyCreateTable(sqlQuery);
        System.out.println("DEBUG_PRINT:\n" + sqlQuery);
    }

    public static void mainTested(String args[]) {
        /*String str = "Hi how are you.\nTest String";
        System.out.println("DEBUG_PRINT: " + str);
        str = str.replaceAll("(\\s|\n)", " ");
        System.out.println("DEBUG_PRINT: " + str);*/
 /*String sqlQuery = "CREATE TABLE CUSTOMERS(\n"
                + "   ID   INT              NOT NULL,\n"
                + "   NAME VARCHAR(20)     NOT NULL,\n"
                + "   AGE  INT              NOT NULL,\n"
                + "   ADDRESS  CHAR(25) ,\n"
                + "   SALARY   DECIMAL(18, 2),       \n"
                + "   PRIMARY KEY(ID)\n"
                + ");";*/
        String sqlQuery = "ID   INT              NOT NULL,\n"
                + "   NAME VARCHAR(20)     NOT NULL,\n"
                + "   AGE  INT              NOT NULL,\n"
                + "   ADDRESS  CHAR(25) UNIQUE DEFAULT ,\n"
                + "   SALARY   DECIMAL(18, 2),       \n"
                + "   PRIMARY KEY(ID)";
        sqlQuery = sqlQuery.replaceAll("[\\s|\n]+", " ");
        sqlQuery = sqlQuery.replaceAll("\\(\\s", "\\(");
        sqlQuery = sqlQuery.replaceAll("\\s\\)", "\\)");
        //sqlQuery = sqlQuery.toUpperCase();
        System.out.println("DEBUG_PRINT: " + sqlQuery);
        //String[] splitedByWhiteSpace = sqlQuery.split("\\s+");
        //String strTest = "a, (b, c, d), e, f, (g, h)";
        //String[] splitedTest = strTest.split(",(?![^()]*\\))");
        //System.out.println("DEBUG_PRINT: " + splitedTest.toString());
        String[] splitedByComma = sqlQuery.split(",(?![^()]*\\))");
        StringBuilder queryBuilder = new StringBuilder();
        for (int i = 0; i < splitedByComma.length; i++) {
            String appendValue = "";
            appendValue = repeat(":", 4);
            queryBuilder.append(appendValue);
            splitedByComma[i] = splitedByComma[i].trim();
            //splitedByComma[i] = splitedByComma[i].replaceFirst(",([^,]+)$", "");
            //queryBuilder.append(splitedByComma[i]);
            //input.split("\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?");
            //String[] splitedByWhiteSpace = splitedByComma[i].split("\\s+");
            //String line = "foo,bar,c;qual=\"baz,blurb\",d;junk=\"quux,syzygy\"";
            //String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            //"[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)"
            /*splitedByComma[i] = "a \"b c d\" e \"f g\" h";
            String[] splitedByWhiteSpace = splitedByComma[i].split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");*/
            //String[] splitedByWhiteSpace = splitedByComma[i].split("\\s");
            //System.out.println("DEBUG_PRINT: " + splitedByComma[i]);
            String[] splitedByWhiteSpace = splitedByComma[i].split("\\s(?![^()]*\\))");
            for (int j = 0; j < splitedByWhiteSpace.length; j++) {
                String splitedValue = splitedByWhiteSpace[j].trim();
                //splitedValue = splitedValue.replaceAll("\\s+$", "");
                queryBuilder.append(splitedValue);
                if (!splitedValue.isEmpty() && j < splitedByWhiteSpace.length - 1) {
                    int gapLen = 24;
                    //System.out.print("-" + splitedValue + "-" + splitedValue.length());
                    if (j == 0) {
                        gapLen = 24;
                        gapLen = gapLen - splitedValue.length();
                        appendValue = repeat(":", gapLen);
                        queryBuilder.append(appendValue);
                    } else if (j == 1) {
                        gapLen = 24;
                        gapLen = gapLen - splitedValue.length();
                        appendValue = repeat(":", gapLen);
                        queryBuilder.append(appendValue);
                    } else if (j == 2) {
                        if (splitedByWhiteSpace.length > 3) {
                            String nextValue = splitedByWhiteSpace[3];
                            if (nextValue.equalsIgnoreCase("NULL")) {
                                appendValue = repeat(":", 1);
                                queryBuilder.append(appendValue);
                            } else {
                                appendValue = repeat(":", 4);
                                queryBuilder.append(appendValue);
                            }
                        }
                    } else {
                        appendValue = repeat(":", 1);
                        queryBuilder.append(appendValue);
                    }
                }
            }
            System.out.println("\n");
            if (i < splitedByComma.length - 1) {
                queryBuilder.append(",\n");
            }
        }
        String sqlBuilder = queryBuilder.toString();
        sqlBuilder = sqlBuilder.replaceAll(":", " ");
        sqlBuilder = "CREATE TABLE (\n" + sqlBuilder + "\n);";
        System.out.println("DEBUG_PRINT: " + sqlBuilder);
        String text = "a \"b c d\" e \"f g\" h";
        String[] splitedByWhiteSpace = text.split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        System.out.println("DEBUG_PRINT: " + splitedByWhiteSpace.toString());
        //String.format("%0" + n + "d", 0).replace("0",s);
        //repeated = String.format(String.format("%%%ds", n), " ").replace(" ",s);
        //String str = repeat(":", 10);
        //System.out.println("DEBUG_PRINT: " + str);
    }
}
