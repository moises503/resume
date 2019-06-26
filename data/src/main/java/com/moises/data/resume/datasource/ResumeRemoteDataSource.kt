package com.moises.data.resume.datasource

import com.moises.data.resume.model.Resume
import io.reactivex.Single

interface ResumeRemoteDataSource {
    fun attemptGetResume() : Single<Resume>
}