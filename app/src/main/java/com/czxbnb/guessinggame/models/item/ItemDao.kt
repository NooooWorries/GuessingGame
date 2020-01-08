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

    @Query("SELECT id FROM Item LIMIT 1")
    fun getTopId(): Int

    @Query("SELECT * FROM Item WHERE id = :id")
    fun getItemById(id: Int): Item

    @Query("SELECT COUNT (*) FROM Item WHERE id = :id")
    fun isRowExist(id: Int): Int
}