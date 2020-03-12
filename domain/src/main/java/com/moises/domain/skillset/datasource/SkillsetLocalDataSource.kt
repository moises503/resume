package com.moises.domain.skillset.datasource

import com.moises.domain.skillset.model.Course
import com.moises.domain.skillset.model.Skillset
import io.reactivex.Single

interface SkillsetLocalDataSource {
    fun retrieveSkillset() : Single<Skillset>
    fun insertCourses(courses : List<Course>)
    fun retrieveCourses() : List<Course>
    fun insertOneCourse(course : Course)
}