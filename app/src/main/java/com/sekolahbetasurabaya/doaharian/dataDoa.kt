package com.sekolahbetasurabaya.doaharian

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class dataDoa(
    val title: String,
    val arabic: String,
    val latin: String,
    val arti: String
) : Parcelable
