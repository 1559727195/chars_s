package com.massky.chars_s.single

object SingletionDLC {
    @Volatile
    private var mInstance: SingletionDLC? = null

    private fun SingletionDLC(): SingletionDLC? {
        return this
    }

    fun getmInstance(): SingletionDLC? {
        if (mInstance == null) {
            synchronized(SingletionDLC::class.java) {
                if (mInstance == null) {
                    mInstance = SingletionDLC()
                }
            }
        }
        return mInstance
    }

}