package com.demo

import kotlin.js.Json

data class Request(
    val resource: String,
    val path: String? = null,
    val httpMethod: String,
    val headers: Json,
    val queryStringParameters: Json? = null,
    val pathParameters: Json,
    var stageVariables: Json? = null,
    var body: String? = null,
    var isBase64Encoded: Boolean = false)

data class Response(val statusCode: Int,
                    val headers: Map<String, String> = mapOf(),
                    val body: String? = null,
                    val isBase64Encoded: Boolean = false) {
    fun toDynamic(): dynamic {
        val result: dynamic = object {}
        result["statusCode"] = statusCode
        result["body"] = body
        result["isBase64Encoded"] = isBase64Encoded
        result["headers"] = toJson(headers)
        return result
    }

    companion object {
        fun ok(): Response {
            return Response(200)
        }

        fun fail(body: String): Response {
            return Response(statusCode = 500, body = body)
        }
    }
}

data class Error(val message: String)

class ErrorResponse(val statusCode: Int, val errors: List<Error>) {
    fun toDynamic(): dynamic {
        val result: dynamic = object {}
        result["statusCode"] = statusCode
        result["body"] = JSON.stringify(errors)
        result["isBase64Encoded"] = false
        result["headers"] = object {}
        result["headers"]["Content-Type"] = "application/json"
        return result
    }
}