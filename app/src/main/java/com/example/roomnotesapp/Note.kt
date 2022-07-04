package com.example.roomnotesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note(@ColumnInfo(name = "title")var noteTitle:String,
         @ColumnInfo(name = "desc")var noteDesc:String,
         @ColumnInfo(name = "time")var noteTime:String){
    @PrimaryKey(autoGenerate = true)var id=0 }