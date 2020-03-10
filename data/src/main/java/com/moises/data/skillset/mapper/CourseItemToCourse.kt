package com.moises.data.skillset.mapper

import com.moises.data.core.mapper.Transformer
import com.moises.data.skillset.model.CourseItem
import com.moises.domain.skillset.model.Course

class CourseItemToCourse : Transformer<CourseItem, Course>() {
    override fun transform(value: CourseItem): Course {
        return Course(
            name = value.name.orEmpty(),
            platform = value.platform.orEmpty(),
            image = value.image.orEmpty()
        )
    }
}