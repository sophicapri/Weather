package com.example.weather.presentation

sealed interface UiResult<T> {
    class Success<T>(val data: T) : UiResult<T>
    class Loading<T> : UiResult<T>
    class Failure<T> : UiResult<T>
    class None<T> : UiResult<T>
}