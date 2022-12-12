package edu.programacion.shop

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RegaloEntity")
data class Regalo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var regalo: String,
    var precio: String = "",
    var notas: String,
    var fecha : String,
    var completado: Boolean = false){


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Regalo

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}


