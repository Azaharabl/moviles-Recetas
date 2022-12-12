package edu.programacion.shop

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class RegaloApplication: Application() {
    //implementado el patron singleton para que se pueda acceder desde cualquier parte de la app


    companion object{
        lateinit var database: RegaloDatabase
    }

    override fun onCreate() {
        super.onCreate()


        val MIGRATIO_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("ALTER TABLE RegaloEntity ADD COLUMN photoUrl TEXT NOT NULL DEFAULT ''")
            }
        }

        //construimos la database
        database = Room.databaseBuilder(this,
               RegaloDatabase::class.java,
                "RegaloDatabase")
            .addMigrations(MIGRATIO_1_2)
            .build()
    }

}