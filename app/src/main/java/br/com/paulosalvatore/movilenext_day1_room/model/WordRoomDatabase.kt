package br.com.paulosalvatore.movilenext_day1_room.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.jetbrains.anko.doAsync

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private var instance: WordRoomDatabase? = null

        private val roomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                instance?.let { roomDb ->
                    doAsync {
                        val dao = roomDb.wordDao()

                        dao.deleteAll()

                        val word = Word("Movile")
                        dao.insert(word)

                        val word2 = Word("Next")
                        dao.insert(word2)
                    }
                }
            }
        }

        fun getDatabase(context: Context): WordRoomDatabase {
            if (instance == null) {
                synchronized(WordRoomDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                    )
                        .addCallback(roomDatabaseCallback)
                        .build()
                }
            }

            return instance!!
        }
    }
}
