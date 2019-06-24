package com.moises.data.source

import com.checkeat.data.exception.NetworkConnectionException
import com.checkeat.data.exception.ServiceException
import com.moises.domain.entity.Resume
import java.io.IOException

class ResumeDataSourceImpl(val resumeService: ResumeService) : ResumeDataSource {
    override fun attemptGetProfile(): Resume? {
        try {
            val response = resumeService.getResume().execute()
            if(response.isSuccessful){
                return response.body()
            } else{
                throw ServiceException()
            }
        } catch (exception: IOException) {
            throw NetworkConnectionException()
        }
    }
}