package com.moises.resume.ui.skillset

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moises.domain.skillset.model.Skillset
import com.moises.presentation.skillset.SkillsetPresenter
import com.moises.presentation.skillset.SkillsetView

import com.moises.resume.R
import com.moises.resume.ResumeApp
import com.moises.resume.core.toast
import com.moises.resume.ui.skillset.adapters.CoursesAdapter
import kotlinx.android.synthetic.main.fragment_skills.*

class SkillsetFragment : Fragment(), SkillsetView {

    private lateinit var coursesAdapter: CoursesAdapter
    private var skillsetPresenter : SkillsetPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupInjection()
    }

    override fun onResume() {
        super.onResume()
        skillsetPresenter?.retrieveSkillset()
    }

    override fun onStop() {
        skillsetPresenter?.onStop()
        super.onStop()
    }

    override fun hideUIElements() {
        hideOrShowElements(View.GONE)
    }

    override fun showUIElements() {
        hideOrShowElements(View.VISIBLE)
    }

    override fun showLoader() {
        pbLoader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        pbLoader.visibility = View.GONE
    }

    override fun setSkillset(skillset: Skillset) {
        coursesAdapter.updateDataSet(skillset.courses)
    }

    override fun showError(message: String) {
        context?.toast(message)
    }

    private fun hideOrShowElements(visibility : Int){
        txtCourses.visibility = visibility
        lstCourses.visibility = visibility
    }

    private fun setupViews() {
        coursesAdapter = CoursesAdapter()
        lstCourses.apply {
            adapter = coursesAdapter
            layoutManager = LinearLayoutManager(context,
                RecyclerView.VERTICAL,
                false)
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
        }
    }

    private fun setupInjection() {
        skillsetPresenter = (activity?.application as ResumeApp).getSkillsetComponent(this, this).getSkillsetPresenter()
    }
}
