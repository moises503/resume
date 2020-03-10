package com.moises.resume.skilset.faker

import com.moises.data.skillset.model.CourseItem
import com.moises.data.skillset.model.SkillItem
import com.moises.data.skillset.model.SkillsetResponse
import com.moises.domain.skillset.model.Course
import com.moises.domain.skillset.model.Skill
import com.moises.domain.skillset.model.Skillset


class SkillsetSeeder {
    companion object {
        fun getFakeSkillsetResponse() : SkillsetResponse {
            return SkillsetResponse(
                courses = listOf(CourseItem(name = "Kotlin", platform = "platzi", image = "https://myimage.com/image/1")),
                archs = listOf(SkillItem(99.0, "CLEAN")),
                patternArchs = listOf(SkillItem(99.0, "CLEAN"))
            )
        }

        fun getFakeSkillset() : Skillset {
            return Skillset(
                courses = listOf(Course(name = "Kotlin", platform = "platzi", image = "https://myimage.com/image/1")),
                archs = listOf(Skill("CLEAN", 99.0)),
                patternArchs = listOf(Skill("CLEAN", 99.0))
            )
        }
    }
}