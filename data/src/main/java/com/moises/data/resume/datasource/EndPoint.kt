package com.moises.data.resume.datasource

import com.moises.data.resume.model.Resume
import io.reactivex.Single
import retrofit2.http.GET

interface EndPoint {
    @GET("16c5e1a4572791e1eaf44ea5d3af7d48/raw" +
            "/e2870e361453decf4811e1efceb12425bf011865/experience.json")
    fun attemptGetExperience(): Single<Resume>
}