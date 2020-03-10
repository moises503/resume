package com.moises.resume.skilset.unit

import com.moises.data.skillset.mapper.CourseItemToCourse
import com.moises.data.skillset.mapper.SkillItemToSkill
import com.moises.data.skillset.mapper.SkillsetResponseToSkillset
import com.moises.data.skillset.model.SkillsetResponse
import com.moises.domain.skillset.datasource.SkillsetRemoteDataSource
import com.moises.domain.skillset.model.Skillset
import com.moises.resume.RxImmediateSchedulerRule
import com.moises.resume.skilset.faker.SkillsetSeeder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

class SkillsetDataSourceTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var courseItemToCourse: CourseItemToCourse
    lateinit var skillItemToSkill: SkillItemToSkill
    lateinit var skillsetResponseToSkillset: SkillsetResponseToSkillset
    lateinit var skillsetRemoteDataSource: SkillsetRemoteDataSource
    lateinit var skillsetResponse: SkillsetResponse
    lateinit var skillset: Single<Skillset>

    @Before
    fun setup() {
        skillsetResponse = SkillsetSeeder.getFakeSkillsetResponse()
        courseItemToCourse = CourseItemToCourse()
        skillItemToSkill = SkillItemToSkill()
        skillsetResponseToSkillset = SkillsetResponseToSkillset(courseItemToCourse, skillItemToSkill)
        skillset = Single.just(skillsetResponse).map { skillsetResponseToSkillset.transform(it)}
        skillsetRemoteDataSource = mock()
    }

    @Test()
    fun whenExecuteDataSourceTransformResponseToDomainEntity(){
        whenever(skillsetRemoteDataSource.retrieveSkillset()).thenReturn(skillset)
        val result = skillsetRemoteDataSource.retrieveSkillset()
        Assert.assertEquals(result, skillset)
    }
}