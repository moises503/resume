package com.moises.resume

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.moises.resume.ui.menu.BottomMenuActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BottomMenuActivityInstrumentedTest {

    @Rule
    @JvmField
    var activityTestRule : ActivityTestRule<BottomMenuActivity> =
        ActivityTestRule(BottomMenuActivity::class.java, true, false)

    @Before
    fun setup() {
        val intent = Intent()
        activityTestRule.launchActivity(intent)
    }

    @Test
    fun whenPressNavigationResumeShowsFragmentResume() {
        onView(withId(R.id.navigation_resume)).perform(click())
        onView(withId(R.id.fragment_resume)).check(matches(isDisplayed()))
    }

    @Test
    fun whenPressNavigationSkillsShowsFragmentSkills() {
        onView(withId(R.id.navigation_skills)).perform(click())
        onView(withId(R.id.fragment_skills)).check(matches(isDisplayed()))
    }

    @Test
    fun whenShowsFragmentSkillsShouldSeeViewResumeInformation() {
        onView(withId(R.id.pbLoader)).check(matches(Matchers.not(isDisplayed())))
        onView(withId(R.id.txtFullName)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtContactInfo)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtLibs)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtPatterns)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtExperienceText)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.rvExperience)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtLibsText)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.txtPatternsText)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.imgvPhoto)).check(
            matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}