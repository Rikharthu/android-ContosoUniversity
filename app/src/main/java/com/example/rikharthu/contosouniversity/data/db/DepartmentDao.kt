package com.example.rikharthu.contosouniversity.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.rikharthu.contosouniversity.data.models.Department
import com.example.rikharthu.contosouniversity.data.models.DepartmentWithCourses

@Dao
interface DepartmentDao {

    companion object {
        const val tableName = "department"
    }

    @Query("SELECT * FROM $tableName")
    fun getAll(): List<Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Department): Long

    @Query("SELECT * FROM $tableName WHERE id = :id LIMIT 1")
    fun findById(id: Long): Department?

    @Query("SELECT * FROM $tableName WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Department?

    @Insert
    fun insertAll(vararg deparments: Department): List<Long>

    @Transaction
    @Query("SELECT * FROM $tableName")
    fun getDepartmentsWithCourses(): List<DepartmentWithCourses>

    @Transaction
    @Query("SELECT * FROM $tableName")
    fun getDepartmentsWithCoursesLiveData(): LiveData<List<DepartmentWithCourses>>
}