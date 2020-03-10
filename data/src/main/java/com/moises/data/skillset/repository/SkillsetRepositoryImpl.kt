package com.moises.data.skillset.repository

import com.moises.domain.skillset.datasource.SkillsetRemoteDataSource
import com.moises.domain.skillset.model.Skillset
import com.moises.domain.skillset.repository.SkillsetRepository
import io.reactivex.Single

class SkillsetRepositoryImpl(private val skillsetRemoteDataSource: SkillsetRemoteDataSource) :
    SkillsetRepository {

    override fun retrieveSkillset(): Single<Skillset> =
        skillsetRemoteDataSource.retrieveSkillset()
}