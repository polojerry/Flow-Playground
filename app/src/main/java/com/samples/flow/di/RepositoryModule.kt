package com.samples.flow.di

import com.google.firebase.firestore.FirebaseFirestore
import com.samples.flow.data.local.database.FriendsDatabase
import com.samples.flow.data.local.repository.FriendsRepositoryImpl
import com.samples.flow.data.remote.api.MoviesApi
import com.samples.flow.data.remote.repository.GamesRepositoryImpl
import com.samples.flow.data.remote.repository.MoviesRepositoryImpl
import com.samples.flow.domain.repository.FriendsRepository
import com.samples.flow.domain.repository.GamesRepository
import com.samples.flow.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesGamesRepository(firebaseFirestore: FirebaseFirestore): GamesRepository {
        return GamesRepositoryImpl(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun providesFriendsRepository(friendsDatabase: FriendsDatabase): FriendsRepository {
        return FriendsRepositoryImpl(
            friendsDao = friendsDatabase.friendsDaoDao
        )
    }

    @Provides
    @Singleton
    fun providesMoviesRepository(api: MoviesApi): MovieRepository {
        return MoviesRepositoryImpl(api)
    }
}