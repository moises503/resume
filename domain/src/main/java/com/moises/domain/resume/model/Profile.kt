package com.moises.domain.resume.model

data class Profile(val photo : String? = null, val fullName : String? = null, val contactInfo : String? = null, val birthDate : String? = null,
                   val libs : String, val archs : String, val interests : String, val experience: List<Experience?>? = null)