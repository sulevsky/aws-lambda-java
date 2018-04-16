package com.demo

import com.demo.RequestTest.Companion.defaultRequest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue


class ValidatorTest {

    @BeforeTest
    fun setUp() {
        js(""" process.env['S3_BUCKET'] = 'SOME_NAME' """)
    }

    @Test
    fun responseWithErrorOnEmptyBody() {
        val request = defaultRequest().replace("{\\\"test\\\":\\\"body\\\"}", "")
        val parsed = JSON.parse<Request>(request)
        var response: dynamic = null
        var error: String? = null
        val cb: (err: String?, result: Any?) -> Unit = { err: String?, result: dynamic ->
            error = err
            response = result
        }
        handle(parsed, mapOf(), cb)

        assertEquals(400, response["statusCode"])
        assertNull(error)
        assertTrue {
            response["body"].toString().contains("body can not be empty")
        }
    }

    @Test
    fun responseWithErrorOnEmptyPath() {
        val request = defaultRequest().replace("/path/to/resource", "")
        val parsed = JSON.parse<Request>(request)
        var response: dynamic = null
        var error: String? = null
        val cb: (err: String?, result: Any?) -> Unit = { err: String?, result: dynamic ->
            error = err
            response = result
        }
        handle(parsed, mapOf(), cb)

        assertEquals(400, (response["statusCode"]))
        assertNull(error)
        assertTrue {
            response["body"].toString().contains("path is not specified")
        }
    }


}