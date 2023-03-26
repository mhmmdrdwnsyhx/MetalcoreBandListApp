package com.example.mybandlistapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(val title: String, val year: String, val description: String, val photo: String?) : Parcelable

