package com.orkestra.paycars.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityDataShowroom (

    @PrimaryKey(autoGenerate = true) var idShowroom: Int? = null,
    @ColumnInfo(name = "full_name") var fullName: String?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "phone") var phone: String?
)