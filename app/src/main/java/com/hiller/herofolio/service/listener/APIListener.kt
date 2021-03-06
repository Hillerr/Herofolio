package com.hiller.herofolio.service.listener

interface APIListener<T> {
    fun onSuccess(model: T)

    fun onFailure(str: String)
}