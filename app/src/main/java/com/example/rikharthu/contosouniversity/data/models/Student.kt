package com.example.rikharthu.contosouniversity.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Student(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "first_name") val firstName: String = "",
        @ColumnInfo(name = "last_name") val lastName: String = "",
        @ColumnInfo(name = "enrollment_date") val enrollmentDate: Date = Date(0)
) {
    @Ignore
    constructor() : this(0, "", "", Date(0))
}