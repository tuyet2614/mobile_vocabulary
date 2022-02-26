package com.example.englishvocabulary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_vocabulary.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class addVocabulary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vocabulary)
        btnThemtu.setOnClickListener {
            val intent: Intent = Intent()
            intent.putExtra("Newtitle", txttitle.text.toString())
            intent.putExtra("Newmean", txtmean.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        btnthoat.setOnClickListener {
            val intent: Intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }

    }
}