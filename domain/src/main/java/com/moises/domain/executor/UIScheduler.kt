package com.moises.domain.executor

import io.reactivex.Scheduler

interface UIScheduler {
    fun getScheduler(): Scheduler
}