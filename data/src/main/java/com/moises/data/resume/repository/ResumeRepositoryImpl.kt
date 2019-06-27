package com.moises.data.resume.repository

import com.moises.domain.resume.datasource.ResumeDataSource
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.repository.ResumeRepository
import io.reactivex.Single

class ResumeRepositoryImpl(private val resumeDataSource: ResumeDataSource) : ResumeRepository{
    override fun attemptGetResume(): Single<Profile> = resumeDataSource.getResume()
}