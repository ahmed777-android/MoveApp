package com.example.moveapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moveapp.networking.data.Movie
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Movie::class],version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase()  {
    abstract fun getMovieDao():Dao


    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context:Context ):MovieDatabase{
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,MovieDatabase::class.java,"database").fallbackToDestructiveMigration().build()
                INSTANCE =instance
                return instance
            }
        }

    }

}