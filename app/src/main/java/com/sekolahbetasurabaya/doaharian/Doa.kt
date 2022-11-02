package com.sekolahbetasurabaya.doaharian

import com.google.gson.annotations.SerializedName


class Doa : ArrayList<DoaItem>()

data class DoaItem(
    @SerializedName("artinya")
    val artinya: String?,
    @SerializedName("ayat")
    val ayat: String?,
    @SerializedName("doa")
    val doa: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("latin")
    val latin: String?
)