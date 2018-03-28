package app.keimura.famiie.di

import app.keimura.famiie.presentation.MainActivity
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            DatabaseModule::class
//            ViewModelModule::class
        ]
)
interface AppComponent : AndroidInjector<MainActivity> {
    override fun inject(instance: MainActivity?)
}