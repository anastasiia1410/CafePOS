package com.example.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Portion(
    val id: Long,
    val portionType: PortionType,
) : Parcelable