package com.javokhir.reachyourgoal.utils

import androidx.room.Transaction

@Transaction
suspend fun runInTransaction(block: suspend () -> Unit) {
    block()
}