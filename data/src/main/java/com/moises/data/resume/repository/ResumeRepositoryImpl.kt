package com.moises.data.resume.repository

import com.moises.domain.resume.datasource.ResumeDataSource
import com.moises.domain.resume.datasource.ResumeLocalDataSource
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.repository.ResumeRepository
import io.reactivex.Single

class ResumeRepositoryImpl(private val resumeDataSource: ResumeDataSource,
                           private val resumeLocalDataSource: ResumeLocalDataSource) : ResumeRepository{

    override fun attemptGetResume(hasInternetConnection: Boolean): Single<Profile> =
        if(hasInternetConnection){
            var localResume : Single<Profile>? = null
            val resume = resumeDataSource.getResume().doOnSuccess { profile ->
                resumeLocalDataSource.insertProfile(profile)
            }.doOnError {
                localResume = resumeLocalDataSource.retrieveProfile()
            }
            localResume ?: resume
        } else {
            resumeLocalDataSource.retrieveProfile()
        }
}