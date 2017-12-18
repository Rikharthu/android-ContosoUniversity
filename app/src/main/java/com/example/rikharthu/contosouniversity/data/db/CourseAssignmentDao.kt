package com.example.rikharthu.contosouniversity.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.rikharthu.contosouniversity.data.models.CourseAssignment

@Dao
interface CourseAssignmentDao {

    @Query("SELECT * FROM course_assignment")
    fun getAll(): List<CourseAssignment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(courseAssignment: CourseAssignment): Long

    @Query("SELECT * FROM course_assignment WHERE course_id = :id")
    fun findByCourseId(id: Long): CourseAssignment

    @Query("SELECT * FROM course_assignment WHERE course_id = :id")
    fun findByCourseIdLiveData(id: Long): LiveData<CourseAssignment>

    @Query("SELECT * FROM course_assignment WHERE instructor_id = :id")
    fun findByInstructorId(id: Long): CourseAssignment

    @Insert
    fun insertAll(vararg courseAssignments: CourseAssignment): List<Long>
}