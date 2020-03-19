package com.moises.resume.ui.resume

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moises.domain.resume.model.Profile
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumeView
import com.moises.resume.R
import com.moises.resume.ResumeApp
import com.moises.resume.core.deviceHasInternetConnection
import com.moises.resume.core.toast
import com.moises.resume.ui.resume.adapters.ExperienceAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_resume.*

class ResumeFragment : Fragment(), ResumeView {

    private var presenter: ResumePresenter? = null
    private lateinit var experienceAdapter: ExperienceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_resume, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInjection()
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        presenter?.attemptGetResume(context?.deviceHasInternetConnection() ?: true)
    }

    override fun showLoading() {
        pbLoader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoader.visibility = View.GONE
    }

    override fun displayProfile(profile: Profile) {
        Picasso.get().load(profile.photo).fit()
            .centerCrop().into(imgvPhoto)
        txtFullName.text = String.format(profile.fullName.orEmpty(), "Nombre")
        txtContactInfo.text = String.format(profile.contactInfo.orEmpty(), "E-mail", "Teléfono")
        txtLibs.text = profile.libs
        txtPatterns.text = profile.archs
        experienceAdapter.updateDataSet(profile.experience.orEmpty())
    }

    override fun showError(message: String) {
        context?.toast(message)
    }

    override fun showViews() {
        this.showViews(View.VISIBLE)
    }

    override fun hideViews() {
        this.showViews(View.GONE)
    }

    override fun onStop() {
        presenter?.onStop()
        super.onStop()
    }


    private fun setupInjection() {
        presenter = (activity?.application as ResumeApp)
            .getResumeComponent(this, this).getResumePresenter()
    }

    private fun setupViews() {
        experienceAdapter = ExperienceAdapter(emptyList())
        rvExperience?.apply {
            layoutManager = LinearLayoutManager(context,
                RecyclerView.VERTICAL,
                false)
            adapter = experienceAdapter
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
        }
    }

    private fun showViews(visibility: Int) {
        imgvPhoto.visibility = visibility
        txtFullName.visibility = visibility
        txtContactInfo.visibility = visibility
        txtLibs.visibility = visibility
        txtPatterns.visibility = visibility
        txtExperienceText.visibility = visibility
        rvExperience.visibility = visibility
        txtLibsText.visibility = visibility
        txtPatternsText.visibility = visibility
    }

}
