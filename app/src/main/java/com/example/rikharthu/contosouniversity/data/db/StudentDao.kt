package com.example.rikharthu.contosouniversity.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.rikharthu.contosouniversity.data.models.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Student): Long

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Long): Student

    @Query("SELECT * FROM student WHERE first_name LIKE :name OR last_name LIKE :name")
    fun findByName(name: String): List<Student>

    @Query("SELECT * FROM student WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Student

    @Insert
    fun insertAll(vararg students: Student): List<Long>
}