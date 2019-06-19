package com.checkeat.data.exception

class NetworkConnectionException(errorMessage: String = "The connection has failed"):
    Exception(errorMessage)