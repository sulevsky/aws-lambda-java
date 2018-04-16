package com.demo

import com.amazonaws.services.s3.AmazonS3ClientBuilder
import java.util.UUID


open class S3Client(private val s3Bucket: String) {
    private val s3client = AmazonS3ClientBuilder.defaultClient()

    open fun saveToS3(body: String) {
        s3client.putObject(s3Bucket,
                "demo-events/${UUID.randomUUID()}.txt",
                body
        ).let {
            print("S3 result: $it")
        }
    }
}