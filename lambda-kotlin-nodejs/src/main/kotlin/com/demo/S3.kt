package com.demo

import kotlin.js.Math
import kotlin.js.Promise

object S3 {
    fun save(request: Request): Promise<Any> {
        val bucket = getEnvVariable("S3_BUCKET")
        console.log("Start saving to S3")

        val AWS = require("aws-sdk")
        val s3 = js("new AWS.S3()")
        val params: dynamic = object {}
        params["Bucket"] = bucket
        params["Key"] = "demo-events/${Math.random().toString().substring(2)}.txt"
        params["Body"] = request.body
        return s3.putObject(params).promise() as Promise<Any>
    }
}
