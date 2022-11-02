package com.sekolahbetasurabaya.doaharian

import retrofit2.Response
import retrofit2.http.GET

interface apiservice {

    @GET("/api")
    suspend fun getDoa(): Response<Doa>
}