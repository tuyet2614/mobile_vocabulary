package com.example.englishvocabulary

import android.widget.Button
import java.io.Serializable

data class Vocab(
    val title: String,
    val mean: String,
    var isChecked: Boolean = false
) : Serializable {

}
