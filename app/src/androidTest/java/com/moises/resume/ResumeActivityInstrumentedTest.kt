package com.moises.resume

import android.content.Context
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.moises.domain.core.Observer
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.usecase.ResumeUseCase
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumePresenterImpl
import com.moises.resume.ui.resume.ResumeActivity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ResumeActivityInstrumentedTest {


    lateinit var useCase: ResumeUseCase
    lateinit var resumeObserver : Observer<Profile>
    lateinit var presenter : ResumePresenter
    lateinit var profile : Profile

    @Rule
    @JvmField
    var activityTestRule : ActivityTestRule<ResumeActivity> =
        ActivityTestRule(ResumeActivity::class.java, true, false)

    @Before
    fun setup() {
        val intent = Intent()
        activityTestRule.launchActivity(intent)
        val activity = activityTestRule.activity
        System.setProperty("org.mockito.android.target", activity.getDir("target", Context.MODE_PRIVATE).path)
        profile = ResumeSeeder.transform()
        useCase = mock()
        resumeObserver = mock()
        presenter = ResumePresenterImpl(useCase, activity)
        whenever(useCase.execute(Unit, resumeObserver)).then {
            activity.hideLoading()
            activity.showViews()
            activity.displayProfile(profile)
        }
    }

    @Test
    fun whenGetResumeHideLoadingAndShowViews() {
        presenter.attemptGetResume()
        onView(withId(R.id.pbLoader)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
