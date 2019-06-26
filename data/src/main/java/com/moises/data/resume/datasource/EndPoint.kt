package com.moises.data.resume.datasource

import com.moises.data.resume.model.Resume
import io.reactivex.Single
import retrofit2.http.GET

interface EndPoint {
    @GET("experience.json")
    fun attemptGetExperience() : Single<Resume>
}