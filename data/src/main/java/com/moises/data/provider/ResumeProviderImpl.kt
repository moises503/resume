package com.moises.data.provider

import com.moises.data.model.Profile
import com.moises.data.model.mapper.ResumeResponseToProfile
import com.moises.data.source.ResumeDataSource

class ResumeProviderImpl(val resumeDataSource: ResumeDataSource, val resumeResponseToProfile: ResumeResponseToProfile)
    : ResumeProvider {
    override fun attemptGetResume(): Profile {
        val response = resumeDataSource.attemptGetProfile()
        return resumeResponseToProfile.transform(response!!)
    }
}