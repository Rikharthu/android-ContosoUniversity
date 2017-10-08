package com.example.rikharthu.contosouniversity.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.rikharthu.contosouniversity.data.models.Instructor

@Dao
interface InstructorDao {
    @Query("SELECT * FROM instructor")
    fun getAll(): List<Instructor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Instructor): Long

    @Query("SELECT * FROM instructor WHERE id = :id")
    fun getStudentById(id: Long): Instructor

    @Query("SELECT * FROM instructor WHERE first_name LIKE :name OR last_name LIKE :name")
    fun findByName(name: String): List<Instructor>

    @Query("SELECT * FROM instructor WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Instructor?

    @Insert
    fun insertAll(vararg instructors: Instructor): List<Long>
}