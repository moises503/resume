package com.moises.domain.resume.repository

import com.moises.domain.resume.model.Profile
import io.reactivex.Single

interface ResumeRepository {
    fun attemptGetResume() : Single<Profile>
}