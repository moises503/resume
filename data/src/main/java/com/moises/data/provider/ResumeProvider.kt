package com.moises.data.provider

import com.moises.data.model.Profile


interface ResumeProvider {
    @Throws(Exception::class)
    fun attemptGetResume() : Profile
}