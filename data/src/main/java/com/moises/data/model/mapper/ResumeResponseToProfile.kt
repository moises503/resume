package com.moises.data.model.mapper

import com.moises.data.core.mapper.Transformer
import com.moises.data.model.Experience
import com.moises.data.model.Profile
import com.moises.domain.entity.Resume

class ResumeResponseToProfile : Transformer<Resume, Profile>() {
    override fun transform(value: Resume): Profile {
        var libs  = ""
        value.libs?.forEach {
            libs = libs.plus(it + "\n")
        }
        var archs = ""
        value.archs?.forEach {
            archs = archs.plus(it + "\n")
        }
        var interests = ""
        value.personalInterests?.forEach {
            interests = interests.plus(it + "\n")
        }

        val experience = mutableListOf<Experience>()
        value.experience?.forEach {
            experience.add(Experience(description = it?.description ?: "", company = it?.company ?: "",
                position =  it?.position ?: ""))
        }
        return Profile(photo= value.profile?.photo, fullName = "%s : ${value.profile?.name} ${value.profile?.lastName}",
            contactInfo = "%s: ${value.profile?.email} %s: ${value.profile?.cellphone}",
            birthDate = "%s: ${value.profile?.birthday}", libs = libs, archs = archs, interests = interests,
            experience = experience)
    }
}