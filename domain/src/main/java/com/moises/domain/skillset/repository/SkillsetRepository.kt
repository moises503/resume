package com.moises.domain.skillset.repository

import com.moises.domain.skillset.model.Skillset
import io.reactivex.Single

interface SkillsetRepository {
    fun retrieveSkillset() : Single<Skillset>
}