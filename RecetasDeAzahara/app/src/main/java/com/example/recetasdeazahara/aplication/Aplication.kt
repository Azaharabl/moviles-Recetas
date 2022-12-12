package com.example.recetasdeazahara.aplication

import android.app.Application
import android.os.Bundle


import androidx.room.Room
import com.example.recetasdeazahara.database.DatabaseAza


class Aplication : Application() {


    companion object {
        lateinit var database : DatabaseAza
    }

    override  fun onCreate(){
        super.onCreate()

        database = Room.databaseBuilder(
            this, DatabaseAza::class.java,
            "databaseAza").build()

    }



}