package com.example.rikharthu.contosouniversity.data.models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

// or data class DepartmentWithCourses:Department (extends Department)
data class DepartmentWithCourses(
        @Embedded
        var department: Department? = null,
        @Relation(parentColumn = "id", entityColumn = "department_id", entity = Course::class)
        var courses: List<Course>? = null
)
/*
parentColumn refers to embedded Department's 'id' column
entityColumn refers to Course table's 'department_id' column (Department - Course relation) column
entity refers to table Course, which has relation to Departments table
 */