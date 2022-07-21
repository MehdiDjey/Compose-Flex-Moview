package com.druide.flexmovies.common

object Keys {
    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String
}