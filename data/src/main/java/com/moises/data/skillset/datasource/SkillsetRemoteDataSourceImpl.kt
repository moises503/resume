package com.moises.data.skillset.datasource

import com.moises.data.skillset.mapper.SkillsetResponseToSkillset
import com.moises.domain.skillset.datasource.SkillsetRemoteDataSource
import com.moises.domain.skillset.model.Skillset
import io.reactivex.Single

class SkillsetRemoteDataSourceImpl(
    private val skillsetEndPoint: SkillsetEndPoint,
    private val skillsetResponseToSkillset: SkillsetResponseToSkillset
) :
    SkillsetRemoteDataSource {
    override fun retrieveSkillset(): Single<Skillset> =
        skillsetEndPoint.attemptGetSkillset().map { skillsetResponse ->
            skillsetResponseToSkillset.transform(skillsetResponse)
        }
}