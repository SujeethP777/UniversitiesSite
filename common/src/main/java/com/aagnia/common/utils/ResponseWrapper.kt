package com.aagnia.common.utils

sealed interface ResponseWrapper<T : Any>

class SuccessResponseWrapper<T : Any>(val data: T) : ResponseWrapper<T>

class FailureResponseWrapper<T : Any>(val throwable: Throwable) : ResponseWrapper<T>