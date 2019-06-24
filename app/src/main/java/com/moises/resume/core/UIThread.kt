package com.moises.resume.core

import com.moises.domain.executor.UIScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread: UIScheduler {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}