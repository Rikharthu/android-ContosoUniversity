package com.example.rikharthu.contosouniversity.data.models

import android.arch.persistence.room.*

@Entity(
        foreignKeys = arrayOf(
                ForeignKey(entity = Student::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("student_id")),
                ForeignKey(entity = Course::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("course_id"))
        )
)
data class Enrollment(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "course_id") val courseId: Long = 0, // FK for Course entity
        @ColumnInfo(name = "student_id") val studentId: Long = 0, // FK for Student entity
        val grade: Grade = Grade.UNKWN
) {
    @Ignore
    constructor() : this(0, 0, 0, Grade.UNKWN)
}


enum class Grade {
    A, B, C, D, E, F, UNKWN
}
