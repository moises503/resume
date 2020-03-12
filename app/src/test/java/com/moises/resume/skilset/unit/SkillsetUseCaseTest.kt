package com.moises.resume.skilset.unit

import com.moises.domain.core.SingleObserver
import com.moises.domain.core.SingleUseCase
import com.moises.domain.skillset.model.Skillset
import com.moises.resume.RxImmediateSchedulerRule
import com.moises.resume.skilset.faker.SkillsetSeeder
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

class SkillsetUseCaseTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var skillsetUseCase: SingleUseCase<Unit, Skillset>
    lateinit var skillsetObserver : SingleObserver<Skillset>
    lateinit var skillset: Skillset

    @Before
    fun setup() {
        skillsetUseCase = mock()
        skillsetObserver = mock()
        skillset = SkillsetSeeder.getFakeSkillset()
    }

    @Test
    fun whenExecuteUseCaseAlwaysReturnASkillsetObject() {
        whenever(skillsetUseCase.execute(Unit, skillsetObserver)).then {
            skillsetObserver.onSuccess(skillset)
        }
        skillsetUseCase.execute(Unit, skillsetObserver)
        verify(skillsetObserver, times(1)).onSuccess(skillset)
        verify(skillsetObserver, never()).onError(any())
    }

    @Test(expected = Exception::class)
    fun whenExecuteUseCaseAndSendNullParamsThrowAnException(){
        doAnswer { throw Exception("This is an error") }
            .`when`(skillsetUseCase).execute(null, skillsetObserver)
        skillsetUseCase.execute(null, skillsetObserver)
    }
}