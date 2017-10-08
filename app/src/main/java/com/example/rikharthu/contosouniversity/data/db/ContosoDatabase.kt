package com.example.rikharthu.contosouniversity.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.rikharthu.contosouniversity.data.Converters
import com.example.rikharthu.contosouniversity.data.db.ContosoDatabase.Companion.DATABASE_VERSION_2
import com.example.rikharthu.contosouniversity.data.models.*

@Database(entities = arrayOf(Student::class, Instructor::class, Department::class, Course::class,
        Enrollment::class, CourseAssignment::class), version = DATABASE_VERSION_2)
@TypeConverters(Converters::class)
abstract class ContosoDatabase : RoomDatabase() {

    companion object {
        /**
         * Create base tables
         */
        const val DATABASE_VERSION_1 = 1
        /**
         * Configured foreign keys
         */
        const val DATABASE_VERSION_2 = 2
    }

    abstract fun studentDao(): StudentDao
    abstract fun instructorDao(): InstructorDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun courseDao(): CourseDao
    abstract fun courseAssignmentDao(): CourseAssignmentDao
    abstract fun enrollmentDao(): EnrollmentDao
}