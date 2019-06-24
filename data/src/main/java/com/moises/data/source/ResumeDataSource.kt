package com.moises.data.source

import com.moises.domain.entity.Resume

interface ResumeDataSource {
    @Throws(Exception::class)
    fun attemptGetProfile() : Resume?
}