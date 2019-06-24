package com.moises.resume.ui.resume.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.moises.data.model.Experience
import com.moises.resume.R
import com.moises.resume.core.inflate
import kotlinx.android.synthetic.main.experience_item.view.*

class ExperienceAdapter(var experienceList : List<Experience>) :
    RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ExperienceViewHolder =
        ExperienceViewHolder(p0.inflate(R.layout.experience_item))

    override fun getItemCount(): Int = experienceList.size

    override fun onBindViewHolder(p0: ExperienceViewHolder, p1: Int) {
        p0.bindView(experienceList[p1])
    }

    fun updateDataSet(experienceList: List<Experience>) {
        this.experienceList = experienceList
        notifyDataSetChanged()
    }

    class ExperienceViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(experience : Experience){
            with(experience){
                itemView.txtCompany.text = company
                itemView.txtPosition.text = "Puesto: ${position}"
                itemView.txtDescription.text = description
            }
        }
    }
}