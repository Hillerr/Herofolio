package com.hiller.herofolio.service.model


class MarvelResponse<T>(
    var code: Int,
    var status: String,
    var data: T?
)
