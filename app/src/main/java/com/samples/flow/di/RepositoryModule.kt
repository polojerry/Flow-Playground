package com.samples.flow.di

import com.google.firebase.firestore.FirebaseFirestore
import com.samples.flow.data.remote.repository.GamesRepositoryImpl
import com.samples.flow.domain.repository.GamesRepository
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
}