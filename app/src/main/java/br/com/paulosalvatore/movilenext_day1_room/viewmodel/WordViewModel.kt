package br.com.paulosalvatore.movilenext_day1_room.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import br.com.paulosalvatore.movilenext_day1_room.model.Word
import br.com.paulosalvatore.movilenext_day1_room.model.WordRepository

class WordViewModel(application: Application) :
    AndroidViewModel(application) {
    private val repository = WordRepository(application)

    val allWords = repository.allWords

    fun insert(word: Word) {
        repository.insert(word)
    }
}
