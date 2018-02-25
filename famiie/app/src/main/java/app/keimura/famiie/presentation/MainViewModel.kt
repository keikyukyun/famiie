package app.keimura.famiie.presentation

import android.arch.lifecycle.ViewModel
import android.content.Context
import io.reactivex.disposables.CompositeDisposable

/**
 * Mainアクティビティで保持するViewModel。
 */
class MainViewModel(private val context: Context) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}