package com.moises.data.source

import com.moises.domain.entity.Resume
import retrofit2.Call
import retrofit2.http.GET

interface ResumeService {
    @GET("experience.json")
    fun getResume(): Call<Resume>
}