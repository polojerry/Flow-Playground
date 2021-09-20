package com.samples.flow.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samples.flow.data.local.database.model.FriendsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(friend: FriendsEntity)

    @Query("SELECT * FROM friends_table")
    fun getFriends(): Flow<List<FriendsEntity>>

    @Query("DELETE FROM friends_table")
    suspend fun clearFriends()

}