package com.moises.resume.framework.datasource.resume

import com.moises.domain.resume.datasource.ResumeLocalDataSource
import com.moises.domain.resume.model.Experience
import com.moises.domain.resume.model.Profile
import com.moises.resume.framework.db.daos.ExperiencesDao
import com.moises.resume.framework.db.daos.ProfilesDao
import com.moises.resume.framework.db.entities.ExperienceEntity
import com.moises.resume.framework.db.entities.ProfileEntity
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class ResumeLocalDataSourceImpl(
    private val experiencesDao: ExperiencesDao,
    private val profilesDao: ProfilesDao
) : ResumeLocalDataSource {

    override fun insertProfile(profile: Profile) {
        val experiences = profile.experience.orEmpty()
        val experienceEntities = experiences.map { experience ->
            ExperienceEntity(
                id = 0, description = experience.description, company = experience.company,
                position = experience.position
            )
        }
        experiencesDao.insertAllExperiences(experienceEntities)
        profilesDao.insertProfile(
            ProfileEntity(
                id = 0,
                photo = profile.photo,
                fullName = profile.fullName,
                contactInfo = profile.contactInfo,
                birthDate = profile.birthDate,
                libs = profile.libs,
                archs = profile.archs,
                interests = profile.interests
            )
        )
    }

    override fun retrieveProfile(): Single<Profile> = Single.zip(
        experiencesDao.getAllExperiences(),
        profilesDao.getAllProfiles(),
        BiFunction<List<ExperienceEntity>, List<ProfileEntity>, Profile> { experienceList, profileList ->
            val profileEntity = profileList[0]
            val experiences = experienceList.map { experienceEntity ->
                Experience(
                    description = experienceEntity.description,
                    company = experienceEntity.company,
                    position = experienceEntity.position
                )
            }
            Profile(
                photo = profileEntity.photo,
                fullName = profileEntity.fullName,
                contactInfo = profileEntity.contactInfo,
                birthDate = profileEntity.birthDate,
                libs = profileEntity.libs,
                archs = profileEntity.archs,
                interests = profileEntity.interests,
                experience = experiences
            )
        })

}