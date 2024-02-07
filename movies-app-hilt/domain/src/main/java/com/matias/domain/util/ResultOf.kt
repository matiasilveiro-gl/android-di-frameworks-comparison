package com.matias.domain.util

sealed class ResultOf<out R : Any> {
    data class Success<out R : Any>(val value: R) : ResultOf<R>()
    data class Failure(val throwable: Throwable) : ResultOf<Nothing>()
}
