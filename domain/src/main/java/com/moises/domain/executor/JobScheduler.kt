package com.moises.domain.executor

import io.reactivex.Scheduler

interface JobScheduler {
    fun getScheduler(): Scheduler
}