package com.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import spock.lang.Specification

import java.util.function.Function

import static org.mockito.Mockito.verify

@SpringBootTest
class HandlerTest extends Specification {
    @MockBean
    S3Client s3Client
    @Autowired
    Function<Message<Object>, Message<String>> function

    def "should process request"() {
        when:
        def resp = function.apply(new GenericMessage("test_payload"))
        then: "response generated"
        resp.headers.statusCode == 202
        resp.payload == "Saved to S3"
        and: "s3 client invoked"
        verify(s3Client).saveToS3("\"test_payload\"")
    }
}
