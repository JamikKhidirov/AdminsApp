package com.example.adminapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AppealDao {
    @Insert
    suspend fun insert(appeal: Appeal)

    @Update
    suspend fun update(appeal: Appeal)

    @Query("SELECT * FROM appeals ORDER BY date DESC")
    fun getAllAppeals(): Flow<List<Appeal>>

    @Delete
    suspend fun delete(appeal: Appeal)
}