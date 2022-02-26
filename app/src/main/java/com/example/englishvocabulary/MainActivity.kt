package com.example.englishvocabulary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.contains
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_vocabulary.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.vocabulary_item.*
import kotlinx.android.synthetic.main.vocabulary_item.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var vocabAdapter: vocabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vocabAdapter = vocabAdapter(mutableListOf())
        rvVob.adapter = vocabAdapter
        rvVob.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        btnThem.setOnClickListener {
            val intent = Intent(this, addVocabulary::class.java)
            this.startActivityForResult(intent, 1111);

        }
        btnthuchanh.setOnClickListener {
            val intent : Intent = Intent(this, ThucHanh::class.java)
            startActivity(intent)

        }

        btnXoa.setOnClickListener {
            vocabAdapter.deleteDoneTodos()
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1111) {
            val newtitle: String = data?.getStringExtra("Newtitle").toString()
            val newmean: String = data?.getStringExtra("Newmean").toString()
            val dulieu:Vocab = Vocab(newtitle, newmean)
            if (resultCode == Activity.RESULT_OK)
                vocabAdapter.addVocab(dulieu)


        }

    }

}