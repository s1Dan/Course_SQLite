package com.s1dan.android_lab_8

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


//Создание и наполнение БД
class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DB_CONTACTS, null, DATABASE_VERSION),
    BaseColumns {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE "
                + DBContract.DBEntry.TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBContract.DBEntry.COLUMN_NAME_NAME + " TEXT, "
                + DBContract.DBEntry.COLUMN_NAME_PHONE + " TEXT, "
                + DBContract.DBEntry.COLUMN_NAME_ADDRESS + " TEXT, "
                + DBContract.DBEntry.COLUMN_NAME_BIRTHDAY + " TEXT)")

        // Заполняем БД
        val values = ContentValues()
        values.put(DBContract.DBEntry.COLUMN_NAME_NAME, "Семен Семенович")
        values.put(DBContract.DBEntry.COLUMN_NAME_PHONE, "8-987-654-32-10")
        values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, "улица А, дом 1, кв 26")
        values.put(DBContract.DBEntry.COLUMN_NAME_BIRTHDAY, "24.07.1963")

        db.insert(DBContract.DBEntry.TABLE_NAME, DBContract.DBEntry.COLUMN_NAME_NAME, values)
        values.put(DBContract.DBEntry.COLUMN_NAME_NAME, "Марья Петровна")
        values.put(DBContract.DBEntry.COLUMN_NAME_PHONE, "8-992-353-66-21")
        values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, "улица АБ, дом 96, кв 431")
        values.put(DBContract.DBEntry.COLUMN_NAME_BIRTHDAY, "17.12.1957")

        db.insert(DBContract.DBEntry.TABLE_NAME, DBContract.DBEntry.COLUMN_NAME_NAME, values)
        values.put(DBContract.DBEntry.COLUMN_NAME_NAME, "Аркадий Викторович")
        values.put(DBContract.DBEntry.COLUMN_NAME_PHONE, "8-800-555-35-35")
        values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, "улица АБВ, дом 6, кв 666")
        values.put(DBContract.DBEntry.COLUMN_NAME_BIRTHDAY, "06.06.1966")

        db.insert(DBContract.DBEntry.TABLE_NAME, DBContract.DBEntry.COLUMN_NAME_NAME, values)
    }

    // действия при обновлении БД
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.DBEntry.TABLE_NAME)
        onCreate(db)
    }

    companion object {
        const val DB_CONTACTS = "contacts.db"
        const val DATABASE_VERSION = 1
    }
}