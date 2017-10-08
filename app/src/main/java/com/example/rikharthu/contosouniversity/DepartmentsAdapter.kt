package com.example.rikharthu.contosouniversity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rikharthu.contosouniversity.data.models.Department
import kotlinx.android.synthetic.main.department_item.view.*

class DepartmentsAdapter(val departments: List<Department>) : RecyclerView.Adapter<DepartmentsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(departments[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.department_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = departments.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.departmentNameTv
        val administrator: TextView = itemView.departmentAdministratorTv
        val budget: TextView = itemView.departmentBudgetTv

        fun bind(department: Department) {
            name.text = department.name
            // TODO make it a query tuple instead of fetching here
//            administrator.text = department.instructorId.toString()
            val instructor = App.get(itemView.context).getContosoDatabase().instructorDao().getStudentById(department.instructorId!!)
            administrator.text = "${instructor.firstName} ${instructor.lastName}"
            budget.text = department.budget.toString()
        }
    }
}