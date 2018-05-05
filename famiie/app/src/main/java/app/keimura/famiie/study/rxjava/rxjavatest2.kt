package app.keimura.famiie.study.rxjava

import io.reactivex.Flowable
import io.reactivex.Scheduler
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

internal class rxjavatest2 {
    /**
     * オペレータ：just
     * 引数のデータを通知するFlowable/Observableの生成
     */
    fun rxjust(item1: String, item2: String, item3: String, item4: String, item5: String): Flowable<String> =
            Flowable.just(item1, item2, item3, item4, item5)

    /**
     * varargsを渡してFlowableを生成。
     */
    fun rxFromArrayVarArgs(vararg items: String): Flowable<String> =
            Flowable.fromArray(*items)

    /**
     * Array型を渡してfromArrayオペレーターを呼び出せる。
     */
    fun rxFromArrayArrayOf(arrays: Array<String>): Flowable<String> =
            Flowable.fromArray(*arrays)

    /**
     * Iterableを渡してFlowableを生成する。
     */
    fun rxFromIterable(list: Iterable<String>): Flowable<String> =
            Flowable.fromIterable(list)

    /**
     * Callableを渡してFlowableを生成する。
     */
    fun rxFromCallable(callable: Callable<String>): Flowable<String> = Flowable.fromCallable(callable)

    /**
     * 指定した開始値から順に指定したデータ数分数値を通知するFlowable/Observableを生成する
     */
    fun rxRange(start: Int, count: Int): Flowable<Int> = Flowable.range(start, count)

    /**
     * rangeと一緒。Flowable<Long>を生成する。
     */
    fun rxRangeLong(start: Long, count: Long): Flowable<Long> = Flowable.rangeLong(start, count)

    /**
     * 指定した間隔で数値を通知するFlowable/Observableを生成する。
     */
    fun rxInterval(time: Long, unit: TimeUnit): Flowable<Long> = Flowable.interval(time, unit)

    /**
     * 指定した時間（time）の後に「0」を通知するFlowable/Observableを生成する。
     */
    fun rxTimer(time: Long, unit: TimeUnit) = Flowable.timer(time, unit)

    /**
     * 指定した時間（time）の後に「0」を通知するFlowable/Observableを生成する。(Schedulerを指定）
     */
    fun rxTimerScheduer(time: Long, unit: TimeUnit, scheduler: Scheduler) = Flowable.timer(time, unit, scheduler)
}