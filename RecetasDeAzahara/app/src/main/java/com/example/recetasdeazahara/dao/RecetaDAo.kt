package com.example.recetasdeazahara.dao

import androidx.room.*
import com.example.recetasdeazahara.model.Receta

@Dao
interface RecetaDAo {
    @Query("select * from Receta")
    fun getAllRecetas(): List<Receta>

    @Insert
    fun insertRecetas(r : Receta)


    @Query("delete from Receta where id = :id")
    fun deleterecetaById(id : Int)

    @Delete
    fun deleteRecetas(r : Receta)


    @Update
    fun updateReceta(r : Receta)

    /**

    @Query("Select * from Receta where ")
    fun getCompletador(): List<Receta>

    @Query("Select * from Receta where isPlatoPrincipal")
    fun getCompletador(): List<Receta>

     */






}