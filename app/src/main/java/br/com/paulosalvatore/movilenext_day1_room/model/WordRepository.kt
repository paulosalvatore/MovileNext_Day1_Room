package br.com.paulosalvatore.movilenext_day1_room.model

import android.app.Application
import android.arch.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class WordRepository(application: Application) {
    private val wordDao: WordDao
    val allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        wordDao = db.wordDao()
        allWords = wordDao.getAllWords()
    }

    fun insert(word: Word) {
        doAsync { wordDao.insert(word) }
    }
}
