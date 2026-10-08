package com.learning.networks.model

/** ผลจาก Repository/UseCase มีสองทาง: Error (ซ้าย) หรือ Success (ขวา) */
sealed class Result<out Left, out Right> {
    data class Error<out Left>(val failure: Left) : Result<Left, Nothing>()
    data class Success<out Right>(val success: Right) : Result<Nothing, Right>()

    fun result(fnLeft: (Left) -> Unit, fnRight: (Right) -> Unit) =
        when (this) {
            is Error -> fnLeft(failure)
            is Success -> fnRight(success)
        }
}
