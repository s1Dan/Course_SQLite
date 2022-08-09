package com.s1dan.android_lab_8

import android.provider.BaseColumns

// Класс определния имени таблицы и названий столбцов базы данных контактов
class DBContract private constructor() {
    object DBEntry : BaseColumns {
        const val TABLE_NAME = "people"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_ADDRESS = "address"
        const val COLUMN_NAME_BIRTHDAY = "birthday"
    }
}