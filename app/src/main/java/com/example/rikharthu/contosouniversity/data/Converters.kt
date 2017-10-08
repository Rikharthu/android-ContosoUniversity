package com.example.rikharthu.contosouniversity.data

import android.arch.persistence.room.TypeConverter
import com.example.rikharthu.contosouniversity.data.models.Grade
import java.util.*

class Converters {

    @TypeConverter
    fun timestampToDate(value: Long?): Date? {
        return if (value == null)
            null
        else
            Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun intToGrade(value: Int?): Grade? {
        if (value != null) {
            when (value) {
                5 -> return Grade.A
                4 -> return Grade.B
                3 -> return Grade.C
                2 -> return Grade.D
                1 -> return Grade.E
                0 -> return Grade.F
                -1 -> return Grade.UNKWN
            }
        }
        return null
    }

    @TypeConverter
    fun gradeToInt(grade: Grade?): Int? {
        if (grade != null) {
            when (grade) {
                Grade.A -> return 5
                Grade.B -> return 4
                Grade.C -> return 3
                Grade.D -> return 2
                Grade.E -> return 1
                Grade.F -> return 0
                Grade.UNKWN -> return -1
            }
        }
        return null
    }
}