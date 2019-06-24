package com.moises.resume

import com.moises.data.model.Profile
import com.moises.data.provider.ResumeProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResumeProviderTest {
    lateinit var resumeProvider : ResumeProvider

    @Before
    fun setup(){
        resumeProvider = mock()
    }

    @Test
    fun whenExecuteProviderAlwaysReturnAProfileObject() {
        val profile = Profile("", "", "", "", "", "", "", emptyList())
        whenever(resumeProvider.attemptGetResume()).thenReturn(profile)
        val result = resumeProvider.attemptGetResume()
        assertEquals(result, profile)
    }
}