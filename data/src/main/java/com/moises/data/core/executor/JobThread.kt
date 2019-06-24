package com.moises.data.core.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import com.moises.domain.executor.JobScheduler

class JobThread: JobScheduler {
    override fun getScheduler(): Scheduler = Schedulers.io()
}