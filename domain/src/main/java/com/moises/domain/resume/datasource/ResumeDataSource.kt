package com.moises.domain.resume.datasource

import com.moises.domain.resume.model.Profile
import io.reactivex.Single

interface ResumeDataSource {
    fun getResume() : Single<Profile>
}