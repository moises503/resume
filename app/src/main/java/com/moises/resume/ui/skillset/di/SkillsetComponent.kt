package com.moises.resume.ui.skillset.di

import com.moises.presentation.skillset.SkillsetPresenter
import com.moises.resume.core.DiCoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SkillsetModule::class, DiCoreModule::class])
interface SkillsetComponent {
    fun getSkillsetPresenter() : SkillsetPresenter
}