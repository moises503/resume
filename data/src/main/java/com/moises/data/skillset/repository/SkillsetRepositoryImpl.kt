package com.moises.data.skillset.repository

import com.moises.domain.skillset.datasource.SkillsetLocalDataSource
import com.moises.domain.skillset.datasource.SkillsetRemoteDataSource
import com.moises.domain.skillset.model.Skillset
import com.moises.domain.skillset.repository.SkillsetRepository
import io.reactivex.Single

class SkillsetRepositoryImpl(
    private val skillsetRemoteDataSource: SkillsetRemoteDataSource,
    private val skillsetLocalDataSource: SkillsetLocalDataSource
) : SkillsetRepository {

    override fun retrieveSkillset(hasInternetConnection: Boolean): Single<Skillset> =
        if (hasInternetConnection) {
            var localSkillset : Single<Skillset>? = null
            val skillset = skillsetRemoteDataSource.retrieveSkillset().doOnSuccess { skillsetData ->
               skillsetLocalDataSource.insertCourses(skillsetData.courses)
            }.doOnError {
                localSkillset = skillsetLocalDataSource.retrieveSkillset()
            }
            localSkillset ?: skillset
        } else {
            skillsetLocalDataSource.retrieveSkillset()
        }

}