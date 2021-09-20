package com.samples.flow.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samples.flow.data.local.database.dao.FriendsDao
import com.samples.flow.data.local.database.model.FriendsEntity


@Database(entities = [FriendsEntity::class], version = 1, exportSchema = false)
abstract class FriendsDatabase : RoomDatabase() {
    abstract val friendsDaoDao: FriendsDao
}