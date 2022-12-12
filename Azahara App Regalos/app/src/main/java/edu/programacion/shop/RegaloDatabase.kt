package edu.programacion.shop

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Regalo::class), version = 2)
//classs abstract que hereda de RoomDatabase
abstract class RegaloDatabase : RoomDatabase() {
    abstract fun regaloDao(): RegaloDao
}