/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.conostans;

/**
 *
 * @author Rz Rasel
 */
public class APPConostans {

    public interface DATABASE {

        public String NAME = "sqlite/app-system.sqlite3";

        public interface TABLE {

            public String PREFIX = "tbl_";
            ////////////////
            public String TBL_LOGIN_IFO = "user_login_info";
            public String COL_LOGIN_IFO = "uli";
            public String TBL_AUTH_PROJECT = "appapi_auth_project";
            public String COL_AUTH_PROJECT = "aaap";
            public String TBL_AUTH_KEY = "appapi_auth_key";
            public String COL_AUTH_KEY = "aaak";
        }
    }
}
