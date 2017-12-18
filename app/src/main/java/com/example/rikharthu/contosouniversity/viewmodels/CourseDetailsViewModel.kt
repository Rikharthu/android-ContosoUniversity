package com.example.rikharthu.contosouniversity.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.rikharthu.contosouniversity.data.db.ContosoDatabase
import com.example.rikharthu.contosouniversity.data.models.Course
import com.example.rikharthu.contosouniversity.data.models.Instructor

class CourseDetailsViewModel(database: ContosoDatabase) : ViewModel() {

    private val courseIdData: MutableLiveData<Long>
    var courseId: Long = -1L
        set(value) {
            courseIdData.value = value
            field = value
        }

    val course: LiveData<Course>
    //    val deparment: LiveData<Department>
    val instructor: LiveData<Instructor>

    init {
        courseIdData = MutableLiveData()

        course = Transformations.switchMap(courseIdData, { input ->
            database.courseDao().findByIdLiveData(input)
        })
        instructor = Transformations.switchMap(courseIdData, { input ->
            // Get the CourseAssignment for given courseIdData, extract it's instructor id
            // and return the according Instructor
            Transformations.switchMap(database.courseAssignmentDao().findByCourseIdLiveData(input),
                    { courseAssignment ->
                        database.instructorDao().findByIdLiveData(courseAssignment.instructorId)
                    })
        })
    }

    fun setId(id: Long) {
        if (id == courseIdData.value) {
            return
        }
        courseIdData.value = id
    }
}