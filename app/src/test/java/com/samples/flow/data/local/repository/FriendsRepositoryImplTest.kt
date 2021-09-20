package com.samples.flow.data.local.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.samples.flow.data.fakes.Data
import com.samples.flow.domain.models.Friend
import com.samples.flow.domain.repository.FriendsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(
    manifest = Config.NONE
)
class FriendsRepositoryImplTest : BaseTest() {

    private lateinit var friendsRepository: FriendsRepository

    @Before
    override fun setup() {
        super.setup()
        friendsRepository = FriendsRepositoryImpl(friendsDao)
    }

    @Test
    fun `Calling InsertFriends Saves The Friend Into The Database`() =
        runBlocking {

            val friend = Data.DomainFriend.friend
            friendsRepository.insertFriend(friend)

            val friendFromDb = friendsRepository.getFriends().first()
            Truth.assertThat(friendFromDb[0]).isEqualTo(friend)


        }

    @Test
    fun `Calling GetFriends, Loads All friends from the Database` ()  =  runBlocking{

        val friend = Data.DomainFriend.friend
        val friend1 = Data.DomainFriend.friend1

        friendsRepository.insertFriend(friend)
        friendsRepository.insertFriend(friend1)


        val friendsList = friendsRepository.getFriends().first()
        Truth.assertThat(friendsList.size).isEqualTo(2)


    }
}
