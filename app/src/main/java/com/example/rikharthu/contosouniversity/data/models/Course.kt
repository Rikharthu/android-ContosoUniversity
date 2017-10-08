package com.example.rikharthu.contosouniversity.data.models

import android.arch.persistence.room.*

@Entity(foreignKeys = arrayOf(
        ForeignKey(
                entity = Department::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("department_id")
        )
))
data class Course(
        @PrimaryKey val id: Long = 0,
        val title: String = "",
        val credits: Int = 0,
        @ColumnInfo(name = "department_id") val departmentId: Long = 0
) {
    @Ignore
    constructor() : this(0, "", 0, 0)
}