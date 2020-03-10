package com.moises.domain.skillset.usecase

import com.moises.domain.core.SingleUseCase
import com.moises.domain.core.exceptions.NullParametersException
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.skillset.model.Skillset
import com.moises.domain.skillset.repository.SkillsetRepository
import io.reactivex.Single

class SkillsetUseCase(private val skillsetRepository: SkillsetRepository, jobScheduler: JobScheduler, uiScheduler: UIScheduler)
    : SingleUseCase<Unit, Skillset>(uiScheduler, jobScheduler) {

    override fun buildUseCase(params: Unit?): Single<Skillset> {
        return params?.let {
            skillsetRepository.retrieveSkillset()
        } ?: Single.error(NullParametersException("parameters can not be null"))
    }
}