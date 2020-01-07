package com.czxbnb.guessinggame.models

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.czxbnb.guessinggame.base.BaseApp
import com.czxbnb.guessinggame.models.item.Item
import com.czxbnb.guessinggame.models.item.ItemDao
import com.czxbnb.guessinggame.models.item.headlines.HeadlinesConverter

@Database(entities = [Item::class], version =1)
@TypeConverters(HeadlinesConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (instance == null) {
                        instance = Room
                            .databaseBuilder(
                                BaseApp.getApplicationContext(),
                                AppDatabase::class.java,
                                "aurora"
                            )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return instance
        }
    }

    abstract fun itemDao(): ItemDao
}