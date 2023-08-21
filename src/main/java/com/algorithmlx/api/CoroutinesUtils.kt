package com.algorithmlx.api

import kotlinx.coroutines.delay

suspend fun delay(seconds: Int) {
    delay(m2s(seconds))
}

/**
 * [m2c] it's "Milliseconds to seconds" calculator
 * @param seconds is input as int
 * @return [Long]
 */
fun m2s(seconds: Int): Long = seconds * 1000L
