package com.streamflixreborn.streamflix.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val id: String,
    val name: String,
    val avatar: String
) : Parcelable
