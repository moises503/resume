package com.moises.data.skillset.mapper

import com.moises.data.core.mapper.Transformer
import com.moises.data.skillset.model.SkillsetResponse
import com.moises.domain.skillset.model.Skillset

class SkillsetResponseToSkillset(
    private val courseItemToCourse: CourseItemToCourse,
    private val skillItemToSkill: SkillItemToSkill
) : Transformer<SkillsetResponse, Skillset>() {

    override fun transform(value: SkillsetResponse): Skillset {
        return Skillset(courses = courseItemToCourse.transformCollection(value.courses.orEmpty()),
            archs = skillItemToSkill.transformCollection(value.archs.orEmpty()),
            patternArchs = skillItemToSkill.transformCollection(value.patternArchs.orEmpty()))
    }

}