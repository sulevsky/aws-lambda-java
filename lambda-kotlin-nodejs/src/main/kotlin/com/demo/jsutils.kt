package com.demo

import kotlin.js.Json
import kotlin.js.json

external fun require(module: String): dynamic

fun toMap(json: Json?): Map<String, Any?> {
    if (json == null) {
        return mapOf()
    }
    val keys = js("Object.getOwnPropertyNames(json)") as Array<String>
    return keys.map {
        it to json[it]
    }.toMap()
}

fun toJson(map: Map<String, Any?>): Json {

    val result: Json = json()
    map.forEach {
        result[it.key] = it.value
    }
    return result
}

external val process: dynamic

fun getEnvVariable(name: String): String {
    return process.env[name] as String
}

