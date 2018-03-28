package app.keimura.famiie.di

import android.app.Application
import android.arch.persistence.room.Room
import app.keimura.famiie.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule(private val application: Application) {
    @Singleton
    @Provides
    open fun provideDatabase(): AppDatabase =
            Room.databaseBuilder(
                    application, AppDatabase::class.java, "Famiie.db")
                    .fallbackToDestructiveMigration()
                    .build()
}
