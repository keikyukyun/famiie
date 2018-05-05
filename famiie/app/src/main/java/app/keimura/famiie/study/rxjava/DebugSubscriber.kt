package app.keimura.famiie.study.rxjava

import io.reactivex.subscribers.DisposableSubscriber

/**
 * デバッグ用のSubscriber。
 */
internal class DebugSubscriber<T> : DisposableSubscriber<T> {
    /** ラベル。 */
    private var label: String?

    constructor() : super() {
        this.label = null
    }

    /**
     * セカンダリコンストラクタ。
     *
     * @param label ラベル
     */
    constructor(label: String?) : super() {
        this.label = label
    }

    override fun onComplete() {
        // onComplete呼び出し時に出力するログ
        val threadName = Thread.currentThread().name
        if (label == null) {
            println("$threadName : 完了")
        }
        println("$threadName : $label : 完了")
    }

    override fun onNext(data: T) {
        // onNext呼び出し時に出力するログ
        val threadName = Thread.currentThread().name
        if (label == null) {
            println("$threadName : $data")
            return
        }
        println("$threadName : $label : $data")
    }

    override fun onError(throwable: Throwable?) {
        // onError呼び出し時に出力するログ
        val threadName = Thread.currentThread().name
        if (label == null) {
            println("$threadName :エラー = $throwable")
            return
        }
        println("$threadName : $label :エラー = $throwable")
    }
}