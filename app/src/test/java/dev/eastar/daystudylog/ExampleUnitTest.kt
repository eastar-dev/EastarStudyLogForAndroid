package dev.eastar.daystudylog

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {



        val str  = ""
        assertNotNull("null 이 아니여야만 함", str)

        val nullStr : String?  = null
        assertNull("null 이여야만 함", nullStr)
    }
}