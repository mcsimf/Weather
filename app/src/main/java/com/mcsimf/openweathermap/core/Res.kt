package com.mcsimf.openweathermap.core

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
data class Res<T>(val state: State, val data: T? = null, val error: Error? = null) {

    data class Error(val msg: String?, val code: Int = 0)

    enum class State {
        LOADING,
        COMPLETE,
        ERROR
    }

    companion object {
        fun <T> loading(data: T? = null): Res<T> = Res(State.LOADING, data)
        fun <T> complete(data: T): Res<T> = Res(State.COMPLETE, data)
        fun <T> error(msg: String? = null, code: Int = 0): Res<T> =
            Res(State.ERROR, error = Error(msg, code))
    }

}