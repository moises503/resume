package com.moises.data.skillset.model

import com.google.gson.annotations.SerializedName

data class SkillItem(

	@field:SerializedName("percentage")
	val percentage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null
)