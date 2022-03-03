package com.example.englishvocabulary

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.vocabulary_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class  vocabAdapter(
    private var vocabs: MutableList<Vocab>
) : RecyclerView.Adapter<vocabAdapter.vocabViewHolder>(), Filterable {
    var vocabularyList : MutableList<Vocab> = vocabs

    class vocabViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vocabViewHolder {
        return vocabViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.vocabulary_item,
                parent,
                false
            )
        )
    }


    fun addVocab(Vocab: Vocab) {
        vocabs.add(Vocab)
        notifyItemInserted(vocabs.size-1)
    }

    fun deleteDoneTodos() {
        vocabs.removeAll {Vocab ->
            Vocab.isChecked
        }
        notifyDataSetChanged()

    }

    private fun toggleStrikeThrough(tvtitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvtitle.paintFlags = tvtitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        }
        else {
            tvtitle.paintFlags = tvtitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: vocabViewHolder, position: Int) {
        var vocabulary = vocabularyList[position]
        holder.itemView.apply {
            tvtitle.text = vocabulary.title
            tvmean.text = vocabulary.mean
            cdcheck.isChecked = vocabulary.isChecked
            toggleStrikeThrough(tvtitle, vocabulary.isChecked)
            cdcheck.setOnCheckedChangeListener{_, isChecked ->
                toggleStrikeThrough(tvtitle, vocabulary.isChecked)
                vocabulary.isChecked = !vocabulary.isChecked
            }

        }
    }



    override fun getItemCount(): Int {
        return vocabularyList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()

                if(charString.isEmpty()){
                    vocabularyList = vocabs
                }
                else {
                    val resultList = ArrayList<Vocab>()
                    for (row in vocabs) {
                        if(row.title.lowercase(Locale.ROOT).
                            contains(charString.lowercase(Locale.ROOT))){
                            resultList.add(row)
                        }
                    }
                    vocabularyList = resultList
                }

                val filteredResults = FilterResults()
                filteredResults.values = vocabularyList
                return filteredResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                vocabularyList = p1?.values as ArrayList<Vocab>
                notifyDataSetChanged()
            }

        }
    }

}