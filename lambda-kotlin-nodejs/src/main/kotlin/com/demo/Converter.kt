package com.demo

object RequestValidator {
    fun validateRequest(request: Request): ErrorResponse? {
        val errors = listOfNotNull(validateBodyNotEmpty(request.body), validatePath(request.path))
        return if (errors.isNotEmpty()) {
            ErrorResponse(400, errors)
        } else {
            null
        }
    }

    private fun validateBodyNotEmpty(body: String?): Error? {
        return if (body.isNullOrBlank()) {
            Error("body can not be empty")
        } else {
            null
        }
    }

    private fun validatePath(path: String?): Error? {
        return if (path.isNullOrBlank()) {
            Error("path is not specified")
        } else {
            null
        }
    }
}

