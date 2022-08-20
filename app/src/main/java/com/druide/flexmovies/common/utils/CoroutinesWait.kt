package com.druide.flexmovies.common.utils

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun awaitAll(vararg blocks: suspend () -> Unit) = coroutineScope {
    blocks.forEach {
        launch { it() }
    }
}