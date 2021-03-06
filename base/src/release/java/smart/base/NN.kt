@file:Suppress("NonAsciiCharacters", "ObjectPropertyName", "SpellCheckingInspection")

package smart.base

import android.log.Log

/** 기본설정은 운영기준*/
object NN {
    const val HOST             = "https://localhost"
    const val Authorization    = "key=AAAA-X6hxEw:APA91bF4HfB1HH-xEZm92DEvGfptstiykL9SSYEZOnP4-1iCLTDLttpZQVPJH5rm5uednhRhAQIYSTeQON0joiEwYWTsfjqYO0-XsvXDvX4vRp-p1W7JvBrLymscG3mEBZUdDuycxxoT"

    @JvmStatic
    fun getUrl(path: String?): String {
        when {
            path.isNullOrBlank() -> Log.w("!앗 주소가 없다", path)
            path.startsWith("http", true) && path.startsWith(HOST) -> Log.w("!앗 주소가 다른서버다", path)
            path.startsWith("file://", true) -> Log.w("!앗 주소가 파일이다", path)
            else -> Unit
        }

        return when {
            path.isNullOrBlank() -> HOST
            path.startsWith("http", true) -> path
            path.startsWith("file://", true) -> path
            else -> HOST + path
        }
    }
}
