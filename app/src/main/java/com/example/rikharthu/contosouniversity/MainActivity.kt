package com.example.rikharthu.contosouniversity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.rikharthu.contosouniversity.data.models.Course
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = App.get(this).getContosoDatabase()
        val studentDao = db.studentDao()
        val students = studentDao.getAll()
        val departments = db.departmentDao().getAll()
        val instructors = db.instructorDao().getAll()
        val courses = db.courseDao().getAll()

        val carson = db.studentDao().findByName("Carson", "Alexander")
        val carsonEnrollments = db.enrollmentDao().findStudentEnrollments(carson.id)
        val carsonCourses = mutableListOf<Course>()
        carsonEnrollments.forEach {
            carsonCourses.add(db.courseDao().findById(it.courseId))
        }

        val x = 4
        Timber.d(x.toString())

        val departmentsAdapter = DepartmentsAdapter(departments)
        departmentsRv.adapter = departmentsAdapter
        departmentsRv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }
}
