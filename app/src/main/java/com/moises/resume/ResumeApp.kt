package com.moises.resume

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.moises.presentation.resume.ResumeView
import com.moises.presentation.skillset.SkillsetView
import com.moises.resume.core.DiCoreModule
import com.moises.resume.core.DatabaseModule
import com.moises.resume.ui.resume.di.DaggerResumeComponent
import com.moises.resume.ui.resume.di.ResumeComponent
import com.moises.resume.ui.resume.di.ResumeModule
import com.moises.resume.ui.skillset.di.DaggerSkillsetComponent
import com.moises.resume.ui.skillset.di.SkillsetComponent
import com.moises.resume.ui.skillset.di.SkillsetModule

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

    fun getSkillsetComponent(fragment: Fragment, view : SkillsetView) : SkillsetComponent {
        return DaggerSkillsetComponent.builder().
                diCoreModule(DiCoreModule(fragment))
            .skillsetModule(SkillsetModule(view))
            .databaseModule(DatabaseModule(this))
            .build()
    }
}