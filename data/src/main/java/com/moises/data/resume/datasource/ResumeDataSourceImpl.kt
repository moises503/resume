package com.moises.data.resume.datasource

import com.moises.data.resume.mapper.ResumeResponseToProfile
import com.moises.domain.resume.datasource.ResumeDataSource
import com.moises.domain.resume.model.Profile
import io.reactivex.Single

class ResumeDataSourceImpl(private val endPoint: EndPoint,
                           private val resumeResponseToProfile: ResumeResponseToProfile) : ResumeDataSource {

    override fun getResume(): Single<Profile> = endPoint.attemptGetExperience()
        .map { resume ->  resumeResponseToProfile.transform(resume)}
}