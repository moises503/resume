package com.moises.resume.skilset.unit

import com.moises.domain.skillset.model.Skillset
import com.moises.presentation.skillset.SkillsetPresenter
import com.moises.presentation.skillset.SkillsetView
import com.moises.resume.skilset.faker.SkillsetSeeder
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class SkillsetPresenterTest {
    lateinit var skillsetView: SkillsetView
    lateinit var skillsetPresenter: SkillsetPresenter
    lateinit var skillset : Skillset
    lateinit var errorMessage : String

    @Before
    fun setup() {
        skillsetView = mock()
        skillsetPresenter = mock()
        skillset = SkillsetSeeder.getFakeSkillset()
        errorMessage = "This is an error message"
    }

    @Test
    fun whenAttemptGetSkillsetAndIsDoneDisplayProfileInView() {
        whenever(skillsetPresenter.retrieveSkillset(true)).then {
            skillsetView.setSkillset(skillset)
        }
        skillsetPresenter.retrieveSkillset(true)
        verify(skillsetView, times(1)).setSkillset(skillset)
        verify(skillsetView, never()).showError(errorMessage)
    }

    @Test
    fun whenAttemptGetSkillsetAndErrorOccurredDisplayAnError() {
        whenever(skillsetPresenter.retrieveSkillset(true)).then {
            skillsetView.showError(errorMessage)
        }
        skillsetPresenter.retrieveSkillset(true)
        verify(skillsetView, never()).setSkillset(skillset)
        verify(skillsetView, times(1)).showError(errorMessage)
    }
}