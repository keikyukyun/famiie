package app.keimura.famiie.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import app.keimura.famiie.data.db.dao.UserDao
import app.keimura.famiie.data.db.entity.UserEntity

@Database(
        entities = [
            (UserEntity::class)
        ],
        version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}