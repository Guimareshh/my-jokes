package com.lucienguimaraes.network

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiEndPoint

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Logging

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl
