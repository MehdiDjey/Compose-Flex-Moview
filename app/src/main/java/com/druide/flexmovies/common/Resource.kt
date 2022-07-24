package com.druide.flexmovies.common


sealed class Resource {
    object Empty : Resource()
    object Loading : Resource()
    class Success(val data: Any) : Resource()
    class Error(val message: String) : Resource()
}

