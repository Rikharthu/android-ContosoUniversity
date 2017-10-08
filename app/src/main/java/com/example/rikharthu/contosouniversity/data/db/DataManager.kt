package com.example.rikharthu.contosouniversity.data.db

import android.arch.persistence.room.Room
import android.content.Context

class DataManager(context: Context) {

    val database: ContosoDatabase

    init {
        database = Room
                .databaseBuilder(context.applicationContext, ContosoDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
    }

    companion object {
        const val DATABASE_NAME = "contoso-database.sqlite"
    }

}