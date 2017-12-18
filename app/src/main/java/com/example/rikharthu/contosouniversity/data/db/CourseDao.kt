package com.example.rikharthu.contosouniversity.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.rikharthu.contosouniversity.data.models.Course

@Dao
interface CourseDao {

    @Query("SELECT * FROM course")
    fun getAll(): List<Course>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(course: Course): Long

    @Query("SELECT * FROM course WHERE id = :id")
    fun findById(id: Long): Course

    @Query("SELECT * FROM course WHERE id = :id")
    fun findByIdLiveData(id: Long): LiveData<Course>

    @Query("SELECT * FROM course WHERE title = :title LIMIT 1")
    fun findByTitle(title: String): Course

    @Insert
    fun insertAll(vararg courses: Course): List<Long>
}