package com.example.englishvocabulary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.core.view.contains
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_vocabulary.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.vocabulary_item.*
import kotlinx.android.synthetic.main.vocabulary_item.view.*
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var vocabAdapter: vocabAdapter
    private val mItemsList: ArrayList<Vocab> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initItem()
        //loadData()

        vocabAdapter = vocabAdapter(mItemsList)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val menuItem = menu?.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        performSearch(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    private fun performSearch(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vocabAdapter.filter.filter(newText)
                return true
            }

        })
    }



    private fun initItem() {

        mItemsList.add(Vocab("hello", "xin chao"))
        mItemsList.add(Vocab("one", "mot"))
        mItemsList.add(Vocab("two", "Hai"))


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