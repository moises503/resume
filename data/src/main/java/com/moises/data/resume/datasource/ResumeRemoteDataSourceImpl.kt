package com.moises.data.resume.datasource

import com.moises.data.resume.model.Resume
import io.reactivex.Single

class ResumeRemoteDataSourceImpl(private val endPoint: EndPoint) : ResumeRemoteDataSource {
    override fun attemptGetResume(): Single<Resume> {
        return endPoint.attemptGetExperience()
    }
}