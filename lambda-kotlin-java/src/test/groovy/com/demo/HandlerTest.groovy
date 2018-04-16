package com.demo

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import spock.lang.Specification

class HandlerTest extends Specification {
    def "should process request"() {

        given:
        APIGatewayProxyRequestEvent testEvent = [
                body: "Dummy text"
        ] as APIGatewayProxyRequestEvent
        S3Client s3Client = Mock()

        when:
        def response = new Handler().processRequest(testEvent, s3Client)

        then: 'response generated'
        response.statusCode == 200
        response.body == "Saved to S3"
        and: "s3 client invoked"
        1 * s3Client.saveToS3("Dummy text")
    }
}
