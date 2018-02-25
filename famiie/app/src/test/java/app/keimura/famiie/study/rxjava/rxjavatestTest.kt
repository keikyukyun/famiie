package app.keimura.famiie.study.rxjava

import org.junit.Before
import org.junit.Test

class rxjavatestTest {

    private lateinit var rxjavatest: rxjavatest

    @Before
    fun setInit() {
        rxjavatest = rxjavatest()
    }

    @Test
    fun `RxJavaテスト`() {
        // テストとして書いた処理を実行するだけ
        rxjavatest.main()
    }
}