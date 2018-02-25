package app.keimura.famiie.study.rxjava

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

/**
 * RxJavaの勉強用クラス。
 */
class rxjavatest {
    private lateinit var subscription: Subscription

    fun main() {
        val flowable: Flowable<String> = Flowable.create<String>({ emitter ->
            val datas: Array<String> = arrayOf("Hello, World!", "こんにちは世界")
            datas.forEach {
                if (emitter.isCancelled) {
                    return@create
                }
                emitter.onNext(it)
            }
            emitter.onComplete()
        }, BackpressureStrategy.BUFFER) // 超過した場合はバッファに。

        flowable
                .observeOn(Schedulers.io()) // これを指定すると次のObservableがメインスレッドなのかバックグラウンドスレッドなのか決められる
                .subscribe({ data ->
                    String // データを受け取ったときの処理
                    // onNext()
                    // 実行しているスレッド名の取得
                    val threadName = Thread.currentThread().name;
                    println("$threadName : $data")
                    this.subscription.request(1L)
                }, { error: Throwable ->
                    // エラーを通知された際の処理
                    // onError()
                    error.printStackTrace()
                }, {
                    // 完了を通知された際の処理
                    // onComplete
                    val threadName = Thread.currentThread().name;
                    println("$threadName : 完了しました。")
                }, { subscription: org.reactivestreams.Subscription ->
                    // 購読開始時の処理
                    // onSubscribe
                    this.subscription = subscription
                    this.subscription.request(1L)
                })

        Thread.sleep(500L)
    }
}