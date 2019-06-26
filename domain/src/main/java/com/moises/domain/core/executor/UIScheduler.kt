package com.moises.domain.core.executor

import io.reactivex.Scheduler

interface UIScheduler {
    fun getScheduler(): Scheduler
}