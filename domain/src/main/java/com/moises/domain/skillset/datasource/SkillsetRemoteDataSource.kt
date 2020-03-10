package com.moises.domain.skillset.datasource

import com.moises.domain.skillset.model.Skillset
import io.reactivex.Single

interface SkillsetRemoteDataSource {
    fun retrieveSkillset() : Single<Skillset>
}