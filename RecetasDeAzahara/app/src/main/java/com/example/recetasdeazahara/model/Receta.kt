package com.example.recetasdeazahara.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Receta")
data class Receta(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var nombre : String,
    var categoria :Categoria ,




): Parcelable {

    enum class Categoria{
        principal, postrte, segundo
    }
}