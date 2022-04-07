package com.xu.viewmodeltest.dao

import androidx.room.*
import com.xu.viewmodeltest.entity.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User): Long

    @Update
    fun update(newuser: User)

    @Delete
    fun delete(user: User)

    @Query("select * from User")
    fun loadAllUses() : List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int
}