package com.moises.data.skillset.model

import com.google.gson.annotations.SerializedName

data class SkillsetResponse(

	@field:SerializedName("courses")
	val courses: List<CourseItem>? = null,

	@field:SerializedName("archs")
	val archs: List<SkillItem>? = null,

	@field:SerializedName("pattern_archs")
	val patternArchs: List<SkillItem>? = null
)