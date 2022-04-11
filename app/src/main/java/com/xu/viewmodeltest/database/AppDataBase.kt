package com.xu.viewmodeltest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xu.viewmodeltest.dao.UserDao
import com.xu.viewmodeltest.entity.User

@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    //单例模式
    companion object {
        private var instance: AppDatabase? = null
        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                .build().apply {
                    instance = this
        } } }
}