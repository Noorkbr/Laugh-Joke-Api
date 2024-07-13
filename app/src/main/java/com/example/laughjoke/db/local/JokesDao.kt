package com.example.jokerjokes.db.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jokerjokes.db.JokesData

@Dao
interface JokesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jokesData: JokesData)

    @Query("SELECT * FROM jokes_table")
    fun getAllJokes(): LiveData<List<JokesData>>

    @Delete
    suspend fun deleteJokes(jokesData: JokesData)
}