package com.example.englishvocabulary

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.vocabulary_item.view.*

class vocabAdapter(
    private val vocabs: MutableList<Vocab>
) : RecyclerView.Adapter<vocabAdapter.vocabViewHolder>() {

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
        var vocabulary = vocabs[position]
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
        return vocabs.size
    }
}