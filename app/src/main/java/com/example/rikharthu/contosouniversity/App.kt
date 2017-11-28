package com.example.rikharthu.contosouniversity

import android.app.Application
import android.content.Context
import com.example.rikharthu.contosouniversity.data.db.ContosoDatabase
import com.example.rikharthu.contosouniversity.data.db.DataManager
import com.example.rikharthu.contosouniversity.data.db.DbSeeder
import timber.log.Timber

class App : Application() {

    private lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        initializeDatabase()

        if (BuildConfig.DEBUG) {
            val seeder = DbSeeder(dataManager.database)
            seeder.seedDatabase()
        }
    }

    private fun initializeDatabase() {
        dataManager = DataManager(this)
    }

    fun getContosoDatabase(): ContosoDatabase {
        return dataManager.database
    }

    companion object {
        fun get(context: Context): App {
            return context.applicationContext as App
        }
    }
}