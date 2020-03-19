package com.moises.resume.resume.unit

import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.repository.ResumeRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResumeRepositoryTest {

    lateinit var resumeRepository: ResumeRepository
    lateinit var profile : Profile
    lateinit var singleProfile : Single<Profile>
    @Before
    fun setup(){
        resumeRepository = mock()
        profile = Profile("", "", "", "",
            "", "", "", emptyList())
        singleProfile = Single.just(profile)
        whenever(resumeRepository.attemptGetResume(true)).thenReturn(singleProfile)
    }

    @Test
    fun whenExecuteRepositoryAlwaysReturnAProfileObject() {
        val result = resumeRepository.attemptGetResume(true)
        assertEquals(result, singleProfile)
    }

    @Test
    fun whenExecuteRepositoryAlwaysExecuteOnSuccessMethod() {
        val testObserver = resumeRepository.attemptGetResume(true).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}