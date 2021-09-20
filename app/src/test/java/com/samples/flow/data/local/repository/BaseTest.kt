package com.samples.flow.data.local.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.samples.flow.data.local.database.FriendsDatabase
import com.samples.flow.data.local.database.dao.FriendsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import java.io.IOException

open class BaseTest {

    private lateinit var database: FriendsDatabase
    protected lateinit var friendsDao: FriendsDao

    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, FriendsDatabase::class.java).build()
        friendsDao = database.friendsDaoDao
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        runBlocking(Dispatchers.IO) {
            database.clearAllTables()
        }

        database.close()
    }
}