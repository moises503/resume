package com.moises.resume.core.job

import com.moises.domain.core.executor.UIScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


class UIThread : UIScheduler {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}