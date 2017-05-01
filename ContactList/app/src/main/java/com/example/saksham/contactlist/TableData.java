package com.example.saksham.contactlist;

/**
 * Created by saksham on 30/4/17.
 */


import android.provider.BaseColumns;

public class TableData
{
    public TableData()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {
        public static final String name = "user_name";
        public static final String contact = "contact";
        public static final String images2 = "user_image";
        public static final String email_id = "email_id";
        public static final String description = "description";
        public static final String expiry = "expiry";

        public static final String Database_name = "user_info";
        public static final String Table_name = "contact_info";

    }
}
