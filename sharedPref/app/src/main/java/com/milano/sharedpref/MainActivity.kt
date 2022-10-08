package com.milano.sharedpref

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

const val SHARED_PREFS = "sharedPrefs"
const val TEXT = "text"
const val SWITCH1 = "switch1"

var text = ""
var switchOnOff = false

class MainActivity : AppCompatActivity() {


    val tv: TextView by lazy { findViewById(R.id.tv) }
    val et: EditText by lazy { findViewById<EditText>(R.id.et) }
    val btnApply: Button by lazy { findViewById<Button>(R.id.btnApply) }
    val btnSave: Button by lazy { findViewById<Button>(R.id.btnSave) }
    val switch1: Switch by lazy { findViewById<Switch>(R.id.switch1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnApply.setOnClickListener { tv.text = et.text.toString() }
        btnSave.setOnClickListener { saveData() }
        loadData()
        updateViews()
    }

    private fun loadData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        text = sharedPreferences.getString(TEXT,"").toString()
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false)
    }

    private fun updateViews() {
        tv.setText(text)
        switch1.setChecked(switchOnOff)
    }

    private fun saveData() {
        val sharedPref: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(TEXT, tv.text.toString())
        editor.putBoolean(SWITCH1, switch1.isChecked)

        editor.apply()
        Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show()
    }
}