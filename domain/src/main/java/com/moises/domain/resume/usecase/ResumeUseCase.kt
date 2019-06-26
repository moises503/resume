package com.moises.domain.resume.usecase

import com.moises.data.resume.repository.ResumeRepository
import com.moises.domain.core.SingleUseCase
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.resume.mapper.ResumeResponseToProfile
import com.moises.domain.resume.model.Profile
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ResumeUseCase(private val resumeRepository: ResumeRepository,
                    private val jobScheduler: JobScheduler,
                    private val uiScheduler: UIScheduler,
                    private val mapper: ResumeResponseToProfile)
    : SingleUseCase<Unit, Profile>(uiScheduler, jobScheduler) {


    override fun buildUseCase(params: Unit?): Single<Profile> {
        return params?.let {
            resumeRepository.attemptGetResume().map { resume ->
                mapper.transform(resume)
            }.subscribeOn(Schedulers.from(jobScheduler))
                .observeOn(uiScheduler.getScheduler())
        } ?: Single.error(AttemptLoginExceptions.GenericException())
    }

    sealed class AttemptLoginExceptions {
        class GenericException : Exception()
    }
}