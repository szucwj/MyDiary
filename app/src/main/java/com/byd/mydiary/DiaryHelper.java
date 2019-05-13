package com.byd.mydiary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DiaryHelper {

    private static final String TABLE_NAME = "diary";

    public static void insert(SQLiteDatabase db, String title, String content, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        contentValues.put("date", date);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public static void delete(SQLiteDatabase db, String whereClause, String[] whereArgs) {
        db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public static void update(SQLiteDatabase db, ContentValues contentValues, String whereClause, String[] whereArgs) {
        db.update(TABLE_NAME, contentValues, whereClause, whereArgs);
    }

    public static ArrayList<Diary> queryAllData(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from diary", null);
        ArrayList arrayListDiary = new ArrayList<Diary>();
        while (cursor.moveToNext()) {
            Diary diary = new Diary(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("date")));
            arrayListDiary.add(diary);
        }
        cursor.close();
        return arrayListDiary;
    }

    public static Diary queryFromID(SQLiteDatabase db, String[] selecttionArgs) {
        Cursor cursor = db.rawQuery("select * from diary where _id=?", selecttionArgs);
        Diary diary = null;
        while (cursor.moveToNext()) {
            diary = new Diary(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("date")));
        }
        cursor.close();
        return diary;
    }

    public static Diary queryFromDate(SQLiteDatabase db, String[] selecttionArgs) {
        Cursor cursor = db.rawQuery("select * from diary where date=?", selecttionArgs);
        Diary diary = null;
        while (cursor.moveToNext()) {
            diary = new Diary(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("date")));
        }
        cursor.close();
        return diary;
    }

}
