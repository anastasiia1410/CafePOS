package com.example.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Portion(
    val id: Long = 0L,
    val portionType: String,
    val portionUnit : String
) : Parcelable