package com.example.rikharthu.contosouniversity.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.rikharthu.contosouniversity.data.models.Department

@Dao
interface DepartmentDao {
    @Query("SELECT * FROM department")
    fun getAll(): List<Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Department): Long

    @Query("SELECT * FROM department WHERE id = :id LIMIT 1")
    fun findById(id: Long): Department?

    @Query("SELECT * FROM department WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Department?

    @Insert
    fun insertAll(vararg deparments: Department): List<Long>
}