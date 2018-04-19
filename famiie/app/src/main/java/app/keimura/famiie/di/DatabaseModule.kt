package app.keimura.famiie.di

import android.arch.persistence.room.Room
import android.content.Context
import app.keimura.famiie.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule(private val context: Context) {
    @Singleton
    @Provides
    open fun provideDatabase(): AppDatabase =
            Room.databaseBuilder(
                    context, AppDatabase::class.java, "Famiie.db")
                    .fallbackToDestructiveMigration()
                    .build()
}
