package edu.programacion.shop

interface MainAux {

    //funcion para visualizar el fab
    fun hideFab(isVisible: Boolean = false)

    fun addStore(r: Regalo)

    fun updateStore(r: Regalo)
}