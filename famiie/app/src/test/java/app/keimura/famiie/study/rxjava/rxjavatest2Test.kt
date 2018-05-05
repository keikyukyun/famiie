package app.keimura.famiie.study.rxjava

import io.reactivex.rxkotlin.subscribeBy
import org.junit.Test
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

/**
 * Rxのお勉強用テスト。
 */
internal class rxjavatest2Test {
    private val test: rxjavatest2 = rxjavatest2()

    private val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("mm:ss.SSS")

    @Test
    fun rxjust() {
        val result = test.rxjust("A", "B", "C", "D", "E")
        result.subscribe(DebugSubscriber("just"))
    }

    @Test
    fun rxFromArrayVarArgs() {
        val result = test.rxFromArrayVarArgs("あ", "か", "さ", "た", "な")
        result.subscribe(DebugSubscriber("array varargs"))
    }

    @Test
    fun rxFromArrayArrayOf() {
        val result = test.rxFromArrayArrayOf(arrayOf("い", "ろ", "は"))
        result.subscribe(DebugSubscriber("array arrayof"))
    }

    @Test
    fun rxFromIterable() {
        val result = test.rxFromIterable(mutableListOf("1", "2", "3", "4", "5"))
        result.subscribe(DebugSubscriber("fromIterable"))
    }

    @Test
    fun rxFromCallable() {
        val a = "A"
        val b = "B"

        val callable = Callable<String> {
            a + b
        }
        val result = test.rxFromCallable(callable)

        // 文字列"A"と"B"を結合した結果を通知する。
        result.subscribe(DebugSubscriber("fromCallable"))
    }

    @Test
    fun rxRange() {
        val start = 10
        val count = 3
        val result = test.rxRange(start, count)

        // 10からの値を、3回通知する（10, 11, 12）
        result.subscribe(DebugSubscriber("range"))
    }

    @Test
    fun rxRangeLong() {
        val start = 10L
        val count = 5L
        val result = test.rxRangeLong(start, count)

        // 10からの値を、5回通知する（10, 11, 12, 13, 14）
        result.subscribe(DebugSubscriber("rangeLong"))
    }

    @Test
    fun rxInterval() {
        val time = 1000L
        val unit = TimeUnit.MILLISECONDS
        val result = test.rxInterval(time, unit)
        val nowTime = LocalTime.now().format(formatter)

        println("開始時間: +  : $nowTime")

        result.subscribe { data ->
            val timeStr: String = LocalTime.now().format(formatter)
            println("${Thread.currentThread().name} : $timeStr : data = $data")
        }

        // メインスレッドを止めてIntervalの購読処理結果を通知させる
        Thread.sleep(5000L)
    }

    @Test
    fun rxTimer() {
        val time = 1000L
        val unit = TimeUnit.MILLISECONDS
        val result = test.rxTimer(time, unit)

        // 開始前の時間
        println("開始時間：${LocalTime.now().format(formatter)}")

        result.subscribeBy(
                onNext = { data ->
                    println("${Thread.currentThread().name}:${LocalTime.now().format(formatter)}:data=$data")
                },
                onError = { error ->
                    println("エラー=$error")
                },
                onComplete = {
                    println("完了")
                })

        // ちょっと待つ
        Thread.sleep(1500L)
    }

    @Test
    fun rxTimerScheduler() {

    }
}