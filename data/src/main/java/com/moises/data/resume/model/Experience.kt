package com.moises.data.resume.model

import com.google.gson.annotations.SerializedName

data class Experience(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("position")
	val position: String? = null
)