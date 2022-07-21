package com.druide.flexmovies.common

import kotlinx.serialization.json.Json

object Utils {

    /**
     *  json formatter to prevent unknown attribute
     */
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        encodeDefaults = false
    }
}