package com.example.rikharthu.contosouniversity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.example.rikharthu.contosouniversity.data.models.Course
import com.example.rikharthu.contosouniversity.data.models.Instructor
import com.example.rikharthu.contosouniversity.viewmodels.ContosoViewModelFactory
import com.example.rikharthu.contosouniversity.viewmodels.CourseDetailsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerToggle: ActionBarDrawerToggle

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Main"
        setSupportActionBar(toolbar)
        drawerToggle = setupDrawerToggle()
        drawerLayout.addDrawerListener(drawerToggle)

        navigation.setNavigationItemSelectedListener(this)

        val db = App.get(this).getContosoDatabase()
        val departments = db.departmentDao().getAll()

        val departmentsAdapter = DepartmentsAdapter(departments)
        departmentsRv.adapter = departmentsAdapter
        departmentsRv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        // TODO demo using ViewModels
        val factory = ContosoViewModelFactory(this.applicationContext)
        val courseModel = ViewModelProviders.of(this, factory).get(CourseDetailsViewModel::class.java)
        courseModel.course.observe(this, Observer<Course> {
            // Update UI
            Timber.d(it.toString())
        })
        courseModel.instructor.observe(this, Observer<Instructor> {
            // Update UI
            Timber.d(it.toString())
        })
        courseModel.courseId = 3141L
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig)
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "selected: ${item.itemId}", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
