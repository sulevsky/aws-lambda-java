package com.demo

@JsName("mainHandler")
fun handle(request: Request, context: Map<String, Any>, callback: (err: String?, result: dynamic) -> Unit) {
    RequestValidator.validateRequest(request)
            ?.let { return callback(null, it.toDynamic()) }

    S3.save(request)
            .then { Response.ok() }
            .catch { Response.fail(it.message.orEmpty()) }
            .then { callback(null, it.toDynamic()) }
}
