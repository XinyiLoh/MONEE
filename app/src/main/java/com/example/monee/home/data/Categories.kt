package com.example.monee.home.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categories (val amount: Double,
                       val type: String,
                       val category: String
                       )


