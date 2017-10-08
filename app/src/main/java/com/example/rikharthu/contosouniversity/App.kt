package com.example.rikharthu.contosouniversity

import android.app.Application
import android.content.Context
import com.example.rikharthu.contosouniversity.data.db.ContosoDatabase
import com.example.rikharthu.contosouniversity.data.db.DataManager
import com.example.rikharthu.contosouniversity.data.models.*
import timber.log.Timber
import java.sql.Date

class App : Application() {

    private lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        initializeDatabase()
        seetDatabase()
    }

    private fun seetDatabase() {
        // Students
        val studentDao = dataManager.database.studentDao()
        if (studentDao.getAll().isEmpty()) {
            val students = arrayOf(
                    Student(firstName = "Carson", lastName = "Alexander", enrollmentDate = Date.valueOf("2010-09-01")),
                    Student(firstName = "Meredith", lastName = "Alonso", enrollmentDate = Date.valueOf("2012-09-01")),
                    Student(firstName = "Arturo", lastName = "Anand", enrollmentDate = Date.valueOf("2013-09-01")),
                    Student(firstName = "Gytis", lastName = "Barzdukas", enrollmentDate = Date.valueOf("2012-09-01")),
                    Student(firstName = "Peggy", lastName = "Justice", enrollmentDate = Date.valueOf("2011-09-01")),
                    Student(firstName = "Laura", lastName = "Norman", enrollmentDate = Date.valueOf("2013-09-01")),
                    Student(firstName = "Yan", lastName = "Li", enrollmentDate = Date.valueOf("2014-09-01")),
                    Student(firstName = "Nino", lastName = "Olivetto", enrollmentDate = Date.valueOf("2005-09-01"))
            )
            val studentIds = studentDao.insertAll(*students)
            Timber.d("Student ids: ${studentIds.toString()}")
        }

        // Instructors
        val instructorDao = dataManager.database.instructorDao()
        if (instructorDao.getAll().isEmpty()) {
            val instructors = arrayOf(
                    Instructor(firstName = "Kim", lastName = "Abercrombie", hireDate = Date.valueOf("1995-03-11")),
                    Instructor(firstName = "Fadi", lastName = "Fakhouri", hireDate = Date.valueOf("2002-07-06")),
                    Instructor(firstName = "Roger", lastName = "Harui", hireDate = Date.valueOf("1998-07-01")),
                    Instructor(firstName = "Candace", lastName = "Kapoor", hireDate = Date.valueOf("2001-01-15")),
                    Instructor(firstName = "Roger", lastName = "Zheng", hireDate = Date.valueOf("2004-02-12"))
            )
            instructorDao.insertAll(*instructors)
        }

        // Departments
        val departmentsDao = dataManager.database.departmentDao()
        if (departmentsDao.getAll().isEmpty()) {
            val departments = arrayOf(
                    Department(name = "English", budget = 350000f,
                            startDate = Date.valueOf("2007-09-01"),
                            instructorId = instructorDao.findByName("Kim", "Abercrombie")!!.id),
                    Department(name = "Mathematics", budget = 100000f,
                            startDate = Date.valueOf("2002-07-06"),
                            instructorId = instructorDao.findByName("Fadi", "Fakhouri")!!.id),
                    Department(name = "Engineering", budget = 350000f,
                            startDate = Date.valueOf("1998-07-01"),
                            instructorId = instructorDao.findByName("Roger", "Harui")!!.id),
                    Department(name = "Economics", budget = 100000f,
                            startDate = Date.valueOf("2004-02-12"),
                            instructorId = instructorDao.findByName("Candace", "Kapoor")!!.id)
            )
            departmentsDao.insertAll(*departments)
        }

        // Courses
        val courseDao = dataManager.database.courseDao()
        if (courseDao.getAll().isEmpty()) {
            val courses = arrayOf(
                    Course(id = 1050L, title = "Chemistry", credits = 3,
                            departmentId = departmentsDao.findByName("Engineering")!!.id),
                    Course(id = 4022L, title = "Microeconomics", credits = 3,
                            departmentId = departmentsDao.findByName("Economics")!!.id),
                    Course(id = 4041L, title = "Macroeconomics", credits = 3,
                            departmentId = departmentsDao.findByName("Economics")!!.id),
                    Course(id = 1045L, title = "Calculus", credits = 4,
                            departmentId = departmentsDao.findByName("Mathematics")!!.id),
                    Course(id = 3141L, title = "Trigonometry", credits = 4,
                            departmentId = departmentsDao.findByName("Mathematics")!!.id),
                    Course(id = 2021L, title = "Composition", credits = 3,
                            departmentId = departmentsDao.findByName("English")!!.id),
                    Course(id = 2042L, title = "Literature", credits = 4,
                            departmentId = departmentsDao.findByName("English")!!.id)
            )
            courseDao.insertAll(*courses)
        }

        // Course Assignments
        val courseAssignmentDao = dataManager.database.courseAssignmentDao()
        if (courseAssignmentDao.getAll().isEmpty()) {
            val courseAssignments = arrayOf(
                    CourseAssignment(courseId = courseDao.findByTitle("Chemistry").id,
                            instructorId = instructorDao.findByName("Roger", "Harui")!!.id),
                    CourseAssignment(courseId = courseDao.findByTitle("Microeconomics").id,
                            instructorId = instructorDao.findByName("Roger", "Zheng")!!.id),
                    CourseAssignment(courseId = courseDao.findByTitle("Macroeconomics").id,
                            instructorId = instructorDao.findByName("Roger", "Zheng")!!.id),
                    CourseAssignment(courseId = courseDao.findByTitle("Calculus").id,
                            instructorId = instructorDao.findByName("Fadi", "Fakhouri")!!.id),
                    CourseAssignment(courseId = courseDao.findByTitle("Trigonometry").id,
                            instructorId = instructorDao.findByName("Roger", "Harui")!!.id),
                    CourseAssignment(courseId = courseDao.findByTitle("Chemistry").id,
                            instructorId = instructorDao.findByName("Candace", "Kapoor")!!.id),
                    CourseAssignment(courseId = courseDao.findByTitle("Composition").id,
                            instructorId = instructorDao.findByName("Kim", "Abercrombie")!!.id),
                    CourseAssignment(courseId = courseDao.findByTitle("Literature").id,
                            instructorId = instructorDao.findByName("Kim", "Abercrombie")!!.id)
            )
            courseAssignmentDao.insertAll(*courseAssignments)
        }

        // Enrollments
        val enrollmentDao = dataManager.database.enrollmentDao()
        if (enrollmentDao.getAll().isEmpty()) {
            val enrollments = arrayOf(
                    Enrollment(studentId = studentDao.findByName("Carson", "Alexander").id,
                            courseId = courseDao.findByTitle("Chemistry").id,
                            grade = Grade.A),
                    Enrollment(studentId = studentDao.findByName("Carson", "Alexander").id,
                            courseId = courseDao.findByTitle("Microeconomics").id,
                            grade = Grade.C),
                    Enrollment(studentId = studentDao.findByName("Carson", "Alexander").id,
                            courseId = courseDao.findByTitle("Macroeconomics").id,
                            grade = Grade.B),
                    Enrollment(studentId = studentDao.findByName("Meredith", "Alonso").id,
                            courseId = courseDao.findByTitle("Calculus").id,
                            grade = Grade.B),
                    Enrollment(studentId = studentDao.findByName("Meredith", "Alonso").id,
                            courseId = courseDao.findByTitle("Trigonometry").id,
                            grade = Grade.B),
                    Enrollment(studentId = studentDao.findByName("Meredith", "Alonso").id,
                            courseId = courseDao.findByTitle("Composition").id,
                            grade = Grade.B),
                    Enrollment(studentId = studentDao.findByName("Arturo", "Anand").id,
                            courseId = courseDao.findByTitle("Chemistry").id),
                    Enrollment(studentId = studentDao.findByName("Arturo", "Anand").id,
                            courseId = courseDao.findByTitle("Microeconomics").id,
                            grade = Grade.B),
                    Enrollment(studentId = studentDao.findByName("Yan", "Li").id,
                            courseId = courseDao.findByTitle("Composition").id,
                            grade = Grade.B),
                    Enrollment(studentId = studentDao.findByName("Peggy", "Justice").id,
                            courseId = courseDao.findByTitle("Literature").id,
                            grade = Grade.E)
            )
            enrollmentDao.insertAll(*enrollments)
        }
    }

    private fun initializeDatabase() {
        dataManager = DataManager(this)
    }

    fun getContosoDatabase(): ContosoDatabase {
        return dataManager.database
    }

    companion object {
        fun get(context: Context): App {
            return context.applicationContext as App
        }
    }
}