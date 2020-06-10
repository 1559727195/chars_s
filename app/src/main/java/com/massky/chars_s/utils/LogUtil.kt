package com.massky.chars_s.utils


class LogUtil private constructor() {
    val DEGUB = 0
    val INFO = 1
    val ERROR = 2
    val NOTHING = 3
    var level = DEGUB
    fun debug(msg: String?) {
        if (DEGUB >= level) {
            println(msg)
        }
    }

    fun info(msg: String?) {
        if (INFO >= level) {
            println(msg)
        }
    }

    fun error(msg: String?) {
        if (ERROR >= level) {
            println(msg)
        }
    }

    companion object {
        private var sLogUtil: LogUtil? = null
        val instance: LogUtil?
            get() {
                if (sLogUtil == null) {
                    synchronized(LogUtil::class.java) {
                        if (sLogUtil == null) {
                            sLogUtil = LogUtil()
                        }
                    }
                }
                return sLogUtil
            }
    }
}