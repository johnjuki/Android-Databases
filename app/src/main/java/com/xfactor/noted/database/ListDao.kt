package com.xfactor.noted.database

import androidx.room.*

@Dao
interface ListDao {

    @Query("SELECT * FROM list_table")
    fun getAll() : List

    @Transaction
    @Query("SELECT * From list_table")
    fun getListWithListItem() : kotlin.collections.List<ListWithListItems>

    @Insert
    fun insertAll(vararg lists: List)

    @Delete
    fun deleteAll(list: List)
}
