package com.moises.domain.repository

import com.moises.domain.entity.Resume

interface ResumeRepository {
    @Throws(Exception::class)
    fun attemptGetResume() : Resume
}