package com.moises.domain.resume.usecase

import com.moises.domain.core.SingleUseCase
import com.moises.domain.core.exceptions.NullParametersException
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.repository.ResumeRepository
import io.reactivex.Single

class ResumeUseCase(private val resumeRepository: ResumeRepository, jobScheduler: JobScheduler, uiScheduler: UIScheduler)
    : SingleUseCase<ResumeUseCase.ResumeParams, Profile>(uiScheduler, jobScheduler) {

    override fun buildUseCase(params: ResumeParams?): Single<Profile> {
        return params?.let {
            resumeRepository.attemptGetResume(params.hasInternetConnection)
        } ?: Single.error(NullParametersException("parameters can not be null"))
    }

    data class ResumeParams(val hasInternetConnection : Boolean)
}