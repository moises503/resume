package com.moises.presentation.resume

import com.moises.domain.resume.model.Profile

interface ResumeView {
    fun showLoading()
    fun hideLoading()
    fun displayProfile(profile : Profile)
    fun showViews()
    fun hideViews()
    fun showError(message : String)
}