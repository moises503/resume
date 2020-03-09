package com.moises.resume

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.repository.ResumeRepository
import com.moises.domain.resume.usecase.ResumeUseCase
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumePresenterImpl
import com.moises.resume.core.job.JobThread
import com.moises.resume.core.job.UIThread
import com.moises.resume.ui.resume.ResumeActivity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.hamcrest.Matchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4ClassRunner::class)
class ResumeActivityInstrumentedTest {


    lateinit var useCase: ResumeUseCase
    lateinit var resumeRepository: ResumeRepository
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
        resumeRepository = mock()
        useCase =  ResumeUseCase(resumeRepository, JobThread(), UIThread())
        presenter = ResumePresenterImpl(useCase, activity)
        whenever(resumeRepository.attemptGetResume()).thenReturn(Single.just(profile))
    }

    @Test
    fun whenGetResumeHideLoadingAndShowViews() {
        onView(withId(R.id.pbLoader)).check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.txtFullName)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtContactInfo)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtLibs)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtPatterns)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtExperienceText)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.rvExperience)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtLibsText)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtPatternsText)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.imgvPhoto)).check(ViewAssertions
            .matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}
