package com.moises.resume.ui.skillset.di

import com.moises.presentation.skillset.SkillsetPresenter
import com.moises.resume.core.DiCoreModule
import com.moises.resume.core.DatabaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SkillsetModule::class, DatabaseModule::class, DiCoreModule::class])
interface SkillsetComponent {
    fun getSkillsetPresenter() : SkillsetPresenter
}