package com.zeen.mvplibrary.bus

/**
 */

class MessageEvent<T> @JvmOverloads constructor(val eventType: Int, val event: T? = null) {
    companion object {

        /**
         * Quiz server loadingComplete
         */
        const val EVENT_TYPE_QUIZ_SERVER_LOADING_COMPLETE = 0x1001

        /**
         * Quiz process the end
         */
        const val EVENT_TYPE_QUIZ_PROCESS_END = 0x1002

    }
}