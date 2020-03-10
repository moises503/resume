package com.moises.data.skillset.model

import com.google.gson.annotations.SerializedName

data class CourseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("platform")
	val platform: String? = null
)