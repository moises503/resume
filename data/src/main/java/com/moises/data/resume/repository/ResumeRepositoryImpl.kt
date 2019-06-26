package com.moises.data.resume.repository

import com.moises.data.resume.datasource.ResumeRemoteDataSource
import com.moises.data.resume.model.Resume
import io.reactivex.Single

class ResumeRepositoryImpl(private val resumeRemoteDataSource: ResumeRemoteDataSource) : ResumeRepository {
    override fun attemptGetResume(): Single<Resume> {
        return resumeRemoteDataSource.attemptGetResume()
    }
}