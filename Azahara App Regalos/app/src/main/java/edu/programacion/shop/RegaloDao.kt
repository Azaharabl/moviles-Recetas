package edu.programacion.shop

import androidx.room.*

@Dao
interface RegaloDao {
    //aqui debemos de indicar todas las funciones que vamos a trabajar con Store
    //funcion para mostrar todas los Store
    @Query("SELECT * FROM RegaloEntity")
   suspend fun getAllStore(): MutableList<Regalo>

   //consultar un elemento por medio del id
   @Query("SELECT * FROM RegaloEntity WHERE id = :id")
   suspend fun getStoreById(id: Int): Regalo

    //insertar
    @Insert
    suspend fun addStore(r: Regalo)

    //modificar
    @Update
    suspend fun updateStore(r: Regalo)

    //eliminar
    @Delete
   suspend fun deleteStore(r: Regalo)
}