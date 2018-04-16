package com.demo

import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class S3Client(@Value("\${s3-bucket}") private val s3Bucket: String) {
    private val s3client = AmazonS3ClientBuilder.defaultClient()

    fun saveToS3(body: String) {
        s3client.putObject(s3Bucket,
                "demo-events/${UUID.randomUUID()}.txt",
                body
        ).let {
            print("S3 result: $it")
        }
    }
}