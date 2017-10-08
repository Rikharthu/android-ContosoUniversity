package com.example.rikharthu.contosouniversity.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

@Entity(primaryKeys = arrayOf("course_id", "instructor_id"), tableName = "course_assignment",
        foreignKeys = arrayOf(
                ForeignKey(entity = Instructor::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("instructor_id")),
                ForeignKey(entity = Course::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("course_id"))
        ))
data class CourseAssignment(
        @ColumnInfo(name = "course_id") val courseId: Long,
        @ColumnInfo(name = "instructor_id") val instructorId: Long
)