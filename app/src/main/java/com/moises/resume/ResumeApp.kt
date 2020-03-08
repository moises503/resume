package com.moises.resume

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.moises.presentation.resume.ResumeView
import com.moises.resume.core.DiCoreModule
import com.moises.resume.ui.resume.di.DaggerResumeComponent
import com.moises.resume.ui.resume.di.ResumeComponent
import com.moises.resume.ui.resume.di.ResumeModule

class ResumeApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun getResumeComponent(fragment: Fragment, view: ResumeView) : ResumeComponent {
        return DaggerResumeComponent.builder()
            .diCoreModule(DiCoreModule(fragment))
            .resumeModule(ResumeModule(view))
            .build()
    }

    fun getResumeComponent(activity: Activity, view: ResumeView) : ResumeComponent {
        return DaggerResumeComponent.builder()
            .diCoreModule(DiCoreModule(activity))
            .resumeModule(ResumeModule(view))
            .build()
    }
}