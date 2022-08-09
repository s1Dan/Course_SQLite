package com.s1dan.android_lab_8

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnDelete: Button
    private lateinit var btnClear: Button
    private lateinit var btnShow: Button
    private lateinit var btnAdd: Button
    private lateinit var ET_name: EditText
    private lateinit var ET_phone: EditText
    private lateinit var ET_address: EditText
    private lateinit var ET_birthday: EditText
    private lateinit var text: TextView
    private lateinit var DatabaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd = findViewById(R.id.buttonAdd)
        btnAdd.setOnClickListener(this)
        btnShow = findViewById(R.id.buttonShow)
        btnShow.setOnClickListener(this)
        btnDelete = findViewById(R.id.buttonDel)
        btnDelete.setOnClickListener(this)
        btnClear = findViewById(R.id.buttonClear)
        btnClear.setOnClickListener(this)
        ET_name = findViewById(R.id.ET_name)
        ET_phone = findViewById(R.id.ET_phone)
        ET_address = findViewById(R.id.ET_address)
        ET_birthday = findViewById(R.id.ET_birthday)
        text = findViewById(R.id.text)
        DatabaseHelper = DatabaseHelper(this)
        ET_name.setOnClickListener(this)
        ET_phone.setOnClickListener(this)
        ET_address.setOnClickListener(this)
        ET_birthday.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val input_name = ET_name.text.toString()
        val input_phone = ET_phone.text.toString()
        val input_address = ET_address.text.toString()
        val input_birthday = ET_birthday.text.toString()
        val db = DatabaseHelper!!.writableDatabase
        val values = ContentValues()
        when (v.id) {
            R.id.buttonShow -> {
                text!!.text = ""

                // Извлекаем данные
                val projection = arrayOf(
                    DBContract.DBEntry.COLUMN_NAME_NAME,
                    DBContract.DBEntry.COLUMN_NAME_PHONE,
                    DBContract.DBEntry.COLUMN_NAME_ADDRESS,
                    DBContract.DBEntry.COLUMN_NAME_BIRTHDAY)
                val cursor = db.query(
                    DBContract.DBEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
                )
                val indexName = cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_NAME)
                val indexPhone = cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_PHONE)
                val indexAddress = cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_ADDRESS)
                val indexBirthday = cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_BIRTHDAY)
                while (cursor.moveToNext()) {
                    val value_name = cursor.getString(indexName)
                    val value_phone = cursor.getString(indexPhone)
                    val value_address = cursor.getString(indexAddress)
                    val value_birthday = cursor.getString(indexBirthday)
                    text.append("""
                        $value_name 
                        $value_phone 
                        $value_address
                        $value_birthday 
                        """)
                }
                cursor.close()
            }
            R.id.buttonAdd -> {
                values.put(DBContract.DBEntry.COLUMN_NAME_NAME, input_name)
                values.put(DBContract.DBEntry.COLUMN_NAME_PHONE, input_phone)
                values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, input_address)
                values.put(DBContract.DBEntry.COLUMN_NAME_BIRTHDAY, input_birthday)
                db.insert(DBContract.DBEntry.TABLE_NAME, null, values)
                btnShow!!.callOnClick()
            }
            R.id.buttonDel -> {
                val selection = DBContract.DBEntry.COLUMN_NAME_NAME + "=?"
                val selectionArgs = arrayOf(input_name)
                db.delete(DBContract.DBEntry.TABLE_NAME, selection, selectionArgs)
                btnShow!!.callOnClick()
            }
            R.id.buttonClear -> {
                db.delete(DBContract.DBEntry.TABLE_NAME, null, null)
                btnShow!!.callOnClick()
            }
            R.id.ET_phone -> {
                ET_phone!!.text.clear()
                if (ET_name!!.text.toString() == "" || ET_address!!.text.toString() == "" || ET_birthday!!.text.toString() == "") {
                    ET_name!!.setText("Укажите имя")
                    ET_address!!.setText("Укажите адрес")
                    ET_birthday!!.setText("Укажите дату рождения")
                }
            }
            R.id.ET_address -> {
                ET_address!!.text.clear()
                if (ET_name!!.text.toString() == "" || ET_phone!!.text.toString() == "" || ET_birthday!!.text.toString() == "") {
                    ET_name!!.setText("Укажите имя")
                    ET_phone!!.setText("Укажите номер")
                    ET_birthday!!.setText("Укажите дату рождения")
                }
            }
            R.id.ET_name -> {
                ET_name!!.text.clear()
                if (ET_phone!!.text.toString() == "" || ET_address!!.text.toString() == "" || ET_birthday!!.text.toString() == "") {
                    ET_address!!.setText("Укажите номер")
                    ET_address!!.setText("Укажите адрес")
                    ET_birthday!!.setText("Укажите дату рождения")
                }
            }
            R.id.ET_birthday -> {
                ET_birthday!!.text.clear()
                if (ET_phone!!.text.toString() == "" || ET_address!!.text.toString() == "" || ET_name!!.text.toString() == "") {
                    ET_phone!!.setText("Укажите номер")
                    ET_address!!.setText("Укажите адрес")
                    ET_name!!.setText("Укажите имя")
                }
            }
        }
        DatabaseHelper!!.close()
    }
}
