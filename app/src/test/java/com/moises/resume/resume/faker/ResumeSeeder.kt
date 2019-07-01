package com.moises.resume.resume.faker

import com.moises.data.resume.model.Experience
import com.moises.data.resume.model.Profile
import com.moises.data.resume.model.Resume

class ResumeSeeder {
    companion object {
        fun makeFakeData() : Resume{
            val firstJob = Experience("First job","First company", "First position")
            val secondJob = Experience("Second job","Second company", "Second position")
            val experience = listOf(firstJob, secondJob)
            val libs = listOf("lib1", "lib2")
            val archs = listOf("arch1", "arch2")
            val interests = listOf("interest1", "interest2")
            val profileData = Profile("24/09/1992", "Moi", "https://photos.com/photo.png",
                "Garduño Reyes", "9512780863","moises.garduno.r@gmail.com")
            return Resume(archs, profileData, experience, libs, interests)
        }
    }
}