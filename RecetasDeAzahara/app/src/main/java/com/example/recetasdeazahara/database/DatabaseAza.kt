package com.example.recetasdeazahara.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recetasdeazahara.dao.RecetaDAo
import com.example.recetasdeazahara.model.Receta

@Database(entities = [Receta::class], version = 1)
abstract class DatabaseAza : RoomDatabase(){

    abstract  fun recetaDao() : RecetaDAo
}