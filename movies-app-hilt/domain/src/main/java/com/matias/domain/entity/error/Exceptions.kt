package com.matias.domain.entity.error

class NoNetworkException : Exception("Network service not available.")

class NotAuthenticatedException : Exception("Missing authentication credentials.")

class NotAuthorizedException : Exception("Not authorized to perform that request.")

class InternalServerException : Exception(
    "Operation was rejected because the system is not in a state " +
        "required for the operation's execution."
)

class NotFoundException : Exception("The requested data was not found.")
