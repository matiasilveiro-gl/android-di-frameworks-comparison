package com.matias.data.service.util

object ApiUtils {

    fun getImageUrlFromPath(path: String) = "${ApiConstants.IMG_BASE_URL}/$path"

    fun getPathFromImageUrl(url: String) = url.substringAfter(ApiConstants.IMG_BASE_URL)
}
