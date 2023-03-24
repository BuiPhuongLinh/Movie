package com.btplinh.domain.basenetwork

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorBody(val Response: Boolean?, val Error: String)