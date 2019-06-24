package com.moises.presentation.resume

import com.moises.data.model.Profile

interface ResumeView {
    fun showLoading()
    fun hideLoading()
    fun displayProfile(profile : Profile)
    fun showError(message : String)
}