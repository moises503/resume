package com.moises.data.resume.model

import com.google.gson.annotations.SerializedName

data class Resume(

	@field:SerializedName("archs")
	val archs: List<String?>? = null,

	@field:SerializedName("profile")
	val profile: Profile? = null,

	@field:SerializedName("experience")
	val experience: List<Experience?>? = null,

	@field:SerializedName("libs")
	val libs: List<String?>? = null,

	@field:SerializedName("personal_interests")
	val personalInterests: List<String?>? = null
)