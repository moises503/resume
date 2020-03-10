package com.moises.domain.resume.usecase

import com.moises.domain.core.SingleUseCase
import com.moises.domain.core.exceptions.NullParametersException
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.repository.ResumeRepository
import io.reactivex.Single

class ResumeUseCase(private val resumeRepository: ResumeRepository, jobScheduler: JobScheduler, uiScheduler: UIScheduler)
    : SingleUseCase<Unit, Profile>(uiScheduler, jobScheduler) {

    override fun buildUseCase(params: Unit?): Single<Profile> {
        return params?.let {
            resumeRepository.attemptGetResume()
        } ?: Single.error(NullParametersException("parameters can not be null"))
    }
}