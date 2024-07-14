package com.example.laughjoke.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.laughjoke.db.JokesData

@Database(entities = [JokesData::class], version = 1, exportSchema = false)
abstract class JokesDatabase: RoomDatabase() {
    abstract fun jokeDao(): JokesDao

    companion object{
        private var database: JokesDatabase? = null
        fun getInstance(context: Context): JokesDatabase {
            if (database == null){
                database = Room.databaseBuilder(
                    context,
                    JokesDatabase::class.java,
                    "jokes_database"
                ).build()
                return database as JokesDatabase
            }
            return database as JokesDatabase
        }
    }
}