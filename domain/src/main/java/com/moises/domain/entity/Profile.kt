package com.moises.domain.entity

import com.google.gson.annotations.SerializedName

data class Profile(

	@field:SerializedName("birthday")
	val birthday: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("cellphone")
	val cellphone: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)