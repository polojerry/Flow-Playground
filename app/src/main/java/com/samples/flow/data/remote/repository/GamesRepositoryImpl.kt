package com.samples.flow.data.remote.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.samples.flow.data.remote.mappers.toDomain
import com.samples.flow.data.remote.models.GameNetwork
import com.samples.flow.domain.models.Game
import com.samples.flow.domain.repository.GamesRepository
import com.samples.flow.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    GamesRepository {
    override suspend fun fetchGames(): Flow<Resource<List<Game>>> = callbackFlow {
        var gamesCollection: CollectionReference? = null

        try {
            gamesCollection = firestore
                .collection("pcgames")
        } catch (t: Throwable) {
            trySend(Resource.Error(t.localizedMessage))

            Timber.e(t)
            close(t)
        }

        val subscription = gamesCollection
            ?.addSnapshotListener { snapshot, _ ->
                if (snapshot == null) {
                    return@addSnapshotListener
                }

                try {
                    val ordersSnapshot =
                        snapshot.toObjects(GameNetwork::class.java).map { order ->
                            order.toDomain()
                        }
                    trySend(Resource.Success(ordersSnapshot))
                } catch (t: Throwable) {
                    trySend(Resource.Error(t.localizedMessage))
                    close(t)
                    Timber.e(t)
                }
            }

        awaitClose {
            subscription?.remove()
        }
    }
}