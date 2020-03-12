package com.moises.resume.skilset.unit

import com.moises.domain.skillset.model.Skillset
import com.moises.domain.skillset.repository.SkillsetRepository
import com.moises.resume.skilset.faker.SkillsetSeeder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SkillsetRepositoryTest {
    lateinit var skillsetRepository: SkillsetRepository
    lateinit var skillset : Single<Skillset>

    @Before
    fun setup() {
        skillsetRepository = mock()
        skillset = Single.just(SkillsetSeeder.getFakeSkillset())
        whenever(skillsetRepository.retrieveSkillset(true)).thenReturn(skillset)
    }

    @Test
    fun whenRepositoryRetrievesASkillsetThisReturnASkillsetObject() {
        val result = skillsetRepository.retrieveSkillset(true)
        assertEquals(result, skillset)
    }

    @Test
    fun whenExecuteSkillsetRepositoryAlwaysExecuteOnSuccessMethod() {
        val testObserver = skillsetRepository.retrieveSkillset(true).test()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}