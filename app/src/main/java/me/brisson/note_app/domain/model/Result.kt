package me.brisson.note_app.domain.model

sealed class Result<out T> {
    data class Success<out R>(val value: R): Result<R>()
    data class Failure(
        val message: String? = null,
        val throwable: Throwable? = null
    ): Result<Nothing>()
}
