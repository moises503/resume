package com.moises.domain.resume.usecase

import com.moises.domain.core.SingleUseCase
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.repository.ResumeRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ResumeUseCase(private val resumeRepository: ResumeRepository,
                    private val jobScheduler: JobScheduler,
                    private val uiScheduler: UIScheduler)
    : SingleUseCase<Unit, Profile>(uiScheduler, jobScheduler) {


    override fun buildUseCase(params: Unit?): Single<Profile> {
        return params?.let {
            resumeRepository.attemptGetResume()
                .subscribeOn(Schedulers.from(jobScheduler))
                .observeOn(uiScheduler.getScheduler())
        } ?: Single.error(AttemptLoginExceptions.GenericException())
    }

    sealed class AttemptLoginExceptions {
        class GenericException : Exception()
    }
}