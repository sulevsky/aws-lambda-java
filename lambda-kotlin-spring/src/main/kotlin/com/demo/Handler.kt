package com.demo

import org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler

class Handler : SpringBootApiGatewayRequestHandler(Application::class.java)