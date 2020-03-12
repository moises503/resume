package com.moises.domain.skillset.repository

import com.moises.domain.skillset.model.Skillset
import io.reactivex.Observable
import io.reactivex.Single

interface SkillsetRepository {
    fun retrieveSkillset(hasInternetConnection : Boolean) : Single<Skillset>
    fun retrieveSkillsetAsObservable(hasInternetConnection: Boolean) : Observable<Skillset>
}