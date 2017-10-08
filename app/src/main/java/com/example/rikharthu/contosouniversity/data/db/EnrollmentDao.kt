package com.example.rikharthu.contosouniversity.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.rikharthu.contosouniversity.data.models.Enrollment

@Dao
interface EnrollmentDao {

    @Query("SELECT * FROM enrollment")
    fun getAll(): List<Enrollment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(courseAssignment: Enrollment): Long

    @Query("SELECT * FROM enrollment WHERE course_id = :courseId")
    fun findCourseEnrollments(courseId: Long): Enrollment

    @Query("SELECT * FROM enrollment WHERE student_id = :studentId")
    fun findStudentEnrollments(studentId: Long): List<Enrollment>

    @Insert
    fun insertAll(vararg enrollments: Enrollment): List<Long>
}