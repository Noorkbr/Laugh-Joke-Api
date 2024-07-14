package com.example.laughjoke.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "jokes_table")
data class JokesData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val joke: String? = null,
    val setup: String? = null,
    val delivery: String? = null,
    val type: String
)