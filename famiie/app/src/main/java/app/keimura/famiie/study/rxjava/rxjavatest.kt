package app.keimura.famiie.study.rxjava

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * RxJavaの勉強用クラス。
 */
class rxjavatest {
    // subscribeに指定するSubscriberのところでラムダだとフィールド（プロパティ）が持てないので保持。
    lateinit var subscription: Subscription

    /**
     * 文字列の配列のデータを順番に実行し、最後にonCompleteメソッドが呼ばれるサンプル。
     */
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
                    subscription.request(1L)
                }, { error: Throwable ->
                    // エラーを通知された際の処理
                    // onError()
                    error.printStackTrace()
                }, {
                    // 完了を通知された際の処理
                    // onComplete
                    val threadName = Thread.currentThread().name;
                    println("$threadName : 完了しました。")
                }, { subscription: Subscription ->
                    // 購読開始時の処理
                    // onSubscribe
                    this.subscription = subscription
                    this.subscription.request(1L)
                })

        Thread.sleep(500L)
    }

    /**
     * main()のSubscriberをラムダじゃない方法にしてSubscriptionを保持するように。
     */
    fun main2() {
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
                .subscribe(object : Subscriber<String> {
                    lateinit var subscription: Subscription

                    override fun onSubscribe(subscription: Subscription) {
                        this.subscription = subscription
                        this.subscription.request(1L)
                    }

                    override fun onNext(data: String?) {
                        val threadName = Thread.currentThread().name
                        println("$threadName : $data")
                        this.subscription.request(1L)
                    }

                    override fun onError(error: Throwable) {
                        error.printStackTrace()
                    }

                    override fun onComplete() {
                        val threadName = Thread.currentThread().name
                        println("$threadName : 完了しました。")
                    }
                })

        Thread.sleep(500L)
    }

    /********************************************************************************************************************************/
/* BuckpressureStrategy |説明                                                                                                    */
/* ----------------------------------------------------------------------------------------------------------------------------- */
/* BUFFER               | 通知されるまで、すべてのデータをバッファする                                                                 */
/* DROP                 | データを通知できるようになるまで、新たに生成されたデータを破棄する                                              */
/* LATEST               | 生成した最新のデータのみをバッファし、生成されるたびにバッフするデータを置き換える                                */
/* ERROR                | 通知街のデータがバッファ・サイズを超す場合はMissingBackpressureExceptionのエラーを通知する                     */
/* NONE                 | 特定の処理を行わない。主にonBackpressureで始まるメソッドを使ってバックプレッシャーのモードを設定する場合に使われる。 */
    /*********************************************************************************************************************************/

}