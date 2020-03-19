package com.moises.domain.resume.datasource

import com.moises.domain.resume.model.Profile
import io.reactivex.Single

interface ResumeLocalDataSource {
    fun insertProfile(profile : Profile)
    fun retrieveProfile() : Single<Profile>
}