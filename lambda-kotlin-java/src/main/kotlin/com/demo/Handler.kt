package com.demo

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent


class Handler : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    override fun handleRequest(event: APIGatewayProxyRequestEvent, context: Context): APIGatewayProxyResponseEvent {
        val s3Client = S3Client(Config.s3Bucket)
        return processRequest(event, s3Client)
    }

    fun processRequest(event: APIGatewayProxyRequestEvent,
                       s3Client: S3Client): APIGatewayProxyResponseEvent {
        s3Client.saveToS3(event.body)
        return APIGatewayProxyResponseEvent().apply {
            statusCode = 200
            headers = mapOf("Content-type" to "text/plain")
            body = "Saved to S3"
        }
    }
}
