package com.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import java.util.function.Function

@SpringBootApplication
class Application {

    @Bean
    fun function(s3Client: S3Client, objectMapper: ObjectMapper): Function<Message<Any>, Message<String>> {
        return Function {
            s3Client.saveToS3(objectMapper.writeValueAsString(it.payload))
            GenericMessage("Saved to S3",
                    mapOf("Content-type" to "text/plain",
                            "statusCode" to 202))
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}