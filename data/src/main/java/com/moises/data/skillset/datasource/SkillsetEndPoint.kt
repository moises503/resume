package com.moises.data.skillset.datasource

import com.moises.data.skillset.model.SkillsetResponse
import io.reactivex.Single
import retrofit2.http.GET

interface SkillsetEndPoint {
    @GET("ae27d4a4deeac2c03e9274bc6771e928/raw/" +
            "3320ecf0afba2ab048afd0049ff2ed648adc9774/skillset.json")
    fun attemptGetSkillset(): Single<SkillsetResponse>
}