package com.xfactor.noted.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xfactor.noted.ListItem

@Entity(tableName = "list_table")
data class List(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String
)  
