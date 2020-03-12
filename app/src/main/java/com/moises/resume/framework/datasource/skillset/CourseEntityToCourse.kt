package com.moises.resume.framework.datasource.skillset

import com.moises.data.core.mapper.Transformer
import com.moises.domain.skillset.model.Course
import com.moises.resume.framework.db.entities.CourseEntity

class CourseEntityToCourse : Transformer<CourseEntity, Course>() {
    override fun transform(value: CourseEntity): Course {
        return Course(name = value.name, image = value.image, platform = value.platform)
    }

    override fun reverseTransform(value: Course): CourseEntity {
        return CourseEntity(id = 0, name = value.name, platform = value.platform, image = value.image)
    }
}