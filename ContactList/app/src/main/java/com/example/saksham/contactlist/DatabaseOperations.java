package com.example.saksham.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by saksham on 30/4/17.
 */public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_verion = 1;
//    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.Table_name + "(" + TableData.TableInfo.name + " TEXT,"+TableData.TableInfo.contact + " TEXT);"  ;

//    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.Table_name + "(" + TableData.TableInfo.name + " TEXT,"+TableData.TableInfo.contact + " TEXT," + TableData.TableInfo.images2 + " BLOB);" ;


    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.Table_name + "(" + TableData.TableInfo.name + " TEXT,"+TableData.TableInfo.contact + " TEXT," + TableData.TableInfo.email_id + " TEXT," + TableData.TableInfo.description + " TEXT," + TableData.TableInfo.expiry + " TEXT," + TableData.TableInfo.images2 + " BLOB);" ;


    private static final String DATABASE_ALTER_TEAM_1 = "ALTER TABLE " + TableData.TableInfo.Table_name + " ADD COLUMN " + TableData.TableInfo.email_id + " TEXT;";
    private static final String DATABASE_ALTER_TEAM_2 = "ALTER TABLE " + TableData.TableInfo.Table_name + " ADD COLUMN " + TableData.TableInfo.description + " TEXT;";
//    private static final String DATABASE_ALTER_TEAM_3 = "ALTER TABLE " + TableData.TableInfo.Table_name + "  COLUMN " + TableData.TableInfo.expiry + " TEXT;";
   // private static final String DATABASE_ALTER_TEAM_4 = "ALTER TABLE " + TableData.TableInfo.Table_name + "  COLUMN " + TableData.TableInfo.expiry +";";
   // private static final String DATABASE_ALTER_TEAM_5 = "ALTER TABLE " + TableData.TableInfo.Table_name + " ADD COLUMN " + TableData.TableInfo.expiry + " CALENDAR;";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.Database_name, null, database_verion);
        Log.d("Database Oerations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb)
    {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Oerations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables

/*        if(oldVersion<10)
        {

            db.execSQL(DATABASE_ALTER_TEAM_1);
            db.execSQL(DATABASE_ALTER_TEAM_2);
            db.execSQL(DATABASE_ALTER_TEAM_3);

        }

  */      onCreate(db);

    }

    public void putInformation(DatabaseOperations dop, String name, String contact, String email_i, String desc, String exp, byte[] image1)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();

//        SQ.execSQL(DATABASE_ALTER_TEAM_1);
 //       SQ.execSQL(DATABASE_ALTER_TEAM_2);
   //     SQ.execSQL(DATABASE_ALTER_TEAM_3);
   //     SQ.execSQL(DATABASE_ALTER_TEAM_4);
     //   SQ.execSQL(DATABASE_ALTER_TEAM_5);

        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.name,name);
        cv.put(TableData.TableInfo.contact,contact);
        cv.put(TableData.TableInfo.email_id,email_i);
        cv.put(TableData.TableInfo.description,desc);
        cv.put(TableData.TableInfo.expiry,exp);
        cv.put(TableData.TableInfo.images2,image1);
        SQ.insert(TableData.TableInfo.Table_name, null, cv) ;
        Log.d("Database Oerations", "value added");

    }

    public Cursor getInformation(DatabaseOperations dop)
    {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.name, TableData.TableInfo.contact, TableData.TableInfo.images2, TableData.TableInfo.email_id, TableData.TableInfo.description, TableData.TableInfo.expiry};
        Cursor cr = SQ.query(TableData.TableInfo.Table_name, columns, null, null, null, null, TableData.TableInfo.name , null);
        return cr;
    }



    public void deleteUser(DatabaseOperations DOP, String contact)
    {
        String selection = TableData.TableInfo.contact +    " LIKE ?";
//        String columns[] = {TableData.TableInfo.contact};
        String args[] = {contact};
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.Table_name, selection, args);
    }
}

