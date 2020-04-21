package com.exam.uts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public long insertInfo(String item, String desc, String qty, String addTimeStamp, String updateTimeStamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_ITEM, item);
        values.put(Constants.C_DESC, desc);
        values.put(Constants.C_QTY, qty);
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void updateInfo(String id, String item, String desc, String qty, String addTimeStamp, String updateTimeStamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_ITEM, item);
        values.put(Constants.C_DESC, desc);
        values.put(Constants.C_QTY, qty);
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }

    public ArrayList<Model> getAllData (String orderBy) {
        ArrayList<Model> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {

            do {
                Model model = new Model (
                        "" + cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_ITEM)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_DESC)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_QTY)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_ADD_TIMESTAMP)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_UPDATE_TIMESTAMP))
                );

                arrayList.add(model);
            } while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }
}
