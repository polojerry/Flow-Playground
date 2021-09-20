package com.samples.flow.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.samples.flow.R
import com.samples.flow.data.local.database.FriendsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun providesFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun providesFriendsDatabase(@ApplicationContext context: Context): FriendsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FriendsDatabase::class.java,
            context.resources.getString
                (R.string.friends_database_name)
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}