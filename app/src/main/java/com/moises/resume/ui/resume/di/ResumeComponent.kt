package com.moises.resume.ui.resume.di

import com.moises.presentation.resume.ResumePresenter
import com.moises.resume.core.DiCoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ResumeModule::class, DiCoreModule::class])
interface ResumeComponent {
    fun getResumePresenter() : ResumePresenter
}