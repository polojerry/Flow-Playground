package com.samples.flow.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends_table")
data class FriendsEntity(

    @ColumnInfo(name = "friend_name")
    @PrimaryKey
    val name: String,
)