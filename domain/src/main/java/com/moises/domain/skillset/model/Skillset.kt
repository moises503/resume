package com.moises.domain.skillset.model

data class Skillset(
    val courses: List<Course>,
    val archs: List<Skill>,
    val patternArchs: List<Skill>
)