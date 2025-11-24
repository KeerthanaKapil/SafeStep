package com.example.safestep

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * The main database class.
 * Class is the main access point to the  data.
 * It ensure only one instance of the database is ever created.
 *
 * @property User List of entities included in the database.
 * @property version Version of the database.
 */
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Abstract method to retrieve DAO for the User entity.
     * Room will generate the implementation for this method.
     *
     * @return The User Dao instance for accessing user data.
     */
    abstract fun userDao(): UserDao

    companion object {
        /**
         * The  annotation ensures that the instance variable is always up-to-date.
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Returns the instance of the AppDatabase.
         * If the instance doesn't exist, it is created in a synchronized way.
         *
         * @param context Used to get the app's file path.
         * @return The AppDatabase instance.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "safestep_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
