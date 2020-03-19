package com.moises.presentation.resume

interface ResumePresenter {
    fun attemptGetResume(hasInternetConnection : Boolean)
    fun onStop()
}