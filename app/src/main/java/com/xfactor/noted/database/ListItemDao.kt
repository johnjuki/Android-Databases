package com.xfactor.noted.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ListItemDao {
    @Query("SELECT * FROM list_item")
    fun getAll() : ListItem

    @Insert
    fun insertAll(vararg listItem: ListItem)

    @Delete
    fun delete(listItem: ListItem)
}