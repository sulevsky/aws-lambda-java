package com.demo

import kotlin.test.Test
import kotlin.test.assertEquals

class RequestTest {
    @Test
    fun parseRequest() {
        val stringRequest = defaultRequest()
        val request = JSON.parse<Request>(stringRequest)

        assertEquals("/{proxy+}", request.resource)
        assertEquals("/path/to/resource", request.path)
        assertEquals("POST", request.httpMethod)
        assertEquals("Custom User Agent String", request.headers["User-Agent"] as String)
        assertEquals("bar", request.queryStringParameters?.get("foo") as String)
        assertEquals("qux", request.stageVariables?.get("baz") as String)
    }


    companion object {
        fun defaultRequest(): String {
            return require("fs").readFileSync("src/test/resources/sample_request.json").toString()
        }
    }
}