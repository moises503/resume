package com.moises.resume.framework.datasource.skillset

import com.moises.domain.skillset.datasource.SkillsetLocalDataSource
import com.moises.domain.skillset.model.Course
import com.moises.domain.skillset.model.Skillset
import com.moises.resume.framework.db.daos.CoursesDao
import io.reactivex.Single

class SkillsetLocalDataSourceImpl(
    private val coursesDao: CoursesDao,
    private val courseEntityToCourse: CourseEntityToCourse
) : SkillsetLocalDataSource {

    override fun retrieveSkillset(): Single<Skillset> =
        Single.just(
            Skillset(
                courses = retrieveCourses(),
                archs = emptyList(),
                patternArchs = emptyList()
            )
        )

    override fun insertCourses(courses: List<Course>) =
        coursesDao.insertAllCourses(courseEntityToCourse.reverseTransformCollection(courses))

    override fun retrieveCourses(): List<Course> =
        courseEntityToCourse.transformCollection(coursesDao.getAllCourses())

    override fun insertOneCourse(course: Course) =
        coursesDao.insertCourse(courseEntityToCourse.reverseTransform(course))

}