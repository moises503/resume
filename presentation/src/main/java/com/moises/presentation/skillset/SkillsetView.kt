package com.moises.presentation.skillset

import com.moises.domain.skillset.model.Skillset

interface SkillsetView {
    fun hideUIElements()
    fun showUIElements()
    fun showLoader()
    fun hideLoader()
    fun setSkillset(skillset: Skillset)
    fun showError(message : String)
}