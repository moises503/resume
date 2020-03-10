package com.moises.resume.ui.skillset.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moises.domain.skillset.model.Course
import com.moises.resume.R
import com.moises.resume.core.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.course_item.view.*

class CoursesAdapter(private var courses: List<Course> = listOf()) :
    RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder =
        CourseViewHolder(parent.inflate(R.layout.course_item))


    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) =
        holder.bind(courses[position])

    fun updateDataSet(courses: List<Course>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(course: Course) {
            with(course){
                itemView.txtCourseName.text = "$name / $platform"
                Picasso.get().load(image).fit()
                    .centerCrop().into(itemView.imgCourse)
            }
        }
    }
}