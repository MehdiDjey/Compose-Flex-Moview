package com.druide.flexmovies

import com.druide.flexmovies.common.Constant.IMG_BASE_URL

/**
 * get tag of current current instance
 */
val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

/**
 * Formatted poster path
 *
 * According to the Doc we need add base url to fetch image
 *
 * @return
 */
fun String.formattedPosterPath(): String {
    return "$IMG_BASE_URL$this"
}

/**
 * Formatted back drop path
 *
 *  According to the Doc we need add base url to fetch image
 *
 * @return
 */
fun String.formattedBackDropPath(): String {
    return "$IMG_BASE_URL$this"
}