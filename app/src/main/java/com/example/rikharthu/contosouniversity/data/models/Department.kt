package com.example.rikharthu.contosouniversity.data.models

import android.arch.persistence.room.*
import java.util.*


@Entity(foreignKeys = arrayOf(
        ForeignKey(
                entity = Instructor::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("instructor_id")
        )
))
data class Department(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val name: String = "",
        val budget: Float = -1f,
        @ColumnInfo(name = "start_date") val startDate: Date = Date(0),
        @ColumnInfo(name = "instructor_id") val instructorId: Long? = null // A department may or may not have an administrator
) {
    @Ignore
    constructor() : this(0, "", -1f, Date(0), null)
}