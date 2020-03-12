package com.moises.presentation.skillset

interface SkillsetPresenter {
    fun retrieveSkillset(hasInternetConnection : Boolean)
    fun onStop()
}