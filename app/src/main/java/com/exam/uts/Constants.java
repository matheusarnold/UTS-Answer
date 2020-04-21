package com.exam.uts;

public class Constants {

    public static final String DATABASE_NAME = "inventory";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "inventory_table";

    public static final String C_ID = "ID";
    public static final String C_ITEM = "ITEM";
    public static final String C_DESC = "DESC";
    public static final String C_QTY = "QTY";
    public static final String C_ADD_TIMESTAMP = "ADD_TIMESTAMP";
    public static final String C_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_ITEM + " TEXT,"
            + C_DESC + " TEXT,"
            + C_QTY + " TEXT,"
            + C_ADD_TIMESTAMP + " TEXT,"
            + C_UPDATE_TIMESTAMP + " TEXT"
            + ");";
}
