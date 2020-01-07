package com.czxbnb.guessinggame.models.item

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @get:Query("SELECT * FROM Item")
    val all: List<Item>

    @Insert
    fun insert(item: List<Item>?)

    @Query("DELETE FROM Item")
    fun removeAll()
}