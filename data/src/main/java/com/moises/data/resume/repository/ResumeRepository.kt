package com.moises.data.resume.repository

import com.moises.data.resume.model.Resume
import io.reactivex.Single

interface ResumeRepository {
    fun attemptGetResume() : Single<Resume>
}