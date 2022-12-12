package edu.programacion.shop

interface RegaloOnClickListener {

    fun onClick(regaloId: Int)
    fun onFavoriteStore(r: Regalo)
    fun onDeleteStore(r: Regalo)


}